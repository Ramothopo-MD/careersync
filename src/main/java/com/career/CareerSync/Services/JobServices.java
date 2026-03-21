package com.career.CareerSync.Services;

import com.career.CareerSync.Models.Company;
import com.career.CareerSync.Models.Job;
import com.career.CareerSync.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class JobServices {

    private JobRepository jr;

    @Autowired
    public JobServices(JobRepository jr) {
        this.jr = jr;
    }

    public Job updateJob(Long id, Job job){

        Job existingJob = jr.findById(id)
                .orElseThrow(() -> new RuntimeException("The job you want to edit does not exist."));

        existingJob.setTitle(job.getTitle());
        existingJob.setJobCategory(job.getJobCategory());
        existingJob.setJobType(job.getJobType());
        existingJob.setJobDescription(job.getJobDescription());
        existingJob.setJobRequirements(job.getJobRequirements());
        existingJob.setJobQualifications(job.getJobQualifications());
        existingJob.setLocation(job.getLocation());
        existingJob.setClosingDate(job.getClosingDate());
        existingJob.setCompany(job.getCompany());

        return jr.save(existingJob);
    }

    public Job createJob(Job job){

        job.setPostedDate(new Timestamp(System.currentTimeMillis()));

        return jr.save(job);
    }

    public void deleteJob(Long id){

        Job job = jr.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        jr.delete(job);
    }

    public List<Job> findAllJobs(){
        return jr.findAll();
    }

    public Optional<Job> findById(Long id){
        return jr.findById(id);
    }

    public Optional<Job> findByTitle(String title){
        return jr.findByTitle(title);
    }
}