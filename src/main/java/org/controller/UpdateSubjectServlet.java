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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html><head>");

		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");

		out.println("<style>");
		out.println("body{background:#f4f6fb;font-family:Arial;}");
		out.println(".box{width:400px;margin:60px auto;background:white;padding:25px;border-radius:12px;box-shadow:0 3px 10px rgba(0,0,0,0.1);}");
		out.println("</style>");

		out.println("</head><body>");

		out.println("<div class='box'>");

		out.println("<h4>Update Subject</h4>");

		out.println("<form action='updateSubject' method='post'>");

		out.println("<input type='hidden' name='id' value='"+s.getSubjectId()+"'>");

		out.println("<input class='form-control mb-3' name='subjectName' value='"+s.getSubjectName()+"'>");

		out.println("<button class='btn btn-warning w-100'>Update</button>");

		out.println("</form>");

		out.println("</div>");

		out.println("</body></html>");
		}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("subjectName");

		SubjectRepository repo = new SubjectRepository();
		repo.updateSubject(id, name);

		response.sendRedirect("viewSubject");
		}

}
