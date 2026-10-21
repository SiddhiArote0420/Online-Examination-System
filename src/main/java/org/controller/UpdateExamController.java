package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.ExamModel;
import org.repository.ExamRepository;


@WebServlet("/updateExam")
public class UpdateExamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));

		ExamRepository repo = new ExamRepository();
		ExamModel e = repo.getExamById(id);

		out.println("<html><head>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("</head><body class='bg-light'>");

		out.println("<div class='container mt-5'>");
		out.println("<div class='card p-4 shadow'>");

		out.println("<h3>Update Exam</h3>");

		out.println("<form action='updateExamAction' method='post'>");

		out.println("<input type='hidden' name='examId' value='" + e.getExamId() + "'/>");

		out.println("Exam Name: <input class='form-control' name='examName' value='" + e.getExamName() + "'/><br>");

		out.println("Subject ID: <input class='form-control' name='subjectId' value='" + e.getSubjectId() + "'/><br>");

		out.println("Total Questions: <input class='form-control' name='totalQuestions' value='" + e.getTotalQuestions() + "'/><br>");

		out.println("Total Marks: <input class='form-control' name='totalMarks' value='" + e.getTotalMarks() + "'/><br>");

		out.println("Duration: <input class='form-control' name='examDuration' value='" + e.getExamDuration() + "'/><br>");

		out.println("<button class='btn btn-success'>Update</button>");

		out.println("</form>");

		out.println("</div></div>");

		out.println("</body></html>");
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
