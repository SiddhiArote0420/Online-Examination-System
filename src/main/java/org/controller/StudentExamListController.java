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
        out.println(".table-box{background:#fff;padding:26px;border-radius:16px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 16px;}");
        out.println(".table tbody td{vertical-align:middle;padding:14px 16px;font-size:14.5px;}");
        out.println(".page-btn{border:1px solid #cbd5e1;background:#fff;color:#334155;padding:5px 12px;border-radius:8px;font-size:13px;font-weight:600;cursor:pointer;transition:0.2s;}");
        out.println(".page-btn:hover:not(:disabled){background:#eff6ff;border-color:#93c5fd;color:#2563eb;}");
        out.println(".page-btn.active{background:#2563eb;border-color:#2563eb;color:#fff;}");
        out.println(".page-btn:disabled{opacity:0.5;cursor:not-allowed;}");
        out.println("</style></head><body>");

        out.println("<div class='table-box'>");
        out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-journal-text text-primary me-2'></i>Available Assessments</h4><span class='text-muted small'>Select any pending exam below to begin</span></div>");
        out.println("    <div><input type='text' id='searchInput' onkeyup='filterTable()' class='form-control form-control-sm' placeholder='Search exam...' style='width:200px;border-radius:8px;'></div>");
        out.println("  </div>");

        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>Examination Title</th><th>Status</th><th class='text-end'>Action</th></tr></thead>");
        out.println("    <tbody id='tableBody'>");

        for (ExamModel e : list) {
            out.println("<tr>");
            out.println("  <td class='fw-semibold'><i class='bi bi-file-earmark-text text-primary me-2'></i>" + e.getExamName() + "</td>");
            if ("PENDING".equalsIgnoreCase(e.getStatus())) {
                out.println("  <td><span class='badge bg-warning-subtle text-warning-emphasis border border-warning-subtle rounded-pill px-3 py-1'>Pending</span></td>");
                out.println("  <td class='text-end'><a href='startExam?examId=" + e.getExamId() + "' class='btn btn-primary btn-sm fw-semibold px-3 py-1 rounded-2'><i class='bi bi-play-fill me-1'></i>Start Exam</a></td>");
            } else {
                out.println("  <td><span class='badge bg-success-subtle text-success border border-success-subtle rounded-pill px-3 py-1'>Completed</span></td>");
                out.println("  <td class='text-end'><button class='btn btn-light btn-sm text-muted border px-3 py-1' disabled><i class='bi bi-check2-all me-1'></i>Submitted</button></td>");
            }
            out.println("</tr>");
        }

        out.println("    </tbody></table>");

        // PAGINATION BAR
        out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center mt-3 pt-3 border-top'>");
        out.println("    <div class='text-muted small fw-semibold' id='pageInfo'></div>");
        out.println("    <div class='d-flex align-items-center gap-1' id='paginationControls'></div>");
        out.println("  </div>");

        out.println("</div>");

        out.println("<script>");
        out.println("const rowsPerPage = 5; let currentPage = 1;");
        out.println("const allRows = Array.from(document.querySelectorAll('#tableBody tr'));");
        out.println("let filteredRows = [...allRows];");
        out.println("function filterTable(){");
        out.println("  const q = document.getElementById('searchInput').value.toLowerCase();");
        out.println("  filteredRows = allRows.filter(r => r.innerText.toLowerCase().includes(q));");
        out.println("  currentPage = 1; renderTable();");
        out.println("}");
        out.println("function renderTable(){");
        out.println("  allRows.forEach(r => r.style.display = 'none');");
        out.println("  const total = filteredRows.length; const totalPages = Math.max(1, Math.ceil(total / rowsPerPage));");
        out.println("  if(currentPage > totalPages) currentPage = totalPages;");
        out.println("  const start = (currentPage - 1) * rowsPerPage; const end = Math.min(start + rowsPerPage, total);");
        out.println("  filteredRows.slice(start, end).forEach(r => r.style.display = '');");
        out.println("  document.getElementById('pageInfo').innerText = total === 0 ? 'No records found' : `Showing ${start + 1} to ${end} of ${total} entries`;");
        out.println("  const ctrl = document.getElementById('paginationControls'); ctrl.innerHTML = '';");
        out.println("  const prev = document.createElement('button'); prev.className = 'page-btn'; prev.innerHTML = '&laquo; Prev'; prev.disabled = currentPage === 1; prev.onclick = () => { currentPage--; renderTable(); }; ctrl.appendChild(prev);");
        out.println("  for(let i=1; i<=totalPages; i++){ const b = document.createElement('button'); b.className = 'page-btn' + (i===currentPage ? ' active' : ''); b.innerText = i; b.onclick = () => { currentPage = i; renderTable(); }; ctrl.appendChild(b); }");
        out.println("  const next = document.createElement('button'); next.className = 'page-btn'; next.innerHTML = 'Next &raquo;'; next.disabled = currentPage === totalPages; next.onclick = () => { currentPage++; renderTable(); }; ctrl.appendChild(next);");
        out.println("}");
        out.println("renderTable();");
        out.println("</script>");

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
