package com.career.CareerSync.Controllers;

import com.career.CareerSync.Models.Job;
import com.career.CareerSync.Models.JobType;
import com.career.CareerSync.Services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/job")
public class JobController {

    private final JobServices js;

    @Autowired
    public JobController(JobServices js) {
        this.js = js;
    }

    @PostMapping("/create-job")
    public ResponseEntity<Job> create(@RequestBody Job job){

        Job createdJob = js.createJob(job);

        return ResponseEntity.ok(createdJob);
    }

    @GetMapping("/view-all")
    public String getAllJobs(Model model) {

        List<Job> jobs = js.findAllJobs();

        List<String> locations = new ArrayList<>();

        for (Job job : jobs) {

            if (job.getLocation() != null && !locations.contains(job.getLocation())) {
                locations.add(job.getLocation());
            }
        }

        JobType[] jobTypes = JobType.values();

        model.addAttribute("jobs", jobs);
        model.addAttribute("locations", locations);
        model.addAttribute("jobTypes", jobTypes);

        return "jobs";
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Job> editJob(@PathVariable Long id, @RequestBody Job job){

        Job updatedJob = js.updateJob(id, job);

        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {

        js.deleteJob(jobId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view-job/{id}")
    public String viewJob(@PathVariable Long id, Model model){

        Job job = js.findById(id)
                .orElseThrow(() -> new RuntimeException("The job was not found"));

        model.addAttribute("job", job);

        return "view-job";
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJob(@RequestParam String title) {

        Optional<Job> jobOptional = js.findByTitle(title);

        if(jobOptional.isPresent()){
            return ResponseEntity.ok(List.of(jobOptional.get()));
        }

        return ResponseEntity.notFound().build();
    }
}