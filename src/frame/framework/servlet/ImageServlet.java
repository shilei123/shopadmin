package framework.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.action.system.FileUploadAction;
import framework.config.ImageContentType;
import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;
import framework.db.pojo.TXtUser;

public class ImageServlet extends HttpServlet {
	
	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TXtUser user = (TXtUser)request.getSession().getAttribute("user");
		String fileId = request.getParameter("fileId");
		String isZoom = request.getParameter("isZoom");
		
		File file = null;
		TPbUploadfile pojo = queryImage(fileId);
		if(pojo==null){return;}
		if("true".equals(isZoom) && pojo.getFileSize().longValue()>1204L*20L){
			file = new File(pojo.getFilePath()+".zoom");
		}else{
			file = new File(pojo.getFilePath());
		}
		
		if(file.exists()){
			response.reset();
			
			String name = file.getName();
			String hz = name.substring(name.indexOf(".")+1, name.length());
			response.setContentType(ImageContentType.getContentType(hz));
	        response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
	        
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
		}else{
			request.getRequestDispatcher("no_img.gif").forward(request, response);
		}
	}

	/**
	 * 数据库查询用户头像
	 * @param swdm
	 * @return
	 */
	private TPbUploadfile queryImage(String fileId){
		DBUtil db = new DBUtil();
		TPbUploadfile pojo = null;
		try{
			pojo = FileUploadAction.getTPbUploadfile(fileId, db);
		}catch(Exception ex){}
		//db.close();
		return pojo;
	}
	
	public ImageServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

}
