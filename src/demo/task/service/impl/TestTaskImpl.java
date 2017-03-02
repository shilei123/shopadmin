package demo.task.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import demo.task.service.ITestTask;

/**
 * @author yangchaowen
 * 2016年12月29日
 * 定时任务实例
 */
@Component
public class TestTaskImpl implements ITestTask {
	
	//每十秒执行一次
	@Scheduled(cron = " 0/10 * * * * ? ")
	public void test() {
		//System.out.println("test");
	}
}
