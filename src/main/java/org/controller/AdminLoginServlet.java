package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.AdminModel;
import org.repository.AdminRepository;
import org.service.AdminService;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");

		String password = request.getParameter("password");

		AdminModel model = new AdminModel();

		model.setEmail(email);

		model.setPassword(password);
		AdminService service = new AdminService();

		boolean status = service.isAdminLogin(model);

		if (status) {

			response.sendRedirect("AdminDashboard.html");
		} else {

			response.sendRedirect("Adminlogin.html?error=Invalid Email or Password");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
