package com.laptrinhmobile.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtil {
	private String value;
	
	public HttpUtil(String value) {
		this.value = value;
	};
	public <T> T toEntity(Class<T> tclass){
		try {
			return new ObjectMapper().readValue(value, tclass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static HttpUtil of(BufferedReader bufferedReader) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		try{
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(stringBuilder.toString());
	}
}