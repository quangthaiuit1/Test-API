package com.laptrinhmobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.laptrinhmobile.entities.Employee;
import com.laptrinhmobile.model.EmployeeModel;
import com.laptrinhmobile.utils.HttpUtil;
import com.mysql.cj.util.StringUtils;

@WebServlet(urlPatterns = { "/employee" })
public class TestAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Employee> employees = EmployeeModel.findAll();

//				ObjectMapper mapper = new ObjectMapper();
//				String jsonString = mapper.writeValueAsString(employees);
//				response.setContentType("application/json");

//				PrintWriter out = response.getWriter();
//				ObjectMapper objectMapper= new ObjectMapper();
//				String jsonString = objectMapper.writeValueAsString(employees);
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("application/json; charset=UTF-8");
			response.setContentType("application/json; charset=utf-8");
//			String json = new Gson().toJson(employees);
			JSONObject obj = new JSONObject();

			obj.put("data", employees);
			obj.put("status", "SUCCESS");
			obj.put("numpage", 1);
			obj.put("message", "Successful!");
			System.out.println(obj);

			response.getWriter().write(obj.toString(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("application/json; charset=utf-8");
		response.setContentType("application/json; charset=utf-8");
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		// Read object from json
		Employee employee = HttpUtil.of(request.getReader()).toEntity(Employee.class);
		List<Employee> employees = new ArrayList<>();
		if(!employee.getName().equals("")) {
			employees = EmployeeModel.findByName(employee.getName());
		}
		if(!employees.isEmpty()) {
			JSONObject obj = new JSONObject();

			obj.put("data", employees);
			obj.put("status", "SUCCESS");
			obj.put("numpage", 1);
			obj.put("message", "Successful!");

			response.getWriter().write(obj.toString(4));
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Employee employee = HttpUtil.of(req.getReader()).toEntity(Employee.class);
//		CategoryService.updateOne(employee);
		objectMapper.writeValue(resp.getOutputStream(), employee);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Employee employee = HttpUtil.of(req.getReader()).toEntity(Employee.class);
//		CategoryService.delete(category.getIds());
		objectMapper.writeValue(resp.getOutputStream(), employee);
	}
}
