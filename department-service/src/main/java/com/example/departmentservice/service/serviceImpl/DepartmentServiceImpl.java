package com.example.departmentservice.service.serviceImpl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepo;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepo departmentRepo;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
     Department department = new Department(
             departmentDto.getId(),
             departmentDto.getDepartmentName(),
             departmentDto.getDepartmentDescription(),
             departmentDto.getDepartmentCode()
     );

    Department saveDepartment = departmentRepo.save(department);

     DepartmentDto saveDepartmentDto = new DepartmentDto(
             department.getId(),
             department.getDepartmentName(),
             department.getDepartmentDescription(),
             department.getDepartmentCode()
     );

        return saveDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByDepartmentCode(String departmentCode) {
       Department department = departmentRepo.findDepartmentByDepartmentCode(departmentCode);

       DepartmentDto departmentDto = new DepartmentDto(
               department.getId(),
               department.getDepartmentName(),
               department.getDepartmentDescription(),
               department.getDepartmentCode()
       );
        return departmentDto;
    }
}
