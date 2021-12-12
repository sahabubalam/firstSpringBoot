package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepo er;
    @RequestMapping("/createEmployee")
    public String CreateEmployee(Employee employee)
    {
        return "AddEmployee.html";
    }
    @RequestMapping("/employeeList")
    public String EmployeeList()
    {
        return "EmployeeList.html";
    }
    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "addEmployee.html";
        }
        er.save(employee);
        return "redirect:/employeeList";
    }
    @GetMapping("/employeeList")
    public String getEmployee(Model model)
    {
        List<Employee> employees=er.findAll();
        model.addAttribute("employees", employees);
        return "EmployeeList.html";
    }
    @GetMapping("/deleteEmp/{id}")
    public String deleteEmployee(@PathVariable("id") int id)
    {
            er.deleteById(id);
        return "redirect:/employeeList";
    }
    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable("id") int id,Model model)
    {
        Employee employee= er.getById(id);
        model.addAttribute("employee",employee);

        return "employeeEdit.html";
    }
    @PostMapping("/updateEmployee/{id}")
    public String updateEmployee(@Valid @ModelAttribute Employee employee,BindingResult res, @PathVariable Integer id)
    {

        Employee emp= er.getById(id);
        if (res.hasErrors()) {

            System.out.println("error");
            return "employeeEdit.html";
        }
        else
        {

            emp.setFirst_name(employee.getFirst_name());
            emp.setLast_name(employee.getLast_name());
            emp.setFirst_name(employee.getFirst_name());
            emp.setFirst_name(employee.getFirst_name());

            er.save(emp);

            return "redirect:/employeeList";
        }

    }
}
