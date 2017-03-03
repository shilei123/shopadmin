package framework.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.action.system.FileUploadAction;
import framework.config.Config;
import framework.config.ImageContentType;
import framework.db.DBUtil;
import framework.db.pojo.TXtUser;

public class UserHeadImgServlet extends HttpServlet {
	public final static String SQL = "select (file_id||'.'||file_type) headimg from t_xt_yhxx yh, t_pb_uploadfile uf"
			+" where yh.xx_value=uf.file_id and sw_dm=? and xx_key='userheadimg'";
	public final static java.util.HashMap<String, String> userImgMap = new java.util.HashMap();//缓存用户图片地址
	public final static String DEFAULT_HEADIMG = "userheadimg_default.jpg";
	
	public UserHeadImgServlet() {
		super();
	}

	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TXtUser user = (TXtUser)request.getSession().getAttribute("to_user");
		String swdm = request.getParameter("swdm");
		String fileId = request.getParameter("fileId");
		swdm = (swdm==null||swdm.equals(""))?user.getUId():swdm;
		String url = DEFAULT_HEADIMG;
		
		if(fileId!=null&&!fileId.equals("")){//直接调取
			DBUtil db = new DBUtil();
			try{
				url = FileUploadAction.getTPbUploadfile(fileId, db).getFilePath();
			}catch(Exception ex){}
			//db.close();
		}else if(userImgMap.get(swdm)!=null){//直接获取缓存地址
			url = userImgMap.get(swdm);
		}else{
			url = queryUserHeadImg(swdm);
		}
		
		File file = new File(url);
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
			request.getRequestDispatcher("userheadimg_default.jpg").forward(request, response);
		}
	}

	/**
	 * 数据库查询用户头像
	 * @param swdm
	 * @return
	 */
	private String queryUserHeadImg(String swdm){
		DBUtil db = new DBUtil();
		String url = DEFAULT_HEADIMG;
		try{
			List<Map> list = db.queryBySQL(SQL, swdm);
			if(list.size()>0){
				url = Config.getUserHeadImgRoot()+list.get(0).get("headimg").toString();
				userImgMap.put(swdm,url);
			}
		}catch(Exception ex){}
		//db.close();
		return url;
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
