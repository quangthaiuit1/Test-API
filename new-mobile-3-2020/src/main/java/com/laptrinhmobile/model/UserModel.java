package com.laptrinhmobile.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhmobile.entities.User;

public class UserModel {
	public static List<User> findByName(String username, String password) {
		java.sql.Connection con = Connection.getConnectionModel();
		List<User> results = new ArrayList<User>();	
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		String sql = "select * from user where username = ? and password = ?";
		if (con != null) {
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, password);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					User user = new User();
					user.setId(resultSet.getInt("id"));
					user.setUsername(resultSet.getNString("username"));
					user.setPassword(resultSet.getNString("password"));
					results.add(user);
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
