package com.example.demo.attendance.repository;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

	public Employees[] getEmployees(int employeeId) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee?id="
				+ String.valueOf(employeeId);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Employees[] employees = mapper.readValue(json, Employees[].class);

		return employees;
	}

	public Clock[] getClock(int employeeId) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock?employeeId="
				+ String.valueOf(employeeId);

		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);

		String json = response.getBody();

		ObjectMapper mapper = new ObjectMapper();

		Clock[] clock = mapper.readValue(json, Clock[].class);

		return clock;
	}

	public void postPerson(String name, String hometown, String joiningMonth) throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/employee";

		String jsonData = "{"
				+ "\"body\": \"{"
				+ "\\\"name\\\":\\\"" + name + "\\\","
				+ "\\\"hometown\\\":\\\"" + hometown + "\\\","
				+ "\\\"joining_month\\\":\\\"" + joiningMonth + "\\\""
				+ "}\""
				+ "}";

		//リクエスト情報の作成
		RequestEntity<String> request = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON).body(jsonData);

		//リクエストの送信
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);
	}

	public void postClock(String employeeId, String clockIn, String breakStart, String breakEnd, String clockOut)
			throws IOException {

		String url = "https://jsn9xu2vsk.execute-api.ap-northeast-1.amazonaws.com/sample/attendanceandabsence/clock";

		String jsonData = "{"
				+ "\"body\": \"{"
				+ "\\\"employee_id\\\":\\\"" + employeeId + "\\\","
				+ "\\\"clock_in\\\":\\\"" + clockIn + "\\\","
				+ "\\\"break_start\\\":\\\"" + breakStart + "\\\","
				+ "\\\"break_end\\\":\\\"" + breakEnd + "\\\","
				+ "\\\"clock_out\\\":\\\"" + clockOut + "\\\""
				+ "}\""
				+ "}";

		//リクエスト情報の作成
		RequestEntity<String> request = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON).body(jsonData);

		//リクエストの送信
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.exchange(request, String.class);
	}
}