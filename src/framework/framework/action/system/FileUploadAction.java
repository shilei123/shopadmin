package framework.action.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import framework.bean.UserMsg;
import framework.config.Config;
import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;
import framework.db.pojo.TXtUser;
import framework.helper.RequestHelper;
import framework.util.DateUtils;

@SuppressWarnings({"unchecked","rawtypes"})
public class FileUploadAction {
	private File[] uploadFiles;
    private String[] uploadFilesContentType;
    private String[] uploadFilesFileName;
    
    private int fileSize;//最大文件大小
    private String dirName;//存放目录名称
    private String fileType;//使用逗号分隔
    private String msg;//返回信息
    private int fileCount;//文件个数
    private String ids;//上传ids
    private String groupId;//分组ID
    private TPbUploadfile pojo;
    private List<TPbUploadfile> pojos;
    
    public String load(){
    	DBUtil db = DBUtil.getInstance();
    	if(ids==null && "".equals(ids)){
    		return "success"; 
    	}
    	String[] idarr = ids.split(",");
    	boolean isOne = idarr.length==1;
    	if(!isOne){
    		pojos = new ArrayList(idarr.length);
    	}
    	for (int i = 0; i < idarr.length; i++) {
			if(isOne){
				pojo = (TPbUploadfile) db.get(TPbUploadfile.class, idarr[i]);
			}else{
				pojos.add((TPbUploadfile) db.get(TPbUploadfile.class, idarr[i]));
			}
		}
    	
    	return "success";
    }
    
    public String upload(){   
    	DBUtil db = DBUtil.getInstance();
    	dirName = (dirName==null||dirName.equals(""))?"":"/"+dirName;//是否需要目录
    	
        String root = Config.getFileUploadRoot()+dirName;
        File rootFile = new File(root);   
        if (!rootFile.exists()) {   
        	rootFile.mkdirs();   
        }
        
        if (uploadFiles != null) {
        	if(uploadFiles.length>this.fileCount){
        		msg = "文件最大上传个数为"+this.fileCount+"个";return "error";
        	}
        	ArrayList fileList = new ArrayList();
        	ids = "";
            for (int i = 0; i < uploadFiles.length; i++) {
            	int filesize = getFileSize(uploadFiles[i]);
            	//System.out.println(uploadFiles[i].getName());
            	int indexof = uploadFilesFileName[i].lastIndexOf(".");
            	if(indexof == -1) { //文件中没有"."
            		msg = "文件"+uploadFilesFileName[i]+"是不允许上传类型";return "error";
            	}
            	/*String[] fds = uploadFilesFileName[i].split("\\.");
            	String filetype1 = fds[fds.length-1];*/
            	String filetype = uploadFilesFileName[i].substring(indexof+1);
            	String filename = uploadFilesFileName[i].substring(0 , indexof);
            	
            	if(!validateFileSize(filesize)){
            		msg = "文件"+uploadFilesFileName[i]+"超过最大大小";return "error";
            	}
            	if(!validateFileType(filetype)){
            		msg = "文件"+uploadFilesFileName[i]+"是不允许上传类型";return "error";
            	}
            	String id = UUID.randomUUID().toString();
        		File tofile = new File(rootFile, id+"."+filetype);
        		try {
					FileUtils.copyFile(uploadFiles[i], tofile);
				} catch (IOException e) {
					msg = "文件上传失败";
				}
        		TPbUploadfile dbf = this.getTPbUploadfile(id,tofile, filesize, filename, filetype);
        		dbf.setGroupId(this.groupId);
        		db.insert(dbf);  
        		ids += id;
        		if(i<uploadFiles.length-1){
        			ids += ",";
        		}
        		fileList.add(dbf);
			}
            RequestHelper.getRequest().setAttribute("fileList", fileList);
            RequestHelper.getRequest().setAttribute("fileListJson", JSONArray.fromObject(fileList));
            
            msg = "文件上传成功";
        }   
        return "success";   
    }
    
