package com.example.employeeservice.service.serviceImpl;

import com.example.employeeservice.dto.ApiResponceDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import com.example.employeeservice.service.ApiClient;
import com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo;
    private ApiClient apiClient;
//    private WebClient webClient;
//    private RestTemplate restTemplate;



    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee savedEmployee = employeeRepo.save(employee);

        EmployeeDto savedEmployeeDto =new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()
        );

        return savedEmployeeDto;
    }

    @Override
    public ApiResponceDto getEmployeeById(Long id) {
        Employee employee = employeeRepo.findEmployeeById(id);

        //Communication with Rest Template (includes main method Rest Template Bean)
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/department/" + employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();


        //communication with web client
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/department/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();



        //communication with feign client
        DepartmentDto departmentDto = apiClient.getDepartmentByFeignClient(employee.getDepartmentCode());

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        ApiResponceDto apiResponceDto = new ApiResponceDto(
                employeeDto,departmentDto
        );
        return apiResponceDto;
    }
}
