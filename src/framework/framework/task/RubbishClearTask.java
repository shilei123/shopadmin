package framework.task;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import framework.db.DBUtil;
import framework.db.pojo.TPbUploadfile;
import framework.util.DateUtils;

public class RubbishClearTask {
	Logger log = Logger.getLogger(RubbishClearTask.class);
	Timer timer = new Timer();
	
	public void start(){
		//每天凌晨一点运行
		log.info("启动垃圾文件清理任务....");
		Date exeTime = new Date();
		if(exeTime.getHours()>1){
			exeTime.setDate(exeTime.getDate()+1);
			exeTime.setHours(1);
			exeTime.setMinutes(0);
			exeTime.setSeconds(0);
		}
		timer.schedule(new RubbishClearTimerTask(), exeTime, 24*60*60*1000);
		log.info("启动垃圾文件清理任务成功,首次执行任务时间:"+ DateUtils.toDatetime(exeTime) +"   执行频率：1天");
	}
	
}

/**
 * 垃圾文件清理定时任务
 * @author Administrator
 *
 */
class RubbishClearTimerTask extends TimerTask{
	Logger log = Logger.getLogger(RubbishClearTimerTask.class);
	@Override
	public void run() {
		try {
			log.info(DateUtils.toDatetime(new Date())+">开始清理系统垃圾文件....");
			List<TPbUploadfile> list = this.getRubbishFileList();
			for(TPbUploadfile pojo : list){
				deleteFile(pojo);
			}
			log.info(DateUtils.toDatetime(new Date())+">清理系统垃圾文件完毕,本次清理垃圾文件"+list.size()+"个");
		} catch (Exception e) {
			log.error("清理垃圾文件异常",e);
		}
	}
	
	/**
	 * 删除文件
	 * @param pojo
	 */
	public void deleteFile(TPbUploadfile pojo){
		DBUtil db = new DBUtil();
		try{
			db.delete(pojo);
			//db.commit();
			File file = new File(pojo.getFilePath());
			if(file.exists()){
				file.delete();
			}
		}catch(Exception e){
			log.error("删除垃圾文件异常",e);
		}
		//db.close();
	}
	
	public List<TPbUploadfile> getRubbishFileList(){
		DBUtil db = new DBUtil();
		List<TPbUploadfile> list = null;
		try{
			Date date = new Date();
			date.setDate(date.getDate()-2);//清理两天前的垃圾文件
			Map params = new HashMap(2);
			params.put("yxBj", "0");
			params.put("lrSj<to_date('"+DateUtils.toDatetime(date)+"','yyyy-mm-dd hh24:mi:ss')", null);
			list = db.queryByPojo(TPbUploadfile.class, params);
		}catch(Exception e){
			log.error("查询上传文件异常",e);
		}
		//db.close();
		return list;
	}
	
	
}