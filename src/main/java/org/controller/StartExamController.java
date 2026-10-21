package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/startExam")
public class StartExamController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int examId = Integer.parseInt(request.getParameter("examId"));

        HttpSession session = request.getSession();

        session.setAttribute("examId", examId);
        session.setAttribute("index", 0);
        session.setAttribute("score", 0);

        // 30 minutes timer
        long endTime = System.currentTimeMillis() + (30 * 60 * 1000);

        session.setAttribute("endTime", endTime);

        response.sendRedirect("exam");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}