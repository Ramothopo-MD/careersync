package com.career.CareerSync.Controllers;

import com.career.CareerSync.Models.Job;
import com.career.CareerSync.Models.JobType;
import com.career.CareerSync.Repositories.AdminRepository;
import com.career.CareerSync.Services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job")
public class JobController {
   private JobServices js;
   @Autowired
   private AdminRepository adminRepo;

    public JobController(JobServices js, AdminRepository adminRepo) {
        this.js = js;
        this.adminRepo = adminRepo;
    }


    @PostMapping("/create-job")
    public Job create(@RequestBody Job job, @RequestParam Long adminId){

        adminRepo.findById(adminId).orElseThrow(()->new RuntimeException("The admin " + adminId + " does not exist."));


       return js.createJob(job.getTitle(),
                     job.getCompanyName(),
                     job.getCompanyLogo(),
                     job.getJobDescription(),
                     job.getJobReq());

    }
    @GetMapping("/view-all")
    public String getAllJobs(Model model) {

        List<Job> jobs = js.findAllJobs();

        List<String> companies = new ArrayList<>();
        List<String> locations = new ArrayList<>();

        for (Job job : jobs) {

            if (job.getCompanyName() != null && !companies.contains(job.getCompanyName())) {
                companies.add(job.getCompanyName());
            }

            if (job.getLocation() != null && !locations.contains(job.getLocation())) {
                locations.add(job.getLocation());
            }
        }

        // ENUM values
        JobType[] jobTypes = JobType.values();

        model.addAttribute("jobs", jobs);
        model.addAttribute("companies", companies);
        model.addAttribute("locations", locations);
        model.addAttribute("jobTypes", jobTypes);

        return "jobs";
    }




    @PutMapping("/edit/{id}")
    public ResponseEntity<Job> editJob(@PathVariable Long jobId, @RequestBody Job job){
        Job updatedJob=js.updateJob(jobId,job);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<?> deleteJob(
            @PathVariable Long jobId,
            @RequestParam Long adminId) {

        try {
            js.deleteJob(jobId, adminId);
            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (SecurityException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/view-job/{refNo}")
    public String viewJob(@PathVariable String refNo,Model model){
       Job job=js.findByRefNo(refNo).orElseThrow(()-> new RuntimeException("The job was not found"));
        model.addAttribute("job",job);

        return "view-job";
    }

    // Search a job by reference number
    @GetMapping("/search")
    public ResponseEntity<Job> searchJob(@RequestParam String refNo) {
        Optional<Job> jobOptional = js.findByRefNo(refNo);
        return jobOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

