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


@WebServlet("/updateSubject")
public class UpdateSubjectServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		SubjectRepository repo = new SubjectRepository();
		SubjectModel s = repo.getSubjectById(id);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html><html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><title>Update Subject</title>");
		out.println("<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>");
		out.println("<style>");
		out.println("body{background:#f8fafc;font-family:'Plus Jakarta Sans',sans-serif;padding:24px;color:#0f172a;}");
		out.println(".box{width:100%;max-width:520px;margin:20px auto;background:white;padding:32px;border-radius:18px;border:1px solid #e2e8f0;box-shadow:0 10px 30px rgba(15,23,42,0.05);}");
		out.println(".btn-upd{background:linear-gradient(135deg,#2563eb,#1d4ed8);color:#fff;border:none;font-weight:700;padding:11px;border-radius:10px;}");
		out.println(".btn-upd:hover{opacity:0.95;color:#fff;}");
		out.println("@media(max-width:576px){body{padding:12px;}.box{padding:20px 16px;border-radius:14px;}}");
		out.println("</style>");
		out.println("</head><body>");

		out.println("<div class='box'>");
		out.println("  <div class='d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4 pb-3 border-bottom'>");
		out.println("    <div class='d-flex align-items-center gap-3'>");
		out.println("      <div style='width:44px;height:44px;border-radius:12px;background:#eff6ff;color:#2563eb;display:flex;align-items:center;justify-content:center;font-size:20px;'><i class='bi bi-pencil-square'></i></div>");
		out.println("      <div><h4 class='fw-bold m-0'>Update Subject</h4><span class='text-muted small'>Modify subject details</span></div>");
		out.println("    </div>");
		out.println("    <a href='viewSubject' class='btn btn-outline-secondary btn-sm fw-semibold'><i class='bi bi-arrow-left me-1'></i>Back</a>");
		out.println("  </div>");

		out.println("<form action='updateSubject' method='post'>");
		out.println("<input type='hidden' name='id' value='" + s.getSubjectId() + "'>");
		out.println("<label class='form-label fw-semibold small'>Subject Name</label>");
		out.println("<input class='form-control mb-3 py-2' name='subjectName' value='" + s.getSubjectName() + "' required>");
		out.println("<button class='btn btn-upd w-100'><i class='bi bi-check2-circle me-1'></i> Update Subject</button>");
		out.println("</form>");

		out.println("</div>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("subjectName");

		SubjectRepository repo = new SubjectRepository();
		boolean status = repo.updateSubject(id, name);

		if (status) {
			response.sendRedirect("viewSubject?msg=updated");
		} else {
			response.sendRedirect("viewSubject?msg=update_error");
		}
	}

}
