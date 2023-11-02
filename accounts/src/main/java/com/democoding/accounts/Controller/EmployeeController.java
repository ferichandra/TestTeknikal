package com.democoding.accounts.Controller;

import com.democoding.accounts.Dto.AnyPaginationDto;
import com.democoding.accounts.Dto.EmployeeRequestDto;
import com.democoding.accounts.Dto.MessageDto;
import com.democoding.accounts.Entity.Employee;
import com.democoding.accounts.Repository.EmployeeRepository;
import com.democoding.accounts.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("Hello")
    public String sayHello() {
        return "Welcome To Learn Microservice";
    }

    @GetMapping("/employee")
    public AnyPaginationDto allEmployee(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "10") int pageSize) {
        return employeeService.paginationEmployee(pageNo, pageSize);
    }

    @GetMapping("/list-employee")
    public Iterable<Employee> allEmployeeList() {
        return employeeRepository.findAll();
    }


    @PostMapping("/addEmployee")
    public MessageDto addEmployee(@RequestBody EmployeeRequestDto request) {
        return employeeService.addEmployee(request);
    }

    @PutMapping("/updateEmployee/{id}")
    public MessageDto addEmployee(@RequestBody EmployeeRequestDto request, @PathVariable("id") Long id) {
        return employeeService.updateEmployee(request, id);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public MessageDto deleteEmployee(@PathVariable("id") Long id) {
        return employeeService.deleteEmployee(id);
    }
}
