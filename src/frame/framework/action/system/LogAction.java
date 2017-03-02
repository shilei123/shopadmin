package framework.action.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletOutputStream;

import org.apache.log4j.Logger;

import framework.action.FenyeBase;
import framework.helper.RequestHelper;
import framework.util.DateUtils;

@SuppressWarnings({"unchecked","rawtypes"})
public class LogAction  extends FenyeBase{
	private final static Logger log = Logger.getLogger(LogAction.class);
	private static String logDir = "";
	
	private String logName;
	private String logContent;
	
	static{
		try {
			Properties properties = new Properties();
			InputStream is = LogAction.class.getResourceAsStream("/log4j.properties");
			properties.load(is);
			is.close();
			Enumeration enmu = properties.keys();
			while(enmu.hasMoreElements()){
				String key = String.valueOf(enmu.nextElement());
				if(key.indexOf(".File")>-1){
					File file = new File(properties.getProperty(key));
					logDir = file.getParent();
				}
			}
		} catch (Exception e) {
			log.error("读取日志文件目录异常",e);
		}
	}
	
	public String list(){
		File file = new File(logDir);
		File[] files = file.listFiles(MyFileFilter.filter);
		if(files==null){
			return "success";
		}
		setTotal(files.length);
		ArrayList data = new ArrayList(files.length);
		for(File f : files ){
			HashMap map = new HashMap(3);
			map.put("name", f.getName());
			map.put("time",  DateUtils.toDatetime(f.lastModified()) );
			map.put("size", f.length());
			data.add(map);
		}
		setDataRows(data);
		return "success";
	}
	
	public String query(){
		File file = new File(logDir+"/"+logName);
		try {
			ServletOutputStream os = RequestHelper.getResponse().getOutputStream();
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String tmp = null;
			while((tmp=br.readLine())!=null){
				os.write(tmp.getBytes());
			}
			os.flush();
			br.close();
			isr.close();
			fis.close();
		} catch (Exception e) {
			log.error("异常", e);
		}
		return null;
	}
	
	public String download(){
		return null;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public static String getLogDir() {
		return logDir;
	}	
	
	
}

class MyFileFilter implements FileFilter{
	public final static MyFileFilter filter = new MyFileFilter();
	public boolean accept(File file) {
		if(file.isFile() && !file.isHidden()){
			return file.getName().indexOf(".log")>-1;
		}
		return false;
	}
	
}
