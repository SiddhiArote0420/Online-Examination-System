package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.model.ExamModel;
import org.service.ExamService;


@WebServlet("/examList")
public class ExamListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1. SESSION CHECK (VERY IMPORTANT)
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("studentLogin.html");
            return;
        }

        int studentId = (Integer) session.getAttribute("studentId");

        // 2. SERVICE CALL
        ExamService service = new ExamService();
        List<ExamModel> list = service.getExamWithStatus(studentId);

        // 3. UI START
        out.println("<html>");
        out.println("<head>");
        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head>");

        out.println("<body class='container mt-4'>");

        out.println("<h2 class='text-center mb-4'>Available Exams</h2>");

        out.println("<table class='table table-bordered table-hover text-center'>");
        out.println("<tr class='table-dark'><th>Exam Name</th><th>Status</th><th>Action</th></tr>");

        // 4. LOOP DATA
        for (ExamModel e : list) {

            out.println("<tr>");

            out.println("<td>" + e.getExamName() + "</td>");
            out.println("<td>" + e.getStatus() + "</td>");

            // 5. BUSINESS RULE
            if ("PENDING".equalsIgnoreCase(e.getStatus())) {
                out.println("<td><a class='btn btn-success btn-sm' href='startExam?examId="
                        + e.getExamId() + "'>Start Exam</a></td>");
            } else {
                out.println("<td><button class='btn btn-secondary btn-sm' disabled>Completed</button></td>");
            }

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
