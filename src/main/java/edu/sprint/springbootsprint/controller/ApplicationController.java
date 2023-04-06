package edu.sprint.springbootsprint.controller;

import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.service.ApplicationService;
import edu.sprint.springbootsprint.service.create.CreateApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    /*@GetMapping("/add/{employeeId}")
    public List<Application> addApplication(@PathVariable int employeeId) {
        return ApplicationService.addApplication(CreateApplication.createApplication(employeeId));
    }*/

    @GetMapping("/")
    public List<Application> getAllApplication() {
        return ApplicationService.applicationDatabase;
    }

    @GetMapping("/{id}")
    public Application getApplication(@PathVariable int id) {
        return ApplicationService.getApplication(id);
    }

    @GetMapping("/getForEmployee/{id}")
    public List<Application> getApplicationForEmployee(@PathVariable int id) {
        return ApplicationService.getApplicationForEmployee(id);
    }

//    @GetMapping("/update/{id}/{categoryId}")
//    public List<Application> updateApplication(@PathVariable int id, @PathVariable int categoryId) {
//        return ApplicationService.updateApplication(id, categoryId);
//    }

    @PostMapping("/")
    public int createApplication(@RequestBody Application application) {
        List<Application> applicationList = ApplicationService.addApplication(application);
        return applicationList.get(applicationList.size() - 1).getId();
    }

    @PutMapping("/")
    public List<Application> editApplication(@RequestBody Application application) {
        return ApplicationService.updateAppApplication(application);
    }

    @DeleteMapping("/{id}")
    public List<Application> deleteApplication(@PathVariable int id) {
        return ApplicationService.deleteApplication(id);
    }
}
