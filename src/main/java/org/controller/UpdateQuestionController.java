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


@WebServlet("/updateQuestion")
public class UpdateQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        QuestionService service = new QuestionService();
        QuestionModel q = service.getQuestionById(id);

        if (q == null) {
            out.println("Question not found!");
            return;
        }

        out.println("<html><head>");

        out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");

        out.println("</head><body class='bg-light'>");

        out.println("<div class='container mt-5'>");
        out.println("<div class='card p-4 shadow'>");

        out.println("<h3>Update Question</h3>");

        out.println("<form action='updateQuestionAction' method='post'>");

        out.println("<input type='hidden' name='questionId' value='" + q.getQuestionId() + "'>");

        out.println("Exam ID:");
        out.println("<input type='number' class='form-control' name='examId' value='" + q.getExamId() + "'><br>");

        out.println("Question:");
        out.println("<textarea class='form-control' name='questionText'>" + q.getQuestionText() + "</textarea><br>");

        out.println("Option 1:");
        out.println("<input class='form-control' name='option1' value='" + q.getOption1() + "'><br>");

        out.println("Option 2:");
        out.println("<input class='form-control' name='option2' value='" + q.getOption2() + "'><br>");

        out.println("Option 3:");
        out.println("<input class='form-control' name='option3' value='" + q.getOption3() + "'><br>");

        out.println("Option 4:");
        out.println("<input class='form-control' name='option4' value='" + q.getOption4() + "'><br>");

        out.println("Correct Answer:");
        out.println("<input class='form-control' name='correctAnswer' value='" + q.getCorrectAnswer() + "'><br>");

        out.println("<button class='btn btn-success'>Update Question</button>");

        out.println("</form>");

        out.println("</div></div>");

        out.println("</body></html>");
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
