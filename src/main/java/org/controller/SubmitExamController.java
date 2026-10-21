package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.model.ResultModel;
import org.service.ResultService;

@WebServlet("/submitExam")
public class SubmitExamController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null) {
            response.sendRedirect("studentLogin.html");
            return;
        }

        Integer studentId = (Integer) session.getAttribute("studentId");
        Integer examId = (Integer) session.getAttribute("examId");

        ResultModel result =
                (ResultModel) session.getAttribute("result");

        if(result == null) {
            response.sendRedirect("examList");
            return;
        }

        ResultService service = new ResultService();

        service.saveFinalResult(
                studentId,
                examId,
                result.getTotalMarks(),
                result.getObtainedMarks()
        );

        response.sendRedirect("result");
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}