package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.ExamModel;
import org.repository.ExamRepository;


@WebServlet("/updateExamAction")
public class UpdateExamActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExamModel model = new ExamModel();

		model.setExamId(Integer.parseInt(request.getParameter("examId")));
		model.setExamName(request.getParameter("examName"));
		model.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
		model.setTotalQuestions(Integer.parseInt(request.getParameter("totalQuestions")));
		model.setTotalMarks(Integer.parseInt(request.getParameter("totalMarks")));
		model.setExamDuration(Integer.parseInt(request.getParameter("examDuration")));

		ExamRepository repo = new ExamRepository();
		boolean status = repo.updateExam(model);

		if (status) {
			response.sendRedirect("viewExam?msg=updated");
		} else {
			response.sendRedirect("viewExam?msg=update_error");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
