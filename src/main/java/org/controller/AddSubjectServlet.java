package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.model.SubjectModel;
import org.service.SubjectService;

@WebServlet("/addSubject")
public class AddSubjectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String msg = request.getParameter("msg");

        out.println("<!DOCTYPE html><html><head>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:30px;}");
        out.println(".box{max-width:520px;margin:20px auto;background:white;padding:32px;border-radius:18px;border:1px solid #e2e8f0;box-shadow:0 10px 30px rgba(15,23,42,0.05);}");
        out.println(".btn-sub{background:linear-gradient(135deg,#2563eb,#1d4ed8);color:#fff;border:none;font-weight:700;padding:11px;border-radius:10px;}");
        out.println(".btn-sub:hover{opacity:0.95;color:#fff;}");
        out.println("</style></head><body>");

        out.println("<div class='box'>");
        out.println("  <div class='d-flex justify-content-between align-items-center mb-4 pb-3 border-bottom'>");
        out.println("    <div class='d-flex align-items-center gap-3'>");
        out.println("      <div style='width:44px;height:44px;border-radius:12px;background:#eff6ff;color:#2563eb;display:flex;align-items:center;justify-content:center;font-size:20px;'><i class='bi bi-book-half'></i></div>");
        out.println("      <div><h4 class='fw-bold m-0'>Add Subject</h4><span class='text-muted small'>Create a subject category</span></div>");
        out.println("    </div>");
        out.println("    <a href='viewSubject' class='btn btn-outline-secondary btn-sm fw-semibold'><i class='bi bi-arrow-left me-1'></i>Back</a>");
        out.println("  </div>");

        if ("success".equals(msg)) {
            out.println("<div class='alert alert-success py-2 small fw-semibold'><i class='bi bi-check-circle-fill me-1'></i> Subject added successfully!</div>");
        } else if ("failed".equals(msg)) {
            out.println("<div class='alert alert-danger py-2 small fw-semibold'><i class='bi bi-exclamation-triangle-fill me-1'></i> Could not add subject.</div>");
        }

        out.println("  <form action='addSubject' method='post'>");
        out.println("    <label class='form-label fw-semibold small'>Subject Name</label>");
        out.println("    <input class='form-control mb-3 py-2' name='subjectName' placeholder='e.g., Data Structures & Algorithms' required>");
        out.println("    <button class='btn btn-sub w-100'><i class='bi bi-plus-circle me-1'></i> Save Subject</button>");
        out.println("  </form>");
        out.println("</div></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("subjectName");

        SubjectModel model = new SubjectModel();
        model.setSubjectName(name);

        SubjectService service = new SubjectService();
        boolean status = service.addSubject(model);

        if (status) {
            response.sendRedirect("viewSubject");
        } else {
            response.sendRedirect("addSubject?msg=failed");
        }
    }
}
