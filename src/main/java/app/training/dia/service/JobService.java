package app.training.dia.service;

import app.training.dia.dto.JobDto;
import app.training.dia.model.CustomJobModel;
import app.training.dia.model.JobModel;
import app.training.dia.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    private JobRepository jobRepository;
    private FileService fileService;

    @Autowired
    public JobService(
            JobRepository jobRepository,
            FileService fileService){
        this.jobRepository = jobRepository;
        this.fileService = fileService;
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

    public JobModel getJobByName(String jobName){
        return jobRepository.findByJobName(jobName)
                .orElse(null);
    }

    public List<JobModel> getJobByDesc(String description){
        return jobRepository.selectJobByDesc(description);
    }

    public List<JobModel> filterJobBySalary(int salary){
        return jobRepository.filterJobBySalary(salary);
    }

    public List<CustomJobModel> getCustomJob(String name){
        return jobRepository.getCustomJob(name);
    }

    public JobModel updateJob(JobModel jobModel) {
        Optional<JobModel> existingJobOpt =
                jobRepository.findById(jobModel.getJobId());
        if(existingJobOpt.isEmpty()){
            return null;
        }
        return jobRepository.save(jobModel);
    }

    public JobModel updateJob(int jobId, int jobSalary){
        Optional<JobModel> existingJobOpt = jobRepository.findById(jobId);
        if(existingJobOpt.isEmpty()){
            return null;
        }
        JobModel existingJob = existingJobOpt.get();
        existingJob.setJobSalary(jobSalary);
        return jobRepository.save(existingJob);
    }

    public void deleteJob(int jobId){
        Optional<JobModel> existingJobOpt = jobRepository.findById(jobId);
        if(existingJobOpt.isPresent()){
            jobRepository.deleteById(jobId); // delete by id
//            jobRepository.delete(existingJobOpt.get()); //delete by entity/model
        }
    }

    public boolean uploadFile(MultipartFile file){
        return fileService.saveFile(file);
    }

    public JobDto getDetailJobDto(int jobId){
        Optional<JobModel> jobOpt = jobRepository.findById(jobId);
        if(jobOpt.isPresent()){
            return convertDto(jobOpt.get());
        }else{
            return null;
        }
    }

    public List<JobDto> getAllJobsDto(){
        return jobRepository.findAll()
                .stream().map(this::convertDto).toList();
    }

    private JobDto convertDto(JobModel model){
        JobDto dto = new JobDto();
        dto.setJobId(model.getJobId());
        dto.setJobName(model.getJobName());
        dto.setJobSalary(model.getJobSalary());
        return dto;
    }

}
