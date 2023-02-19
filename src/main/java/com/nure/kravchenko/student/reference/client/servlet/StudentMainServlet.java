package com.nure.kravchenko.student.reference.client.servlet;

import com.nure.kravchenko.student.reference.client.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/student/main")
public class StudentMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Student student = (Student) session.getAttribute("current");
        req.setAttribute("current", student);

        if(Objects.nonNull(student.getStudentGroup())){
            req.setAttribute("group", student.getStudentGroup());
        }

        //req.setAttribute("current", student);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
