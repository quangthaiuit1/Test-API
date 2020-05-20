package com.laptrinhmobile.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhmobile.entities.Employee;

public class EmployeeModel {
	public static List<Employee> findAll() {
		List<Employee> results = new ArrayList<Employee>();
		java.sql.Connection con = Connection.getConnectionModel();
		PreparedStatement statement = null;
		String sql = "select * from employee";
		ResultSet resultSet = null;
		if (con != null) {
			try {
				statement = con.prepareStatement(sql);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Employee employee = new Employee();
					employee.setId(resultSet.getInt("id"));
					employee.setName(resultSet.getNString("name"));
					employee.setEmployeeCode(resultSet.getNString("employee_code"));
					results.add(employee);
				}
				System.out.println(results);
				return results;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					Connection.closeConnection(con, statement);
					if (resultSet != null)
						resultSet.close();
				} catch (Exception e2) {
					return null;
				}
			}
		}
		return null;
	}

	public static List<Employee> findByName(String employeeName) {
		java.sql.Connection con = Connection.getConnectionModel();
		List<Employee> results = new ArrayList<Employee>();
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		String sql = "select * from employee where name = ?";
		if (con != null) {
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, employeeName);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Employee employee = new Employee();
					employee.setId(resultSet.getInt("id"));
					employee.setName(resultSet.getNString("name"));
					employee.setEmployeeCode(resultSet.getNString("employee_code"));
					results.add(employee);
				}
				return results;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					Connection.closeConnection(con, statement);
					if (resultSet != null)
						resultSet.close();
				} catch (Exception e2) {
					return null;
				}
			}
		}
		return null;
	}
}
