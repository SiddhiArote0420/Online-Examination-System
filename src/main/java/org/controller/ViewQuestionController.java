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

import java.util.*;

@WebServlet("/viewQuestions")
public class ViewQuestionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        QuestionService service = new QuestionService();
        List<QuestionModel> list = service.getAllQuestions();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;}");
        out.println(".panel-card{background:#fff;padding:24px;border-radius:16px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 14px;}");
        out.println(".table tbody td{vertical-align:middle;padding:12px 14px;font-size:14px;}");
        out.println(".ans-badge{background:#ecfdf5;color:#059669;border:1px solid #a7f3d0;padding:4px 10px;border-radius:6px;font-weight:600;font-size:12.5px;}");
        out.println("</style></head><body>");

        out.println("<div class='panel-card'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-patch-question-fill text-primary me-2'></i>Question Bank</h4><span class='text-muted small'>Total Questions: " + list.size() + "</span></div>");
        out.println("    <a href='addQuestion.html' class='btn btn-primary btn-sm fw-semibold px-3 py-2'><i class='bi bi-plus-lg me-1'></i> Add Question</a>");
        out.println("  </div>");

        out.println("  <div class='table-responsive'>");
        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>ID</th><th>Exam Name</th><th>Question Statement</th><th>Correct Answer</th><th class='text-end'>Actions</th></tr></thead>");
        out.println("    <tbody>");

        for (QuestionModel q : list) {
            out.println("<tr>");
            out.println("  <td class='fw-bold text-secondary'>#" + q.getQuestionId() + "</td>");
            out.println("  <td><span class='badge bg-light text-dark border'>" + q.getExamName() + "</span></td>");
            out.println("  <td class='fw-medium'>" + q.getQuestionText() + "</td>");
            out.println("  <td><span class='ans-badge'><i class='bi bi-check2 me-1'></i>" + q.getCorrectAnswer() + "</span></td>");
            out.println("  <td class='text-end text-nowrap'>");
            out.println("    <a href='updateQuestion?id=" + q.getQuestionId() + "' class='btn btn-outline-primary btn-sm me-1'><i class='bi bi-pencil-square'></i> Edit</a>");
            out.println("    <a href='deleteQuestion?id=" + q.getQuestionId() + "' class='btn btn-outline-danger btn-sm' onclick='return confirm(\"Delete this question?\")'><i class='bi bi-trash'></i> Delete</a>");
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
