package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

import java.util.Objects;

public class Job {
    @Id(name = "JOB_ID")
    private Long id;
    @Column(name = "JOB_TITLE")
    private String job;
    @Column(name = "MIN_SALARY")
    private Long minSalary;
    @Column(name = "MAX_SALARY")
    private Long maxSalary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job1 = (Job) o;
        return Objects.equals(id, job1.id) &&
                Objects.equals(job, job1.job) &&
                Objects.equals(minSalary, job1.minSalary) &&
                Objects.equals(maxSalary, job1.maxSalary);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, job, minSalary, maxSalary);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }
}