package com.laptrinhmobile.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import oa.SendMessage;

@WebServlet(urlPatterns = "/sendmessage")
public class ZaloAPI extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String accessToken = "td91Cg2csNBSS4OqfA7PUzX-HpwWjwLyXX5vUVwccMo58sDm-Bs5LjnhPXQgzBiQaK1c8h7L_0wFIZGXhPtNPu8VG6k_kjfEcp54SB27X6wnLtrKciU6GUDNIWFtmwCimnn8CUQ-hotaFJCLwR7aCECN3WgOn-ykea5iQfxabb6VKbHab_YtO9HNBdUjXRbGZnWkO8oU-4cu4svwmiAONzLZL6FOi9XTn1XEJjkBhsAZFaDuifA2ThSoOb2NbBzAgHTz3esIjrMv9Kn0kh6lLpHEvwvsexlUT0";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		String url = "https://openapi.zalo.me/v2.0/oa/message?access_token=" + ZaloAPI.accessToken;

		String userId = "3399745519397671176";
		String mess = "HI, Đây là Thái, Xin chào bạn! LIXCO";	
		//Test
		model.OAZalo dataResponse = SendMessage.oaSendMessage(url, userId, mess);
		JSONObject jsonResponse = new JSONObject();
		if(dataResponse.getErr() == 0) {
			jsonResponse.put("error", dataResponse.getErr());
			jsonResponse.put("succes", true);
			jsonResponse.put("message", "Send message to user success!");
		}else {
			jsonResponse.put("error", dataResponse.getErr());
			jsonResponse.put("success", false);
			jsonResponse.put("message", "Send message to user fail!");
		}
		resp.getWriter().write(jsonResponse.toString(3));
	}
	public void sendPost(String POST_URL) throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		//		con.setRequestProperty(key, value);
		
		
	}
	public String getUserIdByPhoneNummber(String phoneNumber) throws IOException {
		
		String accessToken = "td91Cg2csNBSS4OqfA7PUzX-HpwWjwLyXX5vUVwccMo58sDm-Bs5LjnhPXQgzBiQaK1c8h7L_0wFIZGXhPtNPu8VG6k_kjfEcp54SB27X6wnLtrKciU6GUDNIWFtmwCimnn8CUQ-hotaFJCLwR7aCECN3WgOn-ykea5iQfxabb6VKbHab_YtO9HNBdUjXRbGZnWkO8oU-4cu4svwmiAONzLZL6FOi9XTn1XEJjkBhsAZFaDuifA2ThSoOb2NbBzAgHTz3esIjrMv9Kn0kh6lLpHEvwvsexlUT0";
		
		JSONObject jsonData = new JSONObject();
		JSONObject jsonUserId = new JSONObject();
		jsonUserId.put("user_id", phoneNumber);

		jsonData.put("data", jsonUserId);
		System.out.println(jsonData);
		
		String url = "https://openapi.zalo.me/v2.0/oa/getprofile?access_token=" + accessToken + "&" ;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		return "";
	}
}
