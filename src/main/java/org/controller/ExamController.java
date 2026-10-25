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
		    out.println("<html lang='en'>");
		    out.println("<head>");
		    out.println("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		    out.println("<title>Exam Not Available</title>");
		    out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
		    out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		    out.println("</head>");

		    out.println("<body style='background:#f8fafc;font-family:\"Plus Jakarta Sans\",sans-serif;padding:16px;'>");

		    out.println("<div class='container d-flex justify-content-center align-items-center' style='min-height:85vh;'>");

		    out.println("<div class='card shadow-sm border text-center p-4 p-md-5 rounded-4' style='max-width:580px;width:100%;'>");

		    out.println("<div style='font-size:64px;'>📚</div>");

		    out.println("<h3 class='text-danger fw-bold mt-2'>Exam Not Available</h3>");

		    out.println("<p class='text-secondary mt-2'>");
		    out.println("This exam cannot be started because no questions have been added yet.");
		    out.println("</p>");

		    out.println("<div class='alert alert-warning mt-2 small'>");
		    out.println("<strong>Please contact the administrator.</strong><br>");
		    out.println("Questions for this exam have not been uploaded yet.");
		    out.println("</div>");

		    out.println("<a href='studentExamList' class='btn btn-primary mt-2 px-4 fw-semibold'>");
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
		@SuppressWarnings("unchecked")
		Map<String, String> savedAnswers = (Map<String, String>) session.getAttribute("answers");
		String selectedAns = (savedAnswers != null) ? savedAnswers.get(String.valueOf(q.getQuestionId())) : null;

		out.println("<!DOCTYPE html><html lang='en'>");
		out.println("<head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Active Examination</title>");
		out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap' rel='stylesheet'>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css' rel='stylesheet'>");
		out.println("<style>");
		out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;color:#0f172a;}");
		out.println(".exam-card{max-width:760px;margin:10px auto;background:#fff;border-radius:20px;padding:30px;border:1px solid #e2e8f0;box-shadow:0 10px 30px rgba(15,23,42,0.05);}");
		out.println(".exam-header{display:flex;flex-wrap:wrap;justify-content:space-between;align-items:center;gap:12px;padding-bottom:18px;border-bottom:1px solid #e2e8f0;margin-bottom:22px;}");
		out.println(".q-badge{background:#eff6ff;color:#2563eb;font-weight:700;font-size:14px;padding:8px 16px;border-radius:10px;border:1px solid #bfdbfe;}");
		out.println(".timer-pill{background:#fef2f2;color:#dc2626;border:1px solid #fecaca;padding:8px 16px;border-radius:10px;font-weight:700;font-size:14.5px;display:inline-flex;align-items:center;gap:8px;}");
		out.println(".q-title{font-weight:700;font-size:18px;line-height:1.5;color:#0f172a;margin-bottom:20px;}");
		out.println(".opt-label{display:flex;align-items:center;gap:12px;padding:14px 16px;border:1.5px solid #e2e8f0;border-radius:12px;margin-bottom:12px;cursor:pointer;transition:all 0.2s ease;font-weight:500;font-size:15px;}");
		out.println(".opt-label:hover{background:#f8fafc;border-color:#93c5fd;}");
		out.println(".opt-label input[type='radio']{width:18px;height:18px;margin:0;flex-shrink:0;cursor:pointer;}");
		out.println("@media(max-width:576px){body{padding:12px;}.exam-card{padding:20px 16px;border-radius:16px;}.q-title{font-size:16px;}.opt-label{padding:12px;font-size:14px;}}");
		out.println("</style>");
		out.println("</head>");

		out.println("<body>");
		out.println("<div class='exam-card'>");
		out.println("  <div class='exam-header'>");
		out.println("    <span class='q-badge'>Question " + (index + 1) + " of " + questions.size() + "</span>");
		out.println("    <div class='timer-pill'><i class='bi bi-stopwatch-fill'></i> Time Remaining: <span id='timer'>--:--</span></div>");
		out.println("  </div>");
		out.println("  <div class='q-title'>" + q.getQuestionText() + "</div>");
		out.println("  <form action='exam' method='post'>");

		// Option 1
		out.println("    <label class='opt-label'>");
		out.println("      <input class='form-check-input' type='radio' name='answer' value='" + q.getOption1() + "'" + (q.getOption1().equals(selectedAns) ? " checked" : "") + ">");
		out.println("      <span>" + q.getOption1() + "</span>");
		out.println("    </label>");

		// Option 2
		out.println("    <label class='opt-label'>");
		out.println("      <input class='form-check-input' type='radio' name='answer' value='" + q.getOption2() + "'" + (q.getOption2().equals(selectedAns) ? " checked" : "") + ">");
		out.println("      <span>" + q.getOption2() + "</span>");
		out.println("    </label>");

		// Option 3
		out.println("    <label class='opt-label'>");
		out.println("      <input class='form-check-input' type='radio' name='answer' value='" + q.getOption3() + "'" + (q.getOption3().equals(selectedAns) ? " checked" : "") + ">");
		out.println("      <span>" + q.getOption3() + "</span>");
		out.println("    </label>");

		// Option 4
		out.println("    <label class='opt-label mb-4'>");
		out.println("      <input class='form-check-input' type='radio' name='answer' value='" + q.getOption4() + "'" + (q.getOption4().equals(selectedAns) ? " checked" : "") + ">");
		out.println("      <span>" + q.getOption4() + "</span>");
		out.println("    </label>");

		// Buttons
		out.println("    <div class='d-flex flex-wrap justify-content-between align-items-center gap-2 pt-2 border-top'>");
		if (index > 0) {
			out.println("      <button class='btn btn-outline-secondary fw-semibold px-4 py-2 rounded-3' name='action' value='prev'><i class='bi bi-arrow-left me-1'></i> Previous</button>");
		} else {
			out.println("      <div></div>");
		}

		if (index < questions.size() - 1) {
			out.println("      <button class='btn btn-primary fw-semibold px-4 py-2 rounded-3' name='action' value='next'>Next <i class='bi bi-arrow-right ms-1'></i></button>");
		} else {
			out.println("      <button class='btn btn-success fw-semibold px-4 py-2 rounded-3' name='action' value='submit'><i class='bi bi-check2-circle me-1'></i> Submit Exam</button>");
		}

		out.println("    </div>");
		out.println("  </form>");
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