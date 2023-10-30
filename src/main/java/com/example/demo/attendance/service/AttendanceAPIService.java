package com.example.demo.attendance.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

	public List<Employees> getEmployees(int employeeId) throws IOException {
		Employees[] employees = attendanceAPIRepository.getEmployees(employeeId);

		return Arrays.asList(employees);
	}

	public List<Clock> getClock(int employeeId) throws IOException {
		Clock[] clock = attendanceAPIRepository.getClock(employeeId);

		return Arrays.asList(clock);
	}

	public void postPerson(String name, String hometown, String joiningMonth) throws IOException {

		attendanceAPIRepository.postPerson(name, hometown, joiningMonth);
	}

	public void postClock(String employeeId, String attendButton) throws IOException {

		Date now = new Date();

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = simpleDate.format(now);

		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";

		switch (attendButton) {
		case "clockIn":
			clockIn = currentTime;
			break;

		case "breakStart":
			breakStart = currentTime;
			break;

		case "breakEnd":
			breakEnd = currentTime;
			break;

		case "clockOut":
			clockOut = currentTime;
			break;
		}

		attendanceAPIRepository.postClock(employeeId, clockIn, breakStart, breakEnd, clockOut);
	}
}