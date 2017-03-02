package framework.logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import framework.db.pojo.TAuditLog;

/**
 * 日志.
 */
public class AuditLogger {
	
	private static AuditLogger draftLogger;
	
	/**
	 * 线程池.
	 */
	private static final ExecutorService _threadPool = Executors.newCachedThreadPool();
	
	private AuditLogger() {
		
	}
	
	public static AuditLogger getLogger() {  
		if (draftLogger == null) {  
            synchronized (AuditLogger.class) {  
                if (draftLogger == null) {  
                	draftLogger = new AuditLogger();  
                }  
            }  
        }  
		return draftLogger;  
	}  
	
	/**
	 * 日志入库执行线程
	 * @param message
	 * 日志消息
	 */
	public void execute(TAuditLog message) {
		// 获取调用日志的类名
		String className = Thread.currentThread().getStackTrace()[3].getClassName();
		// 获得调用日志的方法名
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		String logSource = className + "::" + methodName;
		message.setLogsource(logSource);
		_threadPool.execute(new LoggerActuator(message));
	}
	
	public void info(TAuditLog message) {
		execute(message);
	}
	
	public void info(String loginName,String logDetail) {
		TAuditLog message = new TAuditLog(loginName,logDetail);
		execute(message);
	}
}
