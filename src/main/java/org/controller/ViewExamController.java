package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.ExamModel;
import org.service.ExamService;
import java.util.*;

@WebServlet("/viewExam")
public class ViewExamController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ExamService service = new ExamService();
        ArrayList<ExamModel> list = service.getAllExams();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;}");
        out.println(".panel-card{background:#fff;border-radius:16px;padding:24px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 14px;}");
        out.println(".table tbody td{vertical-align:middle;padding:12px 14px;font-size:14px;}");
        out.println("</style></head><body>");

        out.println("<div class='panel-card'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-journal-check text-primary me-2'></i>Configured Examinations</h4><span class='text-muted small'>Total Exams: " + list.size() + "</span></div>");
        out.println("    <a href='addExam.html' class='btn btn-primary btn-sm fw-semibold px-3 py-2'><i class='bi bi-plus-lg me-1'></i> Create Exam</a>");
        out.println("  </div>");

        out.println("  <div class='table-responsive'>");
        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>ID</th><th>Exam Name</th><th>Subject ID</th><th>Questions</th><th>Total Marks</th><th>Duration</th><th class='text-end'>Actions</th></tr></thead>");
        out.println("    <tbody>");

        for (ExamModel e : list) {
            out.println("<tr>");
            out.println("  <td class='fw-bold text-secondary'>#" + e.getExamId() + "</td>");
            out.println("  <td class='fw-semibold'>" + e.getExamName() + "</td>");
            out.println("  <td><span class='badge bg-light text-dark border'>Sub #" + e.getSubjectId() + "</span></td>");
            out.println("  <td>" + e.getTotalQuestions() + " Qs</td>");
            out.println("  <td><span class='badge bg-primary-subtle text-primary'>" + e.getTotalMarks() + " Marks</span></td>");
            out.println("  <td><i class='bi bi-clock me-1 text-muted'></i>" + e.getExamDuration() + " min</td>");
            out.println("  <td class='text-end'>");
            out.println("    <a href='updateExam?id=" + e.getExamId() + "' class='btn btn-outline-primary btn-sm me-1'><i class='bi bi-pencil-square'></i> Edit</a>");
            out.println("    <a href='deleteExam?id=" + e.getExamId() + "' class='btn btn-outline-danger btn-sm' onclick='return confirm(\"Delete this exam?\")'><i class='bi bi-trash'></i> Delete</a>");
            out.println("  </td>");
            out.println("</tr>");
        }

        out.println("    </tbody></table></div>");
        out.println("</div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
