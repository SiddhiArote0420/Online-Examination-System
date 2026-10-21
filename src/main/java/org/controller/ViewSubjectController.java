package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.SubjectModel;
import org.repository.SubjectRepository;
import java.util.*;

@WebServlet("/viewSubject")
public class ViewSubjectController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectRepository repo = new SubjectRepository();
        ArrayList<SubjectModel> list = repo.getAllSubjects();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html><html><head>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;}");
        out.println(".card-box{max-width:850px;margin:10px auto;background:#fff;padding:24px;border-radius:16px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 16px;}");
        out.println(".table tbody td{vertical-align:middle;padding:12px 16px;font-size:14px;}");
        out.println("</style></head><body>");

        out.println("<div class='card-box'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-book-half text-primary me-2'></i>Subject Catalog</h4><span class='text-muted small'>Available Subjects: " + list.size() + "</span></div>");
        out.println("    <a class='btn btn-primary btn-sm fw-semibold px-3 py-2' href='addSubject'><i class='bi bi-plus-lg me-1'></i> Add Subject</a>");
        out.println("  </div>");

        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>Subject ID</th><th>Subject Name</th><th class='text-end'>Actions</th></tr></thead>");
        out.println("    <tbody>");

        for (SubjectModel s : list) {
            out.println("<tr>");
            out.println("  <td class='fw-bold text-secondary'>#" + s.getSubjectId() + "</td>");
            out.println("  <td class='fw-semibold'>" + s.getSubjectName() + "</td>");
            out.println("  <td class='text-end'>");
            out.println("    <a class='btn btn-outline-primary btn-sm me-1' href='updateSubject?id=" + s.getSubjectId() + "'><i class='bi bi-pencil-square'></i> Edit</a>");
            out.println("    <a class='btn btn-outline-danger btn-sm' href='deleteSubject?id=" + s.getSubjectId() + "' onclick='return confirm(\"Delete this subject?\")'><i class='bi bi-trash'></i> Delete</a>");
            out.println("  </td>");
            out.println("</tr>");
        }

        out.println("    </tbody></table>");
        out.println("</div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
