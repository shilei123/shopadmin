package framework.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.action.system.LogAction;
import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;

public class FileDownServlet extends HttpServlet {
	public final static String CONTENT_TYPE = "application/x-msdownload";
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String fileid = request.getParameter("fileid");
		String logname = request.getParameter("logname");
		
		TPbUploadfile pojo = null;
		if(fileid!=null){
			pojo = queryTPbUploadfile(fileid);
		}else if(logname!=null){
			pojo = getLogTPbUploadfile(logname);
		}
		
		if(pojo!=null){
			try {
				this.download(request, response, pojo);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
	
	public TPbUploadfile getLogTPbUploadfile(String logname){
		TPbUploadfile pojo = new TPbUploadfile();
		pojo.setFilePath(LogAction.getLogDir()+"/"+logname);
		pojo.setFileName(logname.split("\\.")[0]);
		pojo.setFileType("log");
		return pojo;
	}
	
	public TPbUploadfile queryTPbUploadfile(String fileid){
		TPbUploadfile pojo = null;
		DBUtil db = new DBUtil();
		try{
			HashMap params = new HashMap(1);
			params.put("fileId", fileid);
			List<TPbUploadfile> list = db.queryByPojo(TPbUploadfile.class,params);
			if(list.size()>0){
				pojo = list.get(0);
			}
		} finally {
			//db.close();
		}
		return pojo;
	}
	
	public void download(HttpServletRequest request, HttpServletResponse response, TPbUploadfile pojo) throws Exception {
		if(pojo!=null){
			File file = new File(pojo.getFilePath());
			if(file.exists()){
				String filename = URLEncoder.encode(pojo.getFileName()+"."+pojo.getFileType(), "utf-8");
				response.reset();
				response.setContentType(CONTENT_TYPE);
		        response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
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
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.doGet(request, response);
	}
}
