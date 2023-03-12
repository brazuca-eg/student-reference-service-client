package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.server.StudentDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/student")
public class WatchStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        StudentDto studentDto = (StudentDto) session.getAttribute("student");
        req.setAttribute("student", studentDto);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_show_student.jsp");
        requestDispatcher.forward(req, resp);
    }

}
