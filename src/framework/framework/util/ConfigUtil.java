package framework.util;

/*import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;*/

public class ConfigUtil {
	private String localUploadPath;
	private String imageServer;
	private String attachServer;
	private String imgServerUploadURL;
	private String attachServerUploadURL;
	
	public static ConfigUtil getInstance() {
		framework.util.ConfigUtil instance = (framework.util.ConfigUtil) framework.util.SpringContextUtil.getBean("configBean");
		return instance;
	}
	
	public String getLocalUploadPath() {
		return localUploadPath;
	}

	public void setLocalUploadPath(String localUploadPath) {
		this.localUploadPath = localUploadPath;
	}

	public String getImageServer() {
		return imageServer;
	}

	public void setImageServer(String imageServer) {
		this.imageServer = imageServer;
	}

	public String getAttachServer() {
		return attachServer;
	}

	public void setAttachServer(String attachServer) {
		this.attachServer = attachServer;
	}

	public String getImgServerUploadURL() {
		return imgServerUploadURL;
	}

	public void setImgServerUploadURL(String imgServerUploadURL) {
		this.imgServerUploadURL = imgServerUploadURL;
	}

	public String getAttachServerUploadURL() {
		return attachServerUploadURL;
	}

	public void setAttachServerUploadURL(String attachServerUploadURL) {
		this.attachServerUploadURL = attachServerUploadURL;
	}

	public static void main(String[] args) {
		/*ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
		HneduConfigUtil c = (HneduConfigUtil)ac1.getBean("hneduConfigBean");
		System.out.println(c);*/
	}
}