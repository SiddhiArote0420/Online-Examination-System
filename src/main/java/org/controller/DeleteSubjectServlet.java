package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.repository.SubjectRepository;


@WebServlet("/deleteSubject")
public class DeleteSubjectServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		SubjectRepository repo = new SubjectRepository();
		boolean status = repo.deleteSubject(id);

		if (status) {
			response.sendRedirect("viewSubject?msg=deleted");
		} else {
			response.sendRedirect("viewSubject?msg=delete_error");
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
