package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.QuestionModel;
import org.service.QuestionService;


@WebServlet("/updateQuestion")
public class UpdateQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        QuestionService service = new QuestionService();
        QuestionModel q = service.getQuestionById(id);

        if (q == null) {
            out.println("Question not found!");
            return;
        }

        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Update Question</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;color:#0f172a;}");
        out.println(".card-box{max-width:680px;margin:10px auto;background:#fff;padding:30px;border-radius:18px;border:1px solid #e2e8f0;box-shadow:0 10px 30px rgba(15,23,42,0.05);}");
        out.println(".btn-upd{background:linear-gradient(135deg,#2563eb,#1d4ed8);color:#fff;border:none;padding:12px 20px;border-radius:10px;font-weight:700;width:100%;}");
        out.println(".btn-upd:hover{opacity:0.95;color:#fff;}");
        out.println("@media(max-width:576px){body{padding:12px;}.card-box{padding:20px 16px;border-radius:14px;}}");
        out.println("</style></head><body>");

        out.println("<div class='card-box'>");
        out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4 pb-3 border-bottom'>");
        out.println("    <div class='d-flex align-items-center gap-3'>");
        out.println("      <div style='width:46px;height:46px;border-radius:12px;background:#eff6ff;color:#2563eb;display:flex;align-items:center;justify-content:center;font-size:22px;'><i class='bi bi-pencil-square'></i></div>");
        out.println("      <div><h4 class='fw-bold m-0'>Update Question</h4><span class='text-muted small'>Modify question and answer options</span></div>");
        out.println("    </div>");
        out.println("    <a href='viewQuestions' class='btn btn-outline-secondary btn-sm fw-semibold'><i class='bi bi-arrow-left me-1'></i>Back</a>");
        out.println("  </div>");

        out.println("<form action='updateQuestionAction' method='post'>");
        out.println("<input type='hidden' name='questionId' value='" + q.getQuestionId() + "'>");

        out.println("<div class='mb-3'><label class='form-label fw-semibold small'>Exam ID</label>");
        out.println("<input type='number' class='form-control' name='examId' value='" + q.getExamId() + "' required></div>");

        out.println("<div class='mb-3'><label class='form-label fw-semibold small'>Question</label>");
        out.println("<textarea class='form-control' rows='2' name='questionText' required>" + q.getQuestionText() + "</textarea></div>");

        out.println("<div class='row g-3 mb-3'>");
        out.println("  <div class='col-sm-6'><label class='form-label fw-semibold small'>Option 1</label><input class='form-control' name='option1' value='" + q.getOption1() + "' required></div>");
        out.println("  <div class='col-sm-6'><label class='form-label fw-semibold small'>Option 2</label><input class='form-control' name='option2' value='" + q.getOption2() + "' required></div>");
        out.println("  <div class='col-sm-6'><label class='form-label fw-semibold small'>Option 3</label><input class='form-control' name='option3' value='" + q.getOption3() + "' required></div>");
        out.println("  <div class='col-sm-6'><label class='form-label fw-semibold small'>Option 4</label><input class='form-control' name='option4' value='" + q.getOption4() + "' required></div>");
        out.println("</div>");

        out.println("<div class='mb-4'><label class='form-label fw-semibold small text-primary'>Correct Answer</label>");
        out.println("<input class='form-control' name='correctAnswer' value='" + q.getCorrectAnswer() + "' required></div>");

        out.println("<button class='btn-upd'><i class='bi bi-check2-circle me-1'></i> Update Question</button>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body></html>");
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
