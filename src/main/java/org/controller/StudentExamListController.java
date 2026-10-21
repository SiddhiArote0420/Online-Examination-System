package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import org.model.ExamModel;
import org.service.ExamService;

import java.util.*;

@WebServlet("/studentExamList")
public class StudentExamListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("StudLogin.html");
            return;
        }

        int studentId = (Integer) session.getAttribute("studentId");

        ExamService service = new ExamService();
        List<ExamModel> list = service.getStudentExams(studentId);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head><title>Exam List</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;}");
        out.println(".table-box{max-width:850px;margin:10px auto;background:#fff;padding:26px;border-radius:16px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 16px;}");
        out.println(".table tbody td{vertical-align:middle;padding:14px 16px;font-size:14.5px;}");
        out.println("</style></head><body>");

        out.println("<div class='table-box'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-journal-text text-success me-2'></i>Available Assessments</h4><span class='text-muted small'>Select any pending exam below to begin</span></div>");
        out.println("  </div>");

        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>Examination Title</th><th>Status</th><th class='text-end'>Action</th></tr></thead>");
        out.println("    <tbody>");

        for (ExamModel e : list) {
            out.println("<tr>");
            out.println("  <td class='fw-semibold'><i class='bi bi-file-earmark-text text-muted me-2'></i>" + e.getExamName() + "</td>");
            if ("PENDING".equalsIgnoreCase(e.getStatus())) {
                out.println("  <td><span class='badge bg-warning-subtle text-warning-emphasis border border-warning-subtle rounded-pill px-3 py-1'>Pending</span></td>");
                out.println("  <td class='text-end'><a href='startExam?examId=" + e.getExamId() + "' class='btn btn-success btn-sm fw-semibold px-3 py-1 rounded-2'><i class='bi bi-play-fill me-1'></i>Start Exam</a></td>");
            } else {
                out.println("  <td><span class='badge bg-success-subtle text-success border border-success-subtle rounded-pill px-3 py-1'>Completed</span></td>");
                out.println("  <td class='text-end'><button class='btn btn-light btn-sm text-muted border px-3 py-1' disabled><i class='bi bi-check2-all me-1'></i>Submitted</button></td>");
            }
            out.println("</tr>");
        }

        out.println("    </tbody></table>");
        out.println("</div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
