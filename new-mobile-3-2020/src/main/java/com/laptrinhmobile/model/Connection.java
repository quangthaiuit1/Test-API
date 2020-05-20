package com.laptrinhmobile.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
	public static java.sql.Connection getConnectionModel() {
		// Load driver mysql
		try {
			// handle connect mysql
			String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
			String user = "root";
			String password = "voquangthai";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (java.lang.ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	public static boolean closeConnection(java.sql.Connection con,PreparedStatement ps) {
		try {
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
		} catch (Exception e2) {
			return false;
		}
		return true;
	}
}
