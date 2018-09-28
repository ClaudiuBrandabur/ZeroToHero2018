package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

@Table(name="jobs")
public class Job {
    @Id(name="job_id")
    private long id;

    @Column(name="job_title")
    private String jobName;

    @Column(name="min_salary")
    private int minSalary;

    @Column(name="max_salary")
    private int maxSalary;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Job))
            return false;

        Job job = (Job) obj;

        if(id != job.id || jobName != job.jobName || minSalary != job.minSalary || maxSalary != job.maxSalary){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String job = new String();
        job = "ID: " + this.id + "\n Job Name: " + this.jobName + "\n Min Salary:" + this.minSalary + "\n Max Salary: " + this.maxSalary;
        return job;
    }
}