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
	public String getSignUp(Model model) throws IOException {

		return "employeesSignUp";
	}

	@GetMapping("/employeesDetail/{employeeId}")
	public String getEmployeeDetail(@PathVariable int employeeId, Model model)
			throws IOException {

		List<Employees> employees = attendanceAPIService.getEmployees(employeeId);
		List<Clock> clock = attendanceAPIService.getClock(employeeId);

		model.addAttribute("employees", employees);
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
	public String postClock(@RequestParam String employeeId, @RequestParam String attendButton, Model model)
			throws IOException {

		attendanceAPIService.postClock(employeeId, attendButton);

		return "redirect:/employeesList";
	}
}
