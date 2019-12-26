package quick.boot.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class JobOne implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getTrigger().getJobDataMap();
        String invokeParam = (String) data.get("invokeParam");
        // 在这里实现业务逻辑
        System.out.println("job one execute, param = " + invokeParam);
    }
}