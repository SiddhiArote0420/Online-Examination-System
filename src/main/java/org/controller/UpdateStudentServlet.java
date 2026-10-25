package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.model.StudentModel;
import org.service.StudentService;

@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// =========================
	// SHOW UPDATE FORM
	// =========================

	protected void doGet(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		StudentService service = new StudentService();
		StudentModel s = service.getStudentById(id);

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(getForm(s));
	}

	// =========================
	// UPDATE STUDENT
	// =========================

	protected void doPost(
	HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String course = request.getParameter("course");
		String mobile = request.getParameter("mobile");

		StudentService service = new StudentService();
		boolean status = service.updateStudent(id, name, email, course, mobile);

		if (status) {
			response.sendRedirect("viewStudents?msg=updated");
		} else {
			response.sendRedirect("viewStudents?msg=update_error");
		}
	}

	// =========================
	// FORM UI
	// =========================

	private String getForm(StudentModel s) {
		return
		"<!DOCTYPE html>"
		+ "<html lang='en'>"
		+ "<head>"
		+ "<meta charset='UTF-8'>"
		+ "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
		+ "<title>Update Student</title>"
		+ "<link href='https://fonts.googleapis.com/css2?family=Plus+Jakarta+Sans:wght@400;500;600;700&display=swap' rel='stylesheet'>"
		+ "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>"
		+ "<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css' rel='stylesheet'>"
		+ "<style>"
		+ "body{"
		+ "background:#f8fafc;"
		+ "font-family:'Plus Jakarta Sans',sans-serif;"
		+ "padding:24px;"
		+ "color:#0f172a;"
		+ "}"
		+ ".card-box{"
		+ "width:100%;"
		+ "max-width:560px;"
		+ "margin:15px auto;"
		+ "background:white;"
		+ "padding:32px;"
		+ "border-radius:18px;"
		+ "border:1px solid #e2e8f0;"
		+ "box-shadow:0 10px 30px rgba(15,23,42,0.05);"
		+ "}"
		+ ".title{"
		+ "margin:0;"
		+ "font-weight:700;"
		+ "color:#0f172a;"
		+ "}"
		+ ".btn-update{"
		+ "background:linear-gradient(135deg,#2563eb,#1d4ed8);"
		+ "color:white;"
		+ "font-weight:700;"
		+ "padding:11px;"
		+ "border-radius:10px;"
		+ "border:none;"
		+ "}"
		+ ".btn-update:hover{"
		+ "opacity:0.95;"
		+ "color:white;"
		+ "}"
		+ "@media(max-width:576px){body{padding:12px;}.card-box{padding:20px 16px;border-radius:14px;}}"
		+ "</style>"
		+ "</head>"
		+ "<body>"
		+ "<div class='card-box'>"
		+ "<div class='d-flex flex-wrap justify-content-between align-items-center gap-2 mb-4 pb-3 border-bottom'>"
		+ "  <div class='d-flex align-items-center gap-3'>"
		+ "    <div style='width:44px;height:44px;border-radius:12px;background:#eff6ff;color:#2563eb;display:flex;align-items:center;justify-content:center;font-size:20px;'><i class='bi bi-person-gear'></i></div>"
		+ "    <div><h4 class='title'>Update Student</h4><span class='text-muted small'>Modify student account information</span></div>"
		+ "  </div>"
		+ "  <a href='viewStudents' class='btn btn-outline-secondary btn-sm fw-semibold'><i class='bi bi-arrow-left me-1'></i>Back</a>"
		+ "</div>"
		+ "<form method='post' action='updateStudent'>"
		+ "<input type='hidden' name='id' value='" + s.getStudentId() + "'/>"
		+ "<div class='mb-3'>"
		+ "<label class='form-label'>Student Name</label>"
		+ "<input type='text' class='form-control' name='name' value='" + s.getStudentName() + "' required>"
		+ "</div>"
		+ "<div class='mb-3'>"
		+ "<label class='form-label'>Email</label>"
		+ "<input type='email' class='form-control' name='email' value='" + s.getEmail() + "' required>"
		+ "</div>"
		+ "<div class='mb-3'>"
		+ "<label class='form-label'>Course</label>"
		+ "<input type='text' class='form-control' name='course' value='" + s.getCourse() + "' required>"
		+ "</div>"
		+ "<div class='mb-3'>"
		+ "<label class='form-label'>Mobile</label>"
		+ "<input type='text' class='form-control' name='mobile' value='" + s.getMobile() + "' required>"
		+ "</div>"
		+ "<div class='d-grid gap-2'>"
		+ "<button type='submit' class='btn btn-update'>Update Student</button>"
		+ "<a href='viewStudents' class='btn btn-secondary'>Back</a>"
		+ "</div>"
		+ "</form>"
		+ "</div>"
		+ "</body>"
		+ "</html>";
	}
}
