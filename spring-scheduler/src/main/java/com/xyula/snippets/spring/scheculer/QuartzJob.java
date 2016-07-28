package com.xyula.snippets.spring.scheculer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Laijie on 2016/7/28.
 */
public class QuartzJob extends QuartzJobBean {
    private MyTask myTask;

    public void setMyTask(MyTask task){
        myTask = task;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        myTask.sayHello();
    }
}