    public String uploadfileSetValid(){
    	DBUtil db = DBUtil.getInstance();
    	TXtUser user = (TXtUser)RequestHelper.getSession().getAttribute("user");
    	
    	if(this.ids==null || this.ids.equals("")){return "error";}
    	String[] idArr = ids.split(",");
    	for (int i = 0; i < idArr.length; i++) {
    		Map params = new HashMap(1);
        	params.put("fileId", new BigDecimal(idArr[i]));
        	TPbUploadfile pojo = (TPbUploadfile)db.queryByPojo(TPbUploadfile.class, params).get(0);
        	if(pojo.getCreateUserId().equals(user.getUId())){
        		pojo.setFlag("1");
        		pojo.setUpdateTime(DateUtils.getTimestamp());
        		db.update(pojo);
        	}else{
        		return "error";
        	}
		}
    	return "success";
    }
    
    public String uploadfileDelete(){
    	DBUtil db = DBUtil.getInstance();
    	TXtUser user = (TXtUser)RequestHelper.getSession().getAttribute("user");
    	
    	if(this.ids==null || this.ids.equals("")){msg="文件标识不能为空";return "error";}
    	TPbUploadfile pojo = (TPbUploadfile)db.get(TPbUploadfile.class, ids);
    	if(pojo==null){msg="文件不存在";return "error";}
    	if("1".equals(pojo.getFlag())){msg="有效文件不可删除";return "error";}
    	if(pojo.getCreateUserId().equals(user.getUId())){
    		db.delete(pojo);
    	}else{
    		msg= "文件上传者才可删除";
    		return "error";
    	}
    	return "success";
    }
    
    private boolean validateFileSize(int filesize){
    	return fileSize==0?true:(filesize>fileSize*1024)?false:true;
    }
    
    private boolean validateFileType(String filetypename){
    	if(this.fileType==null || "".equals(this.fileType)){
    		return true;
    	}
    	return this.fileType.toUpperCase().indexOf(filetypename.toUpperCase())>-1;
    }
    
    private TPbUploadfile getTPbUploadfile(String id, File file, int filesize, String name, String type){
		UserMsg user = (UserMsg)RequestHelper.getSession().getAttribute("user");

		TPbUploadfile dbf = new TPbUploadfile();
    	dbf.setFileId(id);
		dbf.setFileName(name);
		dbf.setFileType(type);
		dbf.setFilePath(file.getAbsolutePath());
		dbf.setUpType("default");
		dbf.setCreateUserId(user.getUId());
		dbf.setFlag("0");
		dbf.setUserType("0");
		dbf.setCreateTime(DateUtils.getTimestamp());
		dbf.setFileSize(filesize);
		return dbf;
    }
    
    public static TPbUploadfile getTPbUploadfile(String id, DBUtil db){
		Map params = new HashMap(1);
		params.put("fileId", new BigDecimal(id));
    	List<TPbUploadfile> list = db.queryByPojo(TPbUploadfile.class, params);
		return list.size()==0?null:list.get(0);
	}

    private static int getFileSize(File file){
    	FileInputStream fis = null;
    	try{
			fis = new FileInputStream(file);
			return fis.available();
		}catch (Exception e) {
		}finally{
			if(fis!=null){try {
				fis.close();
			} catch (Exception e) {}}
		}
		return 0;
    }

	public File[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String[] getUploadFilesContentType() {
		return uploadFilesContentType;
	}

	public void setUploadFilesContentType(String[] uploadFilesContentType) {
		this.uploadFilesContentType = uploadFilesContentType;
	}

	public String[] getUploadFilesFileName() {
		return uploadFilesFileName;
	}

	public void setUploadFilesFileName(String[] uploadFilesFileName) {
		this.uploadFilesFileName = uploadFilesFileName;
	}

	

	/**
	 * 文件大小转成KB，MB，
	 * @param size
	 * @return
	 */
	public static String fileSizeToString(int size){
		if(size>1024){
			return (size/1024)+"MB";
		}else{
			return size+"KB";
		}
	}
	
	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public TPbUploadfile getPojo() {
		return pojo;
	}

	public void setPojo(TPbUploadfile pojo) {
		this.pojo = pojo;
	}

	public List<TPbUploadfile> getPojos() {
		return pojos;
	}

	public void setPojos(List<TPbUploadfile> pojos) {
		this.pojos = pojos;
	}
    

}
