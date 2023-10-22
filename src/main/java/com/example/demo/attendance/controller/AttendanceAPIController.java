package com.example.demo.attendance.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.attendance.data.Employees;
import com.example.demo.attendance.service.AttendanceAPIService;

import jakarta.servlet.ServletException;

@Controller
public class AttendanceAPIController {

	private final AttendanceAPIService attendanceAPIService;

	public AttendanceAPIController(AttendanceAPIService attendanceAPIService) {
		this.attendanceAPIService = attendanceAPIService;
	}

	@GetMapping("employeesList")
	public String getPeople(Model model) throws IOException {

		List<Employees> employees = attendanceAPIService.getEmployeesList();
		
		model.addAttribute("employees", employees);

		return "employeesList";
	}

	@GetMapping("employeesSignUp")
	public String doPost(Model model) throws IOException {

		return "employeesSignUp";
	}

	@GetMapping("employeesDetail/{EmployeeId}")
	public String getEmployeeDetail(@PathVariable int EmployeeId, Model model)
			throws ServletException, IOException {

		List<Employees> employeesList = attendanceAPIService.getEmployees(EmployeeId);

		model.addAttribute("employee", employeesList.get(0));

		return "employeesDetail";
	}
}