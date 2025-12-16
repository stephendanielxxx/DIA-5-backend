package app.training.dia.repository;

import app.training.dia.model.CustomJobModel;
import app.training.dia.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<JobModel, Integer> {

    Optional<JobModel> findByJobName(String jobName);

    @Query(value = "select * from tab_job " +
            "where job_desc like %:description%", nativeQuery = true)
    List<JobModel> selectJobByDesc(String description);

    @Query(value = "select j from JobModel j where j.jobSalary >= :minSalary")
    List<JobModel> filterJobBySalary(@Param("minSalary") int salary);

    @Query(value = "select tj.job_id as jobId, tj.job_name as jobName, " +
            "tj.job_salary as jobSalary, 'active' as jobStatus " +
            "from tab_job tj where job_name like %:name%", nativeQuery = true)
    List<CustomJobModel> getCustomJob(String name);
}
