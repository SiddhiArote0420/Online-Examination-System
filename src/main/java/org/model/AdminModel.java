package org.model;

public class AdminModel {
	private int adminId;

	private String adminName;

	private String email;

	private String password;

	
	// GET ADMIN ID
	
	public int getAdminId() {

		return adminId;
	}

	public void setAdminId(int adminId) {

		this.adminId = adminId;
	}

	
	// GET ADMIN NAME
	
	public String getAdminName() {

		return adminName;
	}

	public void setAdminName(String adminName) {

		this.adminName = adminName;
	}

	
	// GET EMAIL
	
	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	
	// GET PASSWORD
	
	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}
}
