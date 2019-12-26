package quick.boot.quartz.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import quick.boot.quartz.entity.AppQuartz;
import quick.boot.quartz.job.JobOne;

/**
 * 初始化一个测试Demo任务
 * 创建者 科帮网
 * 创建时间	2018年4月3日
 */
@Component
public class TaskRunner implements ApplicationRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);

    @Autowired
    private Scheduler scheduler;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void run(ApplicationArguments var) throws Exception {
        LOGGER.info("初始化测试任务");
        AppQuartz quartz = new AppQuartz();
        quartz.setJobName("test01");
        quartz.setJobGroup("test");
        quartz.setCronExpression("0/1 * * * * ?");
        //构建job信息
        JobDetail job = JobBuilder.newJob(JobOne.class).withIdentity(quartz.getJobName(),
                quartz.getJobGroup()).build();
        //添加JobDataMap数据
        job.getJobDataMap().put("itstyle", "科帮网欢迎你");
        job.getJobDataMap().put("blog", "https://blog.52itstyle.vip");
        job.getJobDataMap().put("data", new String[]{"张三", "李四"});
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
    }

}