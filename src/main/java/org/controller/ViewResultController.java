package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import org.model.ResultModel;
import org.service.ResultService;

@WebServlet("/viewResults")
public class ViewResultController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ResultService service = new ResultService();
        ArrayList<ResultModel> list = service.getAllResults();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;}");
        out.println(".panel-card{background:#fff;padding:24px;border-radius:16px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 14px;}");
        out.println(".table tbody td{vertical-align:middle;padding:12px 14px;font-size:14px;}");
        out.println(".badge-pass{background:#ecfdf5;color:#059669;border:1px solid #a7f3d0;padding:5px 12px;border-radius:999px;font-weight:700;font-size:12px;}");
        out.println(".badge-fail{background:#fef2f2;color:#dc2626;border:1px solid #fecaca;padding:5px 12px;border-radius:999px;font-weight:700;font-size:12px;}");
        out.println("</style></head><body>");

        out.println("<div class='panel-card'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-trophy-fill text-warning me-2'></i>Student Examination Results</h4><span class='text-muted small'>Total Submissions: " + list.size() + "</span></div>");
        out.println("  </div>");

        out.println("  <div class='table-responsive'>");
        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>Result ID</th><th>Student Name</th><th>Exam Name</th><th>Subject</th><th>Score</th><th>Percentage</th><th class='text-center'>Status</th></tr></thead>");
        out.println("    <tbody>");

        for (ResultModel r : list) {
            out.println("<tr>");
            out.println("  <td class='fw-bold text-secondary'>#" + r.getResultId() + "</td>");
            out.println("  <td class='fw-semibold'>" + r.getStudentName() + "</td>");
            out.println("  <td>" + r.getExamName() + "</td>");
            out.println("  <td><span class='badge bg-light text-dark border'>" + r.getSubjectName() + "</span></td>");
            out.println("  <td class='fw-bold'>" + r.getObtainedMarks() + " <span class='text-muted fw-normal'>/ " + r.getTotalMarks() + "</span></td>");
            out.println("  <td class='fw-semibold text-primary'>" + String.format("%.1f", r.getPercentage()) + "%</td>");
            if ("PASS".equalsIgnoreCase(r.getStatus())) {
                out.println("  <td class='text-center'><span class='badge-pass'><i class='bi bi-check-circle-fill me-1'></i>PASS</span></td>");
            } else {
                out.println("  <td class='text-center'><span class='badge-fail'><i class='bi bi-x-circle-fill me-1'></i>FAIL</span></td>");
            }
            out.println("</tr>");
        }

        out.println("    </tbody></table></div>");
        out.println("</div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
