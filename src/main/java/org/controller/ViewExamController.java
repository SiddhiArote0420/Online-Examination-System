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
        String msg = request.getParameter("msg");

        out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
        out.println("<style>");
        out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;color:#0f172a;}");
        out.println(".panel-card{background:#fff;border-radius:16px;padding:24px;border:1px solid #e2e8f0;box-shadow:0 6px 20px rgba(15,23,42,0.04);}");
        out.println(".table thead th{background:#f1f5f9;color:#475569;font-size:12.5px;text-transform:uppercase;letter-spacing:0.5px;border-bottom:none;padding:12px 14px;white-space:nowrap;}");
        out.println(".table tbody td{vertical-align:middle;padding:12px 14px;font-size:14px;}");
        out.println(".page-btn{border:1px solid #cbd5e1;background:#fff;color:#334155;padding:5px 12px;border-radius:8px;font-size:13px;font-weight:600;cursor:pointer;transition:0.2s;}");
        out.println(".page-btn:hover:not(:disabled){background:#eff6ff;border-color:#93c5fd;color:#2563eb;}");
        out.println(".page-btn.active{background:#2563eb;border-color:#2563eb;color:#fff;}");
        out.println(".page-btn:disabled{opacity:0.5;cursor:not-allowed;}");
        out.println(".toolbar-right{display:flex;align-items:center;gap:8px;flex-wrap:wrap;}");
        out.println(".search-box{width:210px;border-radius:8px;}");
        out.println(".status-banner{display:flex;align-items:center;justify-content:space-between;padding:12px 16px;border-radius:10px;margin-bottom:18px;font-size:14px;font-weight:600;transition:opacity 0.3s ease;}");
        out.println(".status-success{background:#ecfdf5;color:#065f46;border:1px solid #a7f3d0;}");
        out.println(".status-error{background:#fef2f2;color:#991b1b;border:1px solid #fecaca;}");
        out.println("@media(max-width:576px){body{padding:12px;}.panel-card{padding:16px;border-radius:12px;}.toolbar-right{width:100%;}.search-box{flex:1;min-width:140px;}}");
        out.println("</style></head><body>");

        out.println("<div class='panel-card'>");

        if ("updated".equals(msg)) {
            out.println("  <div id='statusAlert' class='status-banner status-success'><span><i class='bi bi-check-circle-fill me-2'></i>Exam updated successfully!</span><button type='button' class='btn-close btn-sm' onclick=\"this.parentElement.remove()\"></button></div>");
        } else if ("deleted".equals(msg)) {
            out.println("  <div id='statusAlert' class='status-banner status-success'><span><i class='bi bi-check-circle-fill me-2'></i>Exam deleted successfully!</span><button type='button' class='btn-close btn-sm' onclick=\"this.parentElement.remove()\"></button></div>");
        } else if ("update_error".equals(msg)) {
            out.println("  <div id='statusAlert' class='status-banner status-error'><span><i class='bi bi-exclamation-triangle-fill me-2'></i>Failed to update exam. Please try again.</span><button type='button' class='btn-close btn-sm' onclick=\"this.parentElement.remove()\"></button></div>");
        } else if ("delete_error".equals(msg)) {
            out.println("  <div id='statusAlert' class='status-banner status-error'><span><i class='bi bi-exclamation-triangle-fill me-2'></i>Failed to delete exam. Please try again.</span><button type='button' class='btn-close btn-sm' onclick=\"this.parentElement.remove()\"></button></div>");
        }

        out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center gap-3 mb-4'>");
        out.println("    <div><h4 class='fw-bold m-0'><i class='bi bi-journal-check text-primary me-2'></i>Exams List</h4><span class='text-muted small'>Total Configured Exams: " + list.size() + "</span></div>");
        out.println("    <div class='toolbar-right'>");
        out.println("      <input type='text' id='searchInput' onkeyup='filterTable()' class='form-control form-control-sm search-box' placeholder='Search exam...'>");
        out.println("      <a href='addExam.html' class='btn btn-primary btn-sm fw-semibold px-3 py-2 rounded-2 text-nowrap'><i class='bi bi-plus-lg me-1'></i> Add Exam</a>");
        out.println("    </div>");
        out.println("  </div>");

        out.println("  <div class='table-responsive'>");
        out.println("  <table class='table table-hover align-middle mb-0'>");
        out.println("    <thead><tr><th>ID</th><th>Exam Name</th><th>Subject ID</th><th>Questions</th><th>Total Marks</th><th>Duration</th><th class='text-end'>Update / Delete</th></tr></thead>");
        out.println("    <tbody id='tableBody'>");

        for (ExamModel e : list) {
            out.println("<tr>");
            out.println("  <td class='fw-bold text-secondary'>#" + e.getExamId() + "</td>");
            out.println("  <td class='fw-semibold'>" + e.getExamName() + "</td>");
            out.println("  <td><span class='badge bg-light text-dark border'>Sub #" + e.getSubjectId() + "</span></td>");
            out.println("  <td>" + e.getTotalQuestions() + " Qs</td>");
            out.println("  <td><span class='badge bg-primary-subtle text-primary'>" + e.getTotalMarks() + " Marks</span></td>");
            out.println("  <td><i class='bi bi-clock me-1 text-muted'></i>" + e.getExamDuration() + " min</td>");
            out.println("  <td class='text-end text-nowrap'>");
            out.println("    <a href='updateExam?id=" + e.getExamId() + "' class='btn btn-outline-primary btn-sm me-1'><i class='bi bi-pencil-square'></i> Update</a>");
            out.println("    <a href='deleteExam?id=" + e.getExamId() + "' class='btn btn-outline-danger btn-sm' onclick='return confirm(\"Delete this exam?\")'><i class='bi bi-trash'></i> Delete</a>");
            out.println("  </td>");
            out.println("</tr>");
        }

        out.println("    </tbody></table></div>");

        // PAGINATION BAR
        out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center mt-3 pt-3 border-top'>");
        out.println("    <div class='text-muted small fw-semibold' id='pageInfo'></div>");
        out.println("    <div class='d-flex align-items-center gap-1' id='paginationControls'></div>");
        out.println("  </div>");

        out.println("</div>");

        out.println("<script>");
        out.println("const alertEl = document.getElementById('statusAlert');");
        out.println("if(alertEl){");
        out.println("  if(window.history.replaceState){ window.history.replaceState(null, '', window.location.pathname); }");
        out.println("  setTimeout(() => { alertEl.style.opacity = '0'; setTimeout(() => alertEl.remove(), 300); }, 3500);");
        out.println("}");
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
