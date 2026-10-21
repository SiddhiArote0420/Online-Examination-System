package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.service.QuestionService;


@WebServlet("/deleteQuestion")
public class DeleteQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {

	            int id = Integer.parseInt(request.getParameter("id"));

	            QuestionService service = new QuestionService();
	            boolean result = service.deleteQuestion(id);

	            if (result) {
	                response.sendRedirect(request.getContextPath() + "/viewQuestions");
	            } else {
	                response.getWriter().println("<h3>Delete Failed</h3>");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("<h3>Error in Delete Operation</h3>");
	        }
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
