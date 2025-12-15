package app.training.dia.service;

import app.training.dia.model.JobModel;
import app.training.dia.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public JobModel addJob(String jobName, String jobDesc, int jobSalary){
        JobModel newJob = new JobModel();
        newJob.setJobName(jobName);
        newJob.setJobDesc(jobDesc);
        newJob.setJobSalary(jobSalary);
        return jobRepository.save(newJob);
    }

    public JobModel addJob(JobModel jobModel){
        return jobRepository.save(jobModel);
    }

    public void addJobs(List<JobModel> jobModels){
        jobRepository.saveAll(jobModels);
    }

    public JobModel getDetailJob(int jobId){
        Optional<JobModel> jobOpt = jobRepository.findById(jobId);
        if(jobOpt.isPresent()){
            return jobOpt.get();
        }else{
            return null;
        }
    }

    public List<JobModel> getAllJobs(){
        return jobRepository.findAll();
    }
}
