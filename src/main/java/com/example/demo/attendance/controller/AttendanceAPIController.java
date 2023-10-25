package com.example.demo.attendance.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.attendance.data.Clock;
import com.example.demo.attendance.data.Employees;
import com.example.demo.attendance.service.AttendanceAPIService;

@Controller
public class AttendanceAPIController {

	private final AttendanceAPIService attendanceAPIService;

	public AttendanceAPIController(AttendanceAPIService attendanceAPIService) {
		this.attendanceAPIService = attendanceAPIService;
	}

	@GetMapping("/employeesList")
	public String getPeople(Model model) throws IOException {

		List<Employees> employees = attendanceAPIService.getEmployeesList();

		model.addAttribute("employees", employees);

		return "employeesList";
	}

	@GetMapping("/employeesSignUp")
	public String doPost(Model model) throws IOException {

		return "employeesSignUp";
	}

	@GetMapping("/employeesDetail/{EmployeeId}")
	public String getEmployeeDetail(@PathVariable int EmployeeId, Model model)
			throws IOException {

		List<Employees> employeesList = attendanceAPIService.getEmployees(EmployeeId);
		List<Clock> clock = attendanceAPIService.getClock(EmployeeId);

		model.addAttribute("employeeId", employeesList.get(0));
		model.addAttribute("clock", clock);

		return "employeesDetail";
	}

	@PostMapping("/employeesList")
	public String postPerson(@RequestParam String name, @RequestParam String hometown,
			@RequestParam String joiningMonth, Model model) throws IOException {
		
		attendanceAPIService.postPerson(name, hometown, joiningMonth);
		
		return "redirect:/employeesList";
	}

	@PostMapping("/employeesAttend")
	public String postClock(@RequestParam String employeeId, @RequestParam String clockIn, @RequestParam String breakStart,
			@RequestParam String breakEnd, @RequestParam String clockOut, Model model) throws IOException {
		
		attendanceAPIService.postClock(employeeId, clockIn, breakStart, breakEnd, clockOut);

		return "redirect:/employeesDetail";
	}
}
