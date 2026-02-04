package com.career.CareerSync.Services;

import com.career.CareerSync.Models.Job;
import com.career.CareerSync.Models.MyUser;
import com.career.CareerSync.Repositories.AdminRepository;
import com.career.CareerSync.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServices {
    private JobRepository jr;
    private AdminRepository ar;

    @Autowired
    public JobServices(JobRepository jr) {
        this.jr = jr;
    }

    public Job updateJob(Long id,Job job){

        Job existingJob=jr.findById(id).orElseThrow(()->new RuntimeException("The user you want to edit does not exist."));

        existingJob.setJobDescription(job.getJobDescription());
        existingJob.setJobReq(job.getJobReq());
        existingJob.setCompanyName(job.getCompanyName());
        existingJob.setClosingDate(job.getClosingDate());
        existingJob.setCompanyLogo(job.getCompanyLogo());
        existingJob.setTitle(job.getTitle());

        return jr.save(existingJob);
    }

    public Job createJob(String title,String companyName,String companyLogo,String description,String jobReq){
        Job newJob=new Job();

        newJob.setTitle(title);
        newJob.setCompanyName(companyName);
        newJob.setCompanyLogo(companyLogo);
        newJob.setJobDescription(description);
        newJob.setJobReq(jobReq);

        return jr.save(newJob);
    }

    public void deleteJob(Long id,Long adminID) {
        MyUser admin=ar.findById(adminID).orElseThrow(()->new RuntimeException("The admin with that id :"+adminID+" does not exist!"));
        Job job = jr.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        jr.delete(job);
    }

    public List<Job> findAllJobs(){
        return jr.findAll();
    }

    public Optional<Job> findByRefNo(String refNo){

        return Optional.ofNullable(jr.findByRefNo(refNo));
    }
    public Optional<Job> findByTitle(String title){

        return Optional.ofNullable(jr.findByTitle(title));
    }
}
