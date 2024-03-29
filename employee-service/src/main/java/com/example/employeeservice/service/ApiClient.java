package com.example.employeeservice.service;

import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
public interface ApiClient {
    @GetMapping("api/department/{department-code}")
    DepartmentDto getDepartmentByFeignClient(@PathVariable(value = "department-code")String departmentCode);
}
