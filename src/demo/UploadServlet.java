package demo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.log4j.Logger;

import framework.util.HttpUtil;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet  {
	private String upload;
	private String webPath;
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(UploadServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		upload = "http://192.168.0.207:9080/uploadPhoto/upload.do";
		webPath = "C:/111/";
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map result = new HashMap();
		Map name = new HashMap();
		response.setContentType("text/html");
		long size=request.getContentLength();
		if(0 != size && size>2097152){
			result.put("msg", "图片大于1M，请重新上传！");
			result.put("result", false);
			HttpUtil.write(response,result);
		} else {
			//图片上传路径
			String uploadPath = request.getSession().getServletContext().getRealPath("/")+ "upload/";
			//图片网络相对路径
			String imagePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
			
			//文件夹不存在就自动创建：
			if (!new File(webPath).isDirectory()){
				new File(webPath).mkdirs();
			}
				
			result.put("msg", "上传失败，请重新上传！");
			result.put("result", false);
			
			try {
				DiskFileUpload fu = new DiskFileUpload();
				//设置最大文件尺寸
				fu.setSizeMax(2097152);
				//设置缓冲区大小，这里是4kb
				fu.setSizeThreshold(4096);
				//得到所有的文件：
				List fileItems = fu.parseRequest(request);
				Iterator i = fileItems.iterator();
				int num = 1;
				//依次处理每一个文件：
				while (i.hasNext()) {
					String destinationfileName = UUID.randomUUID().toString();
					FileItem file = (FileItem) i.next();
					//获得文件名，这个文件名是用户上传时用户的绝对路径：
					String sourcefileName = file.getName();
					if (sourcefileName != null
							&& (sourcefileName.endsWith(".jpg") || sourcefileName
									.endsWith(".gif") || sourcefileName
									.endsWith(".png") || sourcefileName.endsWith(".JPG") || sourcefileName
									.endsWith(".GIF") || sourcefileName
									.endsWith(".PNG"))) {
						//在这里可以记录用户和文件信息,生成上传后的文件名
						if (sourcefileName.endsWith(".jpg")) {
							destinationfileName = destinationfileName + ".jpg";
						} else if (sourcefileName.endsWith(".gif")) {
							destinationfileName = destinationfileName + ".gif";
						} else if (sourcefileName.endsWith(".png")) {
							destinationfileName = destinationfileName + ".png";
						} else if (sourcefileName.endsWith(".JPG")) {
							destinationfileName = destinationfileName + ".JPG";
						} else if (sourcefileName.endsWith(".GIF")) {
							destinationfileName = destinationfileName + ".GIF";
						} else if (sourcefileName.endsWith(".PNG")) {
							destinationfileName = destinationfileName + ".PNG";
						}
						File f1 = new File(webPath + destinationfileName);
						file.write(f1);
						result.put("msg", "成功上传！");
						if(upload(destinationfileName)) {
							result.put("msg", "上传成功！");
							result.put("result", true);
							name.put("name_"+num, destinationfileName);
							//f1.delete();
						} else {
							result.put("msg", "上传文件出错！");
							result.put("result", false);
						}
					} else {
						result.put("msg", "上传文件出错，只能上传 *.jpg , *.gif ,*.png");
						result.put("result", false);
					}
					num++;
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "图片大小或格式有误，请重新上传！");
				result.put("result", false);
			} finally {
				result.put("name", name);
				HttpUtil.write(response,result);
			}
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public boolean upload(String destinationfileName) {
		boolean flag = false;
		String targetURL = null; //-- 指定URL
		File targetFile = null; //-- 指定上传文件
		targetFile = new File(webPath+destinationfileName);
		targetURL = upload+"?type=verify"; // servleturl
		PostMethod filePost = new PostMethod(targetURL);
		try {
			// 通过以下方法可以模拟页面参数提交
			Part[] parts = { (Part) new FilePart(targetFile.getName(), targetFile) };
			filePost.setRequestEntity(new MultipartRequestEntity((org.apache.commons.httpclient.methods.multipart.Part[]) parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			
			if (status == HttpStatus.SC_OK) { //上传成功
				flag = true;
			} else { //上传失败
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			filePost.releaseConnection();
		}
		return flag;
	}	
}