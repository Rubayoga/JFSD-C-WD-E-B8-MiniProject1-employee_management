package com.employee.employee_management.service;

import com.employee.employee_management.entity.EmployeeEntity;
import com.employee.employee_management.model.EmployeeDTO;
import com.employee.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for(EmployeeEntity employeeEntity : employeeEntityList) {
            employeeDTOList.add(employeeEntityToEmployeeDTO(employeeEntity));
        }
        return employeeDTOList;
    }

    public EmployeeDTO getEmployee(long id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);
        if(employeeEntityOptional.isPresent()) {
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            return employeeEntityToEmployeeDTO(employeeEntity);
        }
        return null;
    }

    public String addEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeDTOEmployeeEntity(employeeDTO));
        return "Employee added successfully";
    }

    public String updateEmployee(EmployeeDTO employeeDTO) {
        employeeRepository.save(employeeDTOEmployeeEntity(employeeDTO));
        return "Employee updated successfully";
    }

    public String deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    private EmployeeDTO employeeEntityToEmployeeDTO(EmployeeEntity employeeEntity) {
        return new EmployeeDTO(employeeEntity.getId(), employeeEntity.getFirstname(),
                employeeEntity.getLastname(), employeeEntity.getDepartment(), employeeEntity.getMail());
    }

    private EmployeeEntity employeeDTOEmployeeEntity(EmployeeDTO employeeDTO) {
        return new EmployeeEntity(employeeDTO.getId(), employeeDTO.getFirstname(),
                employeeDTO.getLastname(), employeeDTO.getDepartment(), employeeDTO.getEmail());
    }
}
