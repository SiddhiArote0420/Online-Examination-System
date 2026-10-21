package org.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

	protected Connection conn;

	protected PreparedStatement stmt;

	protected ResultSet rs;

	public DBConnection() {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = System.getenv("DB_URL") != null
					? System.getenv("DB_URL")
					: "jdbc:mysql://localhost:3306/online_exam_db";
			String user = System.getenv("DB_USER") != null
					? System.getenv("DB_USER")
					: "root";
			String pass = System.getenv("DB_PASSWORD") != null
					? System.getenv("DB_PASSWORD")
					: "MySql_Sid@20";

			conn = DriverManager.getConnection(url, user, pass);

		}
		catch(Exception e) {

			e.printStackTrace();
		}
	}
}