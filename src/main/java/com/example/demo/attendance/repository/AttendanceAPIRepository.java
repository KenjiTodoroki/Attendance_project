package com.example.demo.attendance.repository;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.attendance.data.Clock;
import com.example.demo.attendance.data.Employees;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AttendanceAPIRepository {

	public Employees[] getEmployeesList() throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employees[] employeesList = mapper.readValue(json, Employees[].class);

		return employeesList;
	}

	public Employees[] getEmployees(int index) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee?id="
				+ String.valueOf(index);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employees[] employees = mapper.readValue(json, Employees[].class);

		return employees;
	}

	public Clock[] getClockList() throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clockList = mapper.readValue(json, Clock[].class);

		return clockList;
	}

	public Clock[] getClock(int index) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId="
				+ String.valueOf(index);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clock = mapper.readValue(json, Clock[].class);

		return clock;
	}
}