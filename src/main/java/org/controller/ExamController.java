package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.model.QuestionModel;
import org.model.ResultModel;
import org.service.ExamService;
import org.service.QuestionService;

@WebServlet("/exam")
public class ExamController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		// Session check
		if (session == null || session.getAttribute("examId") == null) {

			response.sendRedirect("examList");
			return;
		}

		int examId = (Integer) session.getAttribute("examId");

		int index = 0;

		if (session.getAttribute("index") != null) {

			index = (Integer) session.getAttribute("index");

		}

		QuestionService service = new QuestionService();

		// YOUR METHOD NAME
		List<QuestionModel> questions = service.getQuestionsByExamId(examId);
		// Debug
		//System.out.println("Exam ID = " + examId);
		//System.out.println("Question Count = " + questions.size());

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		// No Questions

		if (questions == null || questions.isEmpty()) {

		    out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title>Exam Not Available</title>");
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		    out.println("</head>");

		    out.println("<body class='bg-light'>");

		    out.println("<div class='container d-flex justify-content-center align-items-center' style='height:90vh;'>");

		    out.println("<div class='card shadow-lg border-0 text-center p-5' style='max-width:650px;'>");

		    out.println("<div style='font-size:80px;'>📚</div>");

		    out.println("<h2 class='text-danger mt-3'>Exam Not Available</h2>");

		    out.println("<p class='fs-5 mt-3'>");
		    out.println("This exam cannot be started because no questions have been added yet.");
		    out.println("</p>");

		    out.println("<div class='alert alert-warning mt-3'>");
		    out.println("<strong>Please contact the administrator.</strong><br>");
		    out.println("Questions for this exam have not been uploaded yet.");
		    out.println("</div>");

		    out.println("<a href='studentExamList' class='btn btn-primary mt-3 px-4'>");
		    out.println("← Back to Exam List");
		    out.println("</a>");

		    out.println("</div>");
		    out.println("</div>");
		    out.println("</body>");
		    out.println("</html>");

		    return;
		}

		// Index validation

		if (index < 0) {
			index = 0;
		}

		if (index >= questions.size()) {
			index = questions.size() - 1;
		}

		session.setAttribute("index", index);

		QuestionModel q = questions.get(index);

		out.println("<html>");
		out.println("<head>");

		out.println(
				"<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");

		out.println("</head>");

		out.println("<body class='bg-light'>");
		out.println("<div class='container mt-5'>");
		out.println("<div class='card shadow p-4'>");
		out.println("<h4>Question " + (index + 1) + " / " + questions.size() + "</h4>");
		out.println("<h5 class='text-danger'>Time Remaining : <span id='timer'></span></h5>");
		out.println("<hr>");
		out.println("<h5>" + q.getQuestionText() + "</h5>");
		out.println("<form action='exam' method='post'>");

		// Option 1

		out.println("<div class='form-check mt-3'>");
		out.println("<input class='form-check-input' type='radio' name='answer' value='" + q.getOption1() + "'>");
		out.println("<label class='form-check-label'>" + q.getOption1() + "</label>");
		out.println("</div>");

		// Option 2

		out.println("<div class='form-check mt-2'>");
		out.println("<input class='form-check-input' type='radio' name='answer' value='" + q.getOption2() + "'>");
		out.println("<label class='form-check-label'>" + q.getOption2() + "</label>");
		out.println("</div>");

		// Option 3

		out.println("<div class='form-check mt-2'>");
		out.println("<input class='form-check-input' type='radio' name='answer' value='" + q.getOption3() + "'>");
		out.println("<label class='form-check-label'>" + q.getOption3() + "</label>");
		out.println("</div>");

		// Option 4

		out.println("<div class='form-check mt-2 mb-4'>");
		out.println("<input class='form-check-input' type='radio' name='answer' value='" + q.getOption4() + "'>");
		out.println("<label class='form-check-label'>" + q.getOption4() + "</label>");
		out.println("</div>");

		// Buttons

		out.println("<div class='d-flex gap-2'>");
		if (index > 0) {
			out.println("<button class='btn btn-secondary' name='action' value='prev'>Previous</button>");
		}

		if (index < questions.size() - 1) {
			out.println("<button class='btn btn-primary' name='action' value='next'>Next</button>");
		} else {
			out.println("<button class='btn btn-success' name='action' value='submit'>Submit Exam</button>");
		}

		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		
		//js
		out.println("<script>");
		out.println("let endTime = " + session.getAttribute("endTime") + ";");
		out.println("let timer = setInterval(function(){");
		out.println("let currentTime = new Date().getTime();");
		out.println("let remaining = endTime - currentTime;");
		out.println("let minutes = Math.floor(remaining / 1000 / 60);");
		out.println("let seconds = Math.floor((remaining / 1000) % 60);");
		out.println("document.getElementById('timer').innerHTML = minutes + ':' + (seconds < 10 ? '0' : '') + seconds;");

		out.println("if(remaining <= 0){");
		out.println("clearInterval(timer);");
		out.println("alert('Exam Time Over');");
		out.println("window.location='submitExam';");
		out.println("}");

		out.println("},1000);");


		out.println("</script>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {


	    HttpSession session = request.getSession();

	    Integer indexObj = (Integer) session.getAttribute("index");


	    if (indexObj == null) {
	        response.sendRedirect("examList");
	        return;
	    }


	    int index = indexObj;

	    String action = request.getParameter("action");


	    // Save selected answer
	    String answer = request.getParameter("answer");


	    if(answer != null) {

	        Integer examId = (Integer) session.getAttribute("examId");

	        QuestionService qService = new QuestionService();

	        List<QuestionModel> questions =  qService.getQuestionsByExamId(examId);

	        QuestionModel currentQuestion = questions.get(index);
	        Map<String,String> answers =  (Map<String,String>) session.getAttribute("answers");

	        if(answers == null) {
	            answers = new java.util.HashMap<>();
	        }


	        answers.put( String.valueOf(currentQuestion.getQuestionId()), answer);
	        session.setAttribute("answers", answers);
	    }


	    if ("next".equals(action)) {
	        index++;
	    }   else if ("prev".equals(action)) {
	        index--;
	    }  else if ("submit".equals(action)) {
	        int examId = (Integer) session.getAttribute("examId");

	        Map<String,String> answers = (Map<String,String>) session.getAttribute("answers");
	        ExamService service = new ExamService();
	        ResultModel result =
	                service.calculateResult(examId, answers);

	        session.setAttribute("result", result);
	        response.sendRedirect("submitExam");
	        return;

	    }



	    session.setAttribute("index", index);

	    response.sendRedirect("exam");

	}

}