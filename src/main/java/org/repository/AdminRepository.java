package org.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.model.AdminModel;

public class AdminRepository extends DBConnection{
	public boolean isAdminLogin(AdminModel model) {

		try {

			PreparedStatement ps =
			conn.prepareStatement(
			"select * from admin where email=? and password=?");

			ps.setString(1,
			model.getEmail());

			ps.setString(2,
			model.getPassword());

			ResultSet rs = ps.executeQuery();

			return rs.next();

		}
		catch(Exception e) {

			e.printStackTrace();
		}

		return false;
	}
}