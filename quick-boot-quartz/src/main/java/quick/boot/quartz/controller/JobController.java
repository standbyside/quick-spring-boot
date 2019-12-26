package quick.boot.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quick.boot.quartz.entity.AppQuartz;
import quick.boot.quartz.service.JobUtils;

@RestController
public class JobController {

    @Autowired
    private JobUtils jobUtil;

    @GetMapping("/test")
    public String test() {
        return "test quartz";
    }

    /**
     * 添加一个job.
     *
     * @param appQuartz
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/addJob")
    public String addjob(@RequestBody AppQuartz appQuartz) throws Exception {
        return jobUtil.addJob(appQuartz);
    }

    /*

    *//**
     * 暂停job.
     *
     * @param quartzIds
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/pauseJob")
    public String pausejob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if (quartzIds.length > 0) {
            for (Integer quartzId : quartzIds) {
                jobUtil.pauseJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return "success pauseJob";
        } else {
            return "fail pauseJob";
        }
    }

    *//**
     * 恢复job.
     *
     * @param quartzIds
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/resumeJob")
    public String resumejob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        if (quartzIds.length > 0) {
            for (Integer quartzId : quartzIds) {
                appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
                jobUtil.resumeJob(appQuartz.getJobName(), appQuartz.getJobGroup());
            }
            return "success resumeJob";
        } else {
            return "fail resumeJob";
        }
    }

    *//**
     * 删除job.
     *
     * @param quartzIds
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/deletJob")
    public String deletjob(@RequestBody Integer[] quartzIds) throws Exception {
        AppQuartz appQuartz = null;
        for (Integer quartzId : quartzIds) {
            appQuartz = appQuartzService.selectAppQuartzByIdSer(quartzId).get(0);
            String ret = jobUtil.deleteJob(appQuartz);
            if ("success".equals(ret)) {
                appQuartzService.deleteAppQuartzByIdSer(quartzId);
            }
        }
        return "success deleteJob";
    }

    *//**
     * 修改.
     *
     * @param appQuartz
     * @return
     * @throws Exception
     *//*
    @PostMapping(value = "/updateJob")
    public String modifyJob(@RequestBody AppQuartz appQuartz) throws Exception {
        String ret = jobUtil.modifyJob(appQuartz);
        if ("success".equals(ret)) {
            appQuartzService.updateAppQuartzSer(appQuartz);
            return "success updateJob";
        } else {
            return "fail updateJob";
        }
    }*/

    /**
     * 暂停所有.
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/pauseAll")
    public String pauseAllJob() throws Exception {
        jobUtil.pauseAllJob();
        return "success pauseAll";
    }

    /**
     * 恢复所有.
     *
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/repauseAll")
    public String repauseAllJob() throws Exception {
        jobUtil.resumeAllJob();
        return "success repauseAll";
    }
}