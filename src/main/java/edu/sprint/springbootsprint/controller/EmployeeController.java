package edu.sprint.springbootsprint.controller;

import edu.sprint.springbootsprint.model.Application;
import edu.sprint.springbootsprint.model.Employee;
import edu.sprint.springbootsprint.service.EmployeeService;
import edu.sprint.springbootsprint.service.create.CreateEmployee;
import edu.sprint.springbootsprint.service.init.InitEmployeeWithoutApplicationService;
import edu.sprint.springbootsprint.service.init.InitEmployeeWithApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/")
    public List<Employee> getAllEmployee() {
        return EmployeeService.employeeDatabase;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return EmployeeService.getEmployee(id);
    }
    @PostMapping("/")
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        return EmployeeService.addEmployee(employee);
    }

    @PutMapping("/")
    public List<Employee> updateEmployee(@RequestBody Employee employee) {
        return EmployeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteEmployee(@PathVariable int id) {
        return EmployeeService.deleteEmployee(id);
    }

//    @GetMapping("/initWithoutApplication")
//    public List<Employee> initEmployeeWithoutApplication() {
//        return InitEmployeeWithoutApplicationService.init();
//    }
//
//    @GetMapping("/initWithApplication")
//    public Map<Employee, List<Application>> initEmployeeWithApplication() {
//        return InitEmployeeWithApplicationService.init();
//    }
}
