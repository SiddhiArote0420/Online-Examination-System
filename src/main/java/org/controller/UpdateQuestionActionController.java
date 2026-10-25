package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.QuestionModel;
import org.service.QuestionService;


@WebServlet("/updateQuestionAction")
public class UpdateQuestionActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            QuestionModel model = new QuestionModel();

            model.setQuestionId(Integer.parseInt(request.getParameter("questionId")));
            model.setExamId(Integer.parseInt(request.getParameter("examId")));
            model.setQuestionText(request.getParameter("questionText"));

            model.setOption1(request.getParameter("option1"));
            model.setOption2(request.getParameter("option2"));
            model.setOption3(request.getParameter("option3"));
            model.setOption4(request.getParameter("option4"));

            model.setCorrectAnswer(request.getParameter("correctAnswer"));

            QuestionService service = new QuestionService();
            boolean result = service.updateQuestion(model);

            if (result) {
                response.sendRedirect("viewQuestions?msg=updated");
            } else {
                response.sendRedirect("viewQuestions?msg=update_error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("viewQuestions?msg=update_error");
        }
    }

}
