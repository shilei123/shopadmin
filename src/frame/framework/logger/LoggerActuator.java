package framework.logger;

import org.apache.log4j.Logger;

import framework.context.ServiceLocator;
import framework.db.pojo.TAuditLog;
import framework.service.IAuditLogService;

/**
 * 日志入库线程.
 */
public class LoggerActuator implements Runnable {
    private static TAuditLog message;
    private static IAuditLogService loggerService;
    private static Logger logger = Logger.getLogger(LoggerActuator.class);
    private volatile static LoggerActuator loggerActuator;
    
    static {
        if (null == loggerService) {
            synchronized (LoggerActuator.class) {
                loggerService = (IAuditLogService) ServiceLocator.getInstance().getService("auditLogService");
            }
        }
    }

    public static LoggerActuator getInstance(TAuditLog _message) {
		if (null == loggerActuator) {
			synchronized (LoggerActuator.class) {
				if (null == loggerActuator) {
					loggerActuator = new LoggerActuator(_message);
				}
			}
		}
		return loggerActuator;
	}
    
    /**
     * 构造函数.
     */
    public LoggerActuator(TAuditLog _message) {
        this.message = _message;
    }

    /**
     * <p/>
     * 同步线程锁写日志信息.
     *
     * @return void author: wuchengqiang
     */
    private static synchronized void writeLog() {
        try {
            loggerService.addAuditLog(message);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void run() {
        writeLog();
    }
}