package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import org.model.ResultModel;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("StudLogin.html");
            return;
        }

        ResultModel result = (ResultModel) session.getAttribute("result");

        if (result == null) {
            response.sendRedirect("studentExamList");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Exam Result</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700;800&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css' rel='stylesheet'>");
        out.println("<style>body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;color:#0f172a;}");
        out.println(".res-card{max-width:540px;margin:20px auto;background:#fff;border-radius:20px;padding:36px;border:1px solid #e2e8f0;box-shadow:0 12px 32px rgba(15,23,42,0.06);text-align:center;}");
        out.println(".score-circle{width:96px;height:96px;border-radius:50%;background:linear-gradient(135deg,#10b981,#059669);color:#fff;display:flex;align-items:center;justify-content:center;font-size:28px;font-weight:800;margin:0 auto 18px auto;box-shadow:0 10px 24px rgba(16,185,129,0.3);}");
        out.println(".stat-box{background:#f8fafc;border:1px solid #e2e8f0;border-radius:12px;padding:12px;}");
        out.println("@media(max-width:576px){body{padding:12px;}.res-card{padding:24px 16px;border-radius:16px;}}</style>");
        out.println("</head><body>");

        out.println("<div class='res-card'>");
        out.println("  <div class='score-circle'>" + String.format("%.0f", result.getPercentage()) + "%</div>");
        out.println("  <h3 class='fw-bold mb-1'>Assessment Completed!</h3>");
        out.println("  <p class='text-muted small mb-4'>Your answers have been evaluated and recorded.</p>");

        out.println("  <div class='row g-3 mb-4'>");
        out.println("    <div class='col-6'><div class='stat-box'><div class='text-muted small fw-semibold'>Total Questions</div><div class='fs-5 fw-bold text-dark'>" + result.getTotalQuestions() + "</div></div></div>");
        out.println("    <div class='col-6'><div class='stat-box'><div class='text-muted small fw-semibold'>Marks Scored</div><div class='fs-5 fw-bold text-primary'>" + result.getObtainedMarks() + " / " + result.getTotalMarks() + "</div></div></div>");
        out.println("    <div class='col-6'><div class='stat-box'><div class='text-muted small fw-semibold'>Correct Answers</div><div class='fs-5 fw-bold text-success'>" + result.getCorrect() + "</div></div></div>");
        out.println("    <div class='col-6'><div class='stat-box'><div class='text-muted small fw-semibold'>Wrong Answers</div><div class='fs-5 fw-bold text-danger'>" + result.getWrong() + "</div></div></div>");
        out.println("  </div>");

        out.println("  <div class='d-flex justify-content-center gap-2'>");
        out.println("    <a href='studentExamList' class='btn btn-outline-secondary fw-semibold px-4'>Back to Exams</a>");
        out.println("    <a href='studentResults' class='btn btn-success fw-semibold px-4'>All Results</a>");
        out.println("  </div>");
        out.println("</div>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}