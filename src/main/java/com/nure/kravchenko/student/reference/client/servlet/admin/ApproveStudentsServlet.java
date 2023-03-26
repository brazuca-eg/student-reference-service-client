package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminShowWaitingStudents")
public class ApproveStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean("adminService", AdminService.class);


        List<StudentDto> waitingApprovalStudents = adminService.getWaitingApproveStudents(token);
        req.setAttribute("waitingApprovalStudents", waitingApprovalStudents);

        List<StudentGroupDto> studentGroups = adminService.getAllGroups(token);
        req.setAttribute("studentGroups", studentGroups);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_approval_students.jsp");
        requestDispatcher.forward(req, resp);
    }

}
