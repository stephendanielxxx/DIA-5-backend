package app.training.dia.repository;

import app.training.dia.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobModel, Integer> {
}
