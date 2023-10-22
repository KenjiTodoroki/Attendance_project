package com.example.demo.attendance.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.attendance.data.Clock;
import com.example.demo.attendance.data.Employees;
import com.example.demo.attendance.repository.AttendanceAPIRepository;

@Service
public class AttendanceAPIService {

	private final AttendanceAPIRepository attendanceAPIRepository;

	public AttendanceAPIService(AttendanceAPIRepository attendanceAPIRepository) {
		this.attendanceAPIRepository = attendanceAPIRepository;
	}

	public List<Employees> getEmployeesList() throws IOException {
		Employees[] employeesList = attendanceAPIRepository.getEmployeesList();

		return Arrays.asList(employeesList);
	}
	
	public List<Employees> getEmployees(int index) throws IOException {
		Employees[] employees = attendanceAPIRepository.getEmployees(index);
		
		return Arrays.asList(employees);
	}
	
	public List<Clock> getClockList() throws IOException {
		Clock[] clockList = attendanceAPIRepository.getClockList();

		return Arrays.asList(clockList);
	}
	
	public List<Clock> getClock(int index) throws IOException {
		Clock[] clock = attendanceAPIRepository.getClock(index);
		
		return Arrays.asList(clock);
	}
}