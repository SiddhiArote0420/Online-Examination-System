package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.repository.ExamRepository;


@WebServlet("/deleteExam")
public class DeleteExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ExamRepository repo = new ExamRepository();
		boolean status = repo.deleteExam(id);

		if (status) {
			response.sendRedirect("viewExam?msg=deleted");
		} else {
			response.sendRedirect("viewExam?msg=delete_error");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
