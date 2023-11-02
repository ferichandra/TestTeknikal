package com.democoding.accounts.Service;

import com.democoding.accounts.Dto.AnyPaginationDto;
import com.democoding.accounts.Dto.EmployeeDto;
import com.democoding.accounts.Dto.EmployeeRequestDto;
import com.democoding.accounts.Dto.MessageDto;
import com.democoding.accounts.Entity.Employee;
import com.democoding.accounts.Exception.ResourceNotAcceptableException;
import com.democoding.accounts.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public AnyPaginationDto paginationEmployee(int pageNo, int pageSize){
        AnyPaginationDto result = new AnyPaginationDto();
        Pageable page = PageRequest.of(pageNo,pageSize);
        Page<Employee>employeePage = employeeRepository.findAll(page);
        List<EmployeeDto>employeeDtoList = new ArrayList<>();
        for(Employee employee : employeePage){
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setAge(employee.getAge());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setAddress(employee.getAddress());
            employeeDtoList.add(employeeDto);
        }
        result.setData(employeeDtoList);
        result.setCurrentPage(employeePage.getNumber());
        result.setTotalRecord(employeePage.getTotalElements());
        result.setTotalPage(employeePage.getTotalPages());

        return result;
    }

    public MessageDto addEmployee(EmployeeRequestDto request){
        MessageDto messageDto = new MessageDto();
        try{
            Employee employee = new Employee();
            employee.setName(request.getName());
            employee.setAge(request.getAge());
            employee.setAddress(request.getAddress());
            employee.setEmail(request.getEmail());
            employee.setCreatedAt(new Date());
            employeeRepository.save(employee);
            messageDto.setMessage("Success Add Employee");
        }catch (Exception e){
            e.printStackTrace();
            throw new ResourceNotAcceptableException("Failed Add Employee");
        }
        return messageDto;
    }

    public MessageDto updateEmployee(EmployeeRequestDto request,Long id){
        MessageDto messageDto = new MessageDto();
        try{
            Employee employee = employeeRepository.findEmployeeByIdAndDeleteAtIsNull(id);
            employee.setName(request.getName());
            employee.setAge(request.getAge());
            employee.setAddress(request.getAddress());
            employee.setEmail(request.getEmail());
            employee.setUpdateAt(new Date());
            employeeRepository.save(employee);
            messageDto.setMessage("Success Update Employee");
        }catch (Exception e){
            throw new ResourceNotAcceptableException("Failed Update Employee");
        }
        return messageDto;
    }

    public MessageDto deleteEmployee(Long id){
        MessageDto messageDto = new MessageDto();
        try{
            Employee employee = employeeRepository.findEmployeeByIdAndDeleteAtIsNull(id);
            employee.setDeleteAt(new Date());
            employeeRepository.save(employee);
            messageDto.setMessage("Success Update Employee");
        }catch (Exception e){
            throw new ResourceNotAcceptableException("Failed Update Employee");
        }
        return messageDto;
    }
}
