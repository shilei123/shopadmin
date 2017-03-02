package framework.context;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * ClassName:ServiceLocator <br/>
 * Function: 手动获取spring bean. <br/>
 */
@Component
public class ServiceLocator implements BeanFactoryAware {
	private final static Logger log = LoggerFactory.getLogger(ServiceLocator.class);  
	
    private static BeanFactory beanFactory = null;
 
    private static ServiceLocator servlocator = null;
 
   /***
    * getInstance:(单例 获取实例). 
    */
    public static ServiceLocator getInstance() {
        if (servlocator == null)
              servlocator = (ServiceLocator) beanFactory.getBean("serviceLocator");
        return servlocator;
    }
 
    /***
     * getService:( 根据提供的bean名称得到相应的服务类     ). <br/>
     * @param servName bean名称     
     */
    public Object getService(String servName) {
        return beanFactory.getBean(servName);
    }
 
    /***
     * getService:(根据提供的bean名称得到对应于指定类型的服务类). <br/>
     * @param servName bean名称
     * @param clazz 返回的bean类型,若类型不匹配,将抛出异常
     */
	public static Object getService(String servName, Class<?> clazz) {
    	if(StringUtils.isBlank(servName) || null == clazz){
    		log.warn("获取bean失败：类名或类型为空！");
    		return null;
    	}
        return beanFactory.getBean(servName, clazz);
    }
	 
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        ServiceLocator.beanFactory = factory;
    }
 
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
