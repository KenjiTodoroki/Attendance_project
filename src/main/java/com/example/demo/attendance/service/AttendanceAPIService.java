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

	public List<Employees> getEmployees(int index) throws IOException {
		Employees[] employees = attendanceAPIRepository.getEmployees(index);

		return Arrays.asList(employees);
	}

	public List<Clock> getClock(int index) throws IOException {
		Clock[] clock = attendanceAPIRepository.getClock(index);

		return Arrays.asList(clock);
	}

	public void postPerson(String name, String hometown, String joiningMonth) throws IOException {

		attendanceAPIRepository.postPerson(name, hometown, joiningMonth);
	}

	public void postClock(String employeeId, String attendButton) throws IOException {

		Date now = new Date();

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		String attendDate = simpleDate.format(now);

		String clockIn = "";
		String breakStart = "";
		String breakEnd = "";
		String clockOut = "";

		if (attendButton == "clockIn") {

			clockIn = attendDate;

		} else if (attendButton == "breakStart") {

			breakStart = attendDate;

		} else if (attendButton == "breakEnd") {

			breakEnd = attendDate;

		} else if (attendButton == "clockOut") {

			clockOut = attendDate;

		}

		attendanceAPIRepository.postClock(employeeId, clockIn, breakStart, breakEnd, clockOut);
	}
}