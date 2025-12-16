package app.training.dia.controller;

import app.training.dia.dto.JobDto;
import app.training.dia.model.CustomJobModel;
import app.training.dia.model.JobModel;
import app.training.dia.response.JobDetailResponse;
import app.training.dia.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/by-name")
    public JobModel getJobByName(@RequestParam("jobName") String jobName){
        return jobService.getJobByName(jobName);
    }

    @GetMapping("/desc")
    public List<JobModel> getJobByDesc(@RequestParam("desc") String desc){
        return jobService.getJobByDesc(desc);
    }

    @GetMapping("/salary")
    public List<JobModel> filterSalary(@RequestParam("salary") int salary){
        return jobService.filterJobBySalary(salary);
    }

    @GetMapping("/custom")
    public List<CustomJobModel> getCustomJob(
            @RequestParam("name") String jobName){
        return jobService.getCustomJob(jobName);
    }

    @PutMapping()
    public JobModel updateJob(@RequestBody JobModel jobModel){
        return jobService.updateJob(jobModel);
    }

    @PatchMapping("/{jobId}")
    public JobModel updateJob(@PathVariable("jobId") int jobId,
                              @RequestParam("jobSalary") int jobSalary){
        return jobService.updateJob(jobId, jobSalary);
    }

    @DeleteMapping("/{jobId}")
    public boolean deleteJob(@PathVariable("jobId") int jobId){
        jobService.deleteJob(jobId);
        return true;
    }

    @PostMapping("/upload")
    public boolean uploadFile(MultipartFile file){
        return jobService.uploadFile(file);
    }

    @GetMapping("/dto/{jobId}")
    public ResponseEntity<JobDetailResponse> getDetailJobDto(
            @PathVariable("jobId") int jobId){
        JobDetailResponse response = new JobDetailResponse();
        JobDto detailJobDto = jobService.getDetailJobDto(jobId);
        if(detailJobDto == null){
            response.setSuccess(false);
            response.setMessage("Data not found");
            response.setErrorCode("01");
        }else {
            response.setSuccess(true);
            response.setMessage("OK");
            response.setErrorCode("0");
            response.setData(detailJobDto);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dto/all")
    public List<JobDto> getAllJobDto(){
        return jobService.getAllJobsDto();
    }

}
