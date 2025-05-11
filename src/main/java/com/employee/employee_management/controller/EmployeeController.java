package com.employee.employee_management.controller;

import com.employee.employee_management.model.EmployeeDTO;
import com.employee.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/")
    public String getAllEmployee(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "index";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        return "add";
    }

    @GetMapping(value = "/view/{id}")
    public String getEmployee(@PathVariable long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable long id, Model model) {
        model.addAttribute("employee",employeeService.getEmployee(id));
        return "edit";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.addEmployee(employeeDTO);
        return "redirect:/";
    }

    @GetMapping(value="/delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String updateEmployee(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO);
        return "redirect:/";
    }
}
