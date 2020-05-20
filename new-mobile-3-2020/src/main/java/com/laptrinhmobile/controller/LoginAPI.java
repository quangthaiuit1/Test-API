package com.laptrinhmobile.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.laptrinhmobile.entities.User;
import com.laptrinhmobile.model.UserModel;
import com.laptrinhmobile.utils.HttpUtil;

@WebServlet(urlPatterns = { "/login" })
public class LoginAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ObjectMapper objectMapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("application/json; charset=utf-8");
		response.setContentType("application/json; charset=utf-8");
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		// Read object from json
		User user = HttpUtil.of(request.getReader()).toEntity(User.class);
		List<User> users = new ArrayList<>();
		if(!user.getUsername().equals("") && !user.getPassword().equals("")) {
			users = UserModel.findByName(user.getUsername(), user.getPassword());
		}
		if(!users.isEmpty()) {
			
			JSONObject obj = new JSONObject();
			obj.put("data", users);
			obj.put("status", "SUCCESS");
			obj.put("numpage", 1);
			obj.put("message", "Successful!");

			response.getWriter().write(obj.toString(4));
		}
	}
}
