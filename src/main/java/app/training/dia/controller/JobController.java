package app.training.dia.controller;

import app.training.dia.model.JobModel;
import app.training.dia.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public JobModel addJob(@RequestParam("jobName") String jobName,
                           @RequestParam("jobDesc") String jobDesc,
                           @RequestParam("jobSalary") int jobSalary){
        return jobService.addJob(jobName, jobDesc, jobSalary);
    }

    @PostMapping("/body")
    public JobModel addJob(@RequestBody JobModel jobModel){
        return jobService.addJob(jobModel);
    }

    @PostMapping("/list")
    public boolean addJob(@RequestBody List<JobModel> jobModels){
        jobService.addJobs(jobModels);
        return true;
    }

    @GetMapping("/{jobId}")
    public JobModel getDetailJob(@PathVariable("jobId") int jobId){
        return jobService.getDetailJob(jobId);
    }

    @GetMapping
    public List<JobModel> getAllJob(){
        return jobService.getAllJobs();
    }
}
