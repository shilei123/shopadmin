package framework.action.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/*import java.util.Collections;
import java.util.Comparator;*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

import org.apache.commons.collections.map.ListOrderedMap;

import framework.db.DBUtil;
import framework.helper.RequestHelper;
import framework.util.DateUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import common.Logger;

/**
 * 数据库数据导入导出
 * @author youka
 */
@SuppressWarnings({"serial","unchecked","rawtypes","resource"})
public class DataAction  extends ActionSupport {
	private static final Logger log = Logger.getLogger(DataAction.class);
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");;
	private static final JsonConfig jsonConfig = new JsonConfig();
	
	static {
		//解决json解析Timestamp的问题
		MorpherRegistry mor = JSONUtils.getMorpherRegistry();
		mor.registerMorpher(new AbstractObjectMorpher() {
			public Class morphsTo() {
				return Timestamp.class;
			}
			public Object morph(Object obj) {
				if(obj==null){return null;}
				if(obj.getClass()==String.class){
					try {
						return new Timestamp(simpleDateFormat.parse(obj.toString()).getTime());
					} catch (ParseException e) {
						log.error("",e);
					}
				}
				return null;
			}
		});
		
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessor() {
			public Object processObjectValue(String arg0, Object obj, JsonConfig arg2) {
				Timestamp t = (Timestamp)obj;
				return simpleDateFormat.format(t.getTime());
			}
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				
				return null;
			}
		});
	}
	
	private File dataFile;
	private String dataFileContentType;   
	private String dataFileFileName; 
	
	private String pojos;
	private String where;
	
	public String list(){
		ListOrderedMap listmap = DBUtil.getEntitys();
		
		List<Map> list = new ArrayList(listmap.size());
		String name = RequestHelper.getParameter("name");
		name = name==null?"":name.toUpperCase();
		boolean isFilter = !(name==null||"".equals(name));
		for(Object key : listmap.keySet()){
			if(isFilter && key.toString().toUpperCase().indexOf(name)==-1){
				continue;
			}
			HashMap row = new HashMap(1);
			row.put("name", key);
			list.add(row);
		}
		
		String json = null;
		if("combobox".equals(RequestHelper.getParameter("dataType"))){
			JSONArray array = new JSONArray();
			if("true".equals(RequestHelper.getParameter("showNullOption"))){
				HashMap nullMap = new HashMap(2);
				nullMap.put("value", "");
				nullMap.put("text", "--请选择--");
				array.add(nullMap);
			}
			for(Map map : list){
				map.put("value", map.get("name"));
				map.put("text", map.get("name"));
			}
			array.addAll(list);
			json = array.toString();
		}else{
			JSONObject obj = new JSONObject();
			obj.put("total", list.size());
			obj.put("rows", list);
			json = obj.toString();
		}
		try {
			RequestHelper.wirte(json);
		} catch (IOException e) {
			log.error("", e);
		}
		return null;
	}
	
	/**
	 * 导入数据
	 * @return
	 */
	public String importData() {
		DBUtil db = DBUtil.getInstance();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(dataFile);
			ois = new ObjectInputStream(fis);
			Object pojo = ois.readObject();
			while(pojo!=null && !"End".equals(pojo)){
				db.saveOrUpdate(pojo);
				pojo = ois.readObject();
			}
		} catch (Exception e) {
			//db.rollback();
			try {
				if(ois!=null){ois.close();}
			} catch (Exception e2) {
				log.error("", e2);
			}
			try {
				if(fis!=null){fis.close();}
			} catch (Exception e2) {
				log.error("", e2);
			}
			log.error("", e);
			RequestHelper.getRequest().setAttribute("msg", "error");
			return "error";
		}
		RequestHelper.getRequest().setAttribute("msg", "success");
		return "success";
	}
	
	public String importJson(){
		DBUtil db = DBUtil.getInstance();
		int count = 0;
		try {
			FileInputStream fis = new FileInputStream(dataFile);
			byte[] tmp = new byte[fis.available()];
			fis.read(tmp);
			JSONArray arr = JSONArray.fromObject(new String(tmp), jsonConfig);
			int len = arr.size();
			for(int i=0;i<len;i++){
				JSONObject data = arr.getJSONObject(i);
				Class pojoClass = Class.forName(data.getString("pojoClassName"));
				JSONArray rows = data.getJSONArray("pojoList");
				int len2 = rows.size();
				for(int j=0;j<len2;j++){
					JSONObject row = rows.getJSONObject(j);
					//Object pojo = JSONObject.toBean(row, pojoClass);
					Object ooo1 = pojoClass.newInstance();
					
					Object pojo = JSONObject.toBean(row, ooo1, jsonConfig);
					db.saveOrUpdate(pojo);
					count++;
				}
			}
			
		} catch (Exception e) {
			log.error("", e);
			RequestHelper.getRequest().setAttribute("msg", "error");
			return "error";
		}
		RequestHelper.getRequest().setAttribute("updateCount", count);
		RequestHelper.getRequest().setAttribute("msg", "success");
		return "success";
	}
	
	public String importXml(){
		DBUtil db = DBUtil.getInstance();
		List list = null;
		try {
			FileInputStream fis = new FileInputStream(dataFile);
			byte[] tmp = new byte[fis.available()];
			fis.read(tmp);
			fis.close();
			String xml = new String(tmp);
			XStream xs = new XStream(new DomDriver());
			list = (List)xs.fromXML(xml);
			RequestHelper.getRequest().setAttribute("msg", "success");
		} catch (Exception e) {
			log.error("",e);
			RequestHelper.getRequest().setAttribute("msg", "error");
			return "error";
		}
		if(list!=null){
			db.saveOrUpdate(list);
		}
		return "success";
	}
	public String exportXml() throws IOException{
		DBUtil db = DBUtil.getInstance();
		String[] names = pojos.split(",");
		List pkg = new ArrayList();
		if(where!=null){
			where = new String(where.getBytes("ISO8859-1"),"UTF-8");
		}
		for(String name : names){
			List list = db.queryByHql("from "+name+" tmp where 1=1 "+(where==null?"":where), new HashMap());
			pkg.addAll(list);
		}
		
		HttpServletResponse response = RequestHelper.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.reset();
		response.setContentType("application/x-msdownload");
        response.addHeader("Content-Disposition", "attachment; filename=\"dataexport_"+DateUtils.toDate("yyyyMMddHHmmss", System.currentTimeMillis())+".xml\"");
		XStream xs = new XStream(new DomDriver());
		xs.toXML(pkg,response.getOutputStream());
		
		return null;
	}
	
	public String exportJson() throws IOException{
		DBUtil db = DBUtil.getInstance();
		String[] names = pojos.split(",");
		List pkg = new ArrayList();
		for(String name : names){
			List list = db.queryByHql("from "+name+" tmp "+(where==null?"":where), new HashMap());
			if(list.size()>0){
				HashMap map = new HashMap();
				map.put("pojoClassName", list.get(0).getClass().getName());
				map.put("pojoList", list);
				pkg.add(map);
			}
		}
		
		//解析成json数据
		JSONArray json = JSONArray.fromObject(pkg, jsonConfig);
		
		//导出数据
		HttpServletResponse response = RequestHelper.getResponse();
		//response.setCharacterEncoding("UTF-8");
		response.reset();
		response.setContentType("application/x-msdownload");
        response.addHeader("Content-Disposition", "attachment; filename=\"dataexport_"+DateUtils.toDatetime(System.currentTimeMillis())+".json\"");
        response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
	
	/**
	 * 导出数据
	 * @return
	 */
	public String exportData(){
		File file = null;
		FileOutputStream fis = null;
		ObjectOutputStream fos = null;
		try {
			file = File.createTempFile(UUID.randomUUID().toString(), ".sdb");
			fis = new FileOutputStream(file);
			fos = new ObjectOutputStream(fis);
			
			DBUtil db = DBUtil.getInstance();
			String[] names = pojos.split(",");
			for(String name : names){
				List list = db.queryByHql("from "+name+" tmp "+(where==null?"":where), new HashMap());
				int len = list.size();
				for (int i = 0; i < len; i++) {
					fos.writeObject(list.get(i));
					fos.flush();
				}
			}
			fos.writeObject("End");
			fos.close();
			fos.close();
			fos=null;
			fis.close();
			fis=null;
			
			
			//导出数据
			HttpServletResponse response = RequestHelper.getResponse();
			response.reset();
			response.setContentType("application/x-msdownload");
	        response.addHeader("Content-Disposition", "attachment; filename=\"dataexport_"+DateUtils.toDatetime(System.currentTimeMillis())+".sdb\"");
	        int fileLength = (int) file.length();
            response.setContentLength(fileLength);
	        
            InputStream inStream = new FileInputStream(file);
            byte[] buf = new byte[4096];
            ServletOutputStream servletOS = response.getOutputStream();
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
            	servletOS.write(buf, 0, readLength);
            }
            inStream.close();
            servletOS.flush();
            servletOS.close();
            file.delete();
		} catch (Exception e) {
			log.error("",e);
			try {
				if(fos!=null){fos.close();}
			} catch (Exception e2) {
				log.error("", e2);
			}
			try {
				if(fis!=null){fis.close();}
			} catch (Exception e2) {
				log.error("", e2);
			}
			try {
				RequestHelper.wirte("<span style='color:red;'>导出失败</span><div style='display:none;'>"+e.getMessage()+"</div>");
			} catch (IOException e1) {
				log.error("", e1);
			}
		}
		return null;
	}
	
//=================================================
	public String getPojos() {
		return pojos;
	}

	public void setPojos(String pojos) {
		this.pojos = pojos;
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getDataFileContentType() {
		return dataFileContentType;
	}

	public void setDataFileContentType(String dataFileContentType) {
		this.dataFileContentType = dataFileContentType;
	}

	public String getDataFileFileName() {
		return dataFileFileName;
	}

	public void setDataFileFileName(String dataFileFileName) {
		this.dataFileFileName = dataFileFileName;
	}

	public File getDataFile() {
		return dataFile;
	}
	
	
}
