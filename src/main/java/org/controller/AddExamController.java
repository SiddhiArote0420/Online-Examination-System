package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.ExamModel;
import org.service.ExamService;


@WebServlet("/addExam")
public class AddExamController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String examName = request.getParameter("examName");
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        int totalQuestions = Integer.parseInt(request.getParameter("totalQuestions"));
        int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
        int duration = Integer.parseInt(request.getParameter("examDuration"));

        ExamModel e = new ExamModel();
        e.setExamName(examName);
        e.setSubjectId(subjectId);
        e.setTotalQuestions(totalQuestions);
        e.setTotalMarks(totalMarks);
        e.setExamDuration(duration);

        ExamService service = new ExamService();
        boolean status = service.addExam(e);

        if(status) {
            response.sendRedirect("addExam.html?msg=success");
        } else {
            response.sendRedirect("addExam.html?msg=fail");

        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
