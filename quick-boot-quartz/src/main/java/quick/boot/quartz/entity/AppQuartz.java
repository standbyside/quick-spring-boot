package quick.boot.quartz.entity;

import lombok.Data;

@Data
public class AppQuartz {

    /**
     * 主键.
     */
    private Integer quartzId;
    /**
     * 任务名称.
     */
    private String jobName;
    /**
     * 任务分组.
     */
    private String jobGroup;
    /**
     * 任务开始时间.
     */
    private String startTime;
    /**
     * corn表达式.
     */
    private String cronExpression;
    /**
     * 需要传递的参数.
     */
    private String invokeParam;
}
