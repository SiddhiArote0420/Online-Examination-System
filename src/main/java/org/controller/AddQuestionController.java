package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.QuestionModel;
import org.service.QuestionService;


@WebServlet("/addQuestion")
public class AddQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int examId = Integer.parseInt(request.getParameter("examId"));

			String questionText = request.getParameter("questionText");
			String option1 = request.getParameter("option1");
			String option2 = request.getParameter("option2");
			String option3 = request.getParameter("option3");
			String option4 = request.getParameter("option4");
			String correctAnswer = request.getParameter("correctAnswer");

			QuestionModel model = new QuestionModel();
			model.setExamId(examId);
			model.setQuestionText(questionText);
			model.setOption1(option1);
			model.setOption2(option2);
			model.setOption3(option3);
			model.setOption4(option4);
			model.setCorrectAnswer(correctAnswer);

			QuestionService service = new QuestionService();
			boolean result = service.addQuestion(model);

			if (result) {
				response.sendRedirect("addQuestion.html?msg=success");
			} else {
				response.sendRedirect("addQuestion.html?msg=fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("addQuestion.html?msg=error");
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
