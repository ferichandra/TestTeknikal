package com.democoding.accounts.Controller;

import com.democoding.accounts.Dto.AnyPaginationDto;
import com.democoding.accounts.Dto.EmployeeDto;
import com.democoding.accounts.Repository.EmployeeRepository;
import com.democoding.accounts.Repository.RoleRepository;
import com.democoding.accounts.Service.EmployeeService;
import com.democoding.accounts.Service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@SpringBootTest
class EmployeeControllerTest {
/*    @Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;
    @Autowired
    private MockMvc mockMvc;*/
    @Test
    void allEmployee() {
        // Define test data
/*        int pageNo = 0;
        int pageSize = 10;
        String name = "";

        // Mock the service method
        when(employeeService.paginationEmployee(eq(pageNo), eq(pageSize), eq(name)))
                .thenReturn(new AnyPaginationDto());

        // Perform the request and verify the response
        mockMvc.perform(get("/employee")
                        .param("pageNo", String.valueOf(pageNo))
                        .param("pageSize", String.valueOf(pageSize))
                        .param("name", name))
                .andExpect(status().isOk());*/
    }

    @Test
    void addEmployee() {
    }

    @Test
    void testAddEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void reportEmployee() {
    }

    @Test
    void testReportEmployee() {
    }
}