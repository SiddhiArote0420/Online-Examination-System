package org.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.model.StudentModel;
import org.service.StudentService;


@WebServlet("/login")
public class LoginController extends HttpServlet {


@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {


    String email=request.getParameter("email");
    String password=request.getParameter("password");


    StudentService service=new StudentService();

    StudentModel student=service.login(email,password);


    if(student!=null){

        HttpSession session=request.getSession();

        session.setAttribute("studentId",
                student.getStudentId());

        session.setAttribute("studentName",
                student.getStudentName());


        response.sendRedirect("studentDashboard");

    }
    else{

        response.sendRedirect("StudLogin.html?error=Invalid Login");
    }

}

}