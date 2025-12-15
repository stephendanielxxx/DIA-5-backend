package app.training.dia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tab_job")
@Setter
@Getter
public class JobModel {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "job_id") //nama column di table
    private int jobId;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "job_desc")
    private String jobDesc;
    @Column(name = "job_salary")
    private int jobSalary;
}
