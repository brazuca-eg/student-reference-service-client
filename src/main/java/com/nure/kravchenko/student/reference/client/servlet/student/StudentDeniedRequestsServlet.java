package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.RequestDto;
import com.nure.kravchenko.student.reference.client.server.RequestType;
import com.nure.kravchenko.student.reference.client.service.StudentService;
import org.apache.commons.lang3.StringUtils;
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

@WebServlet("/studentDeniedRequests")
public class StudentDeniedRequestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = annotationConfigApplicationContext
                .getBean("studentService", StudentService.class);

        List<RequestDto> studentDeniedRequests = studentService
                .getRequestForStudent(id, RequestType.DENIED, StringUtils.EMPTY, token);
        req.setAttribute("studentDeniedRequests", studentDeniedRequests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_denied_requests.jsp");
        requestDispatcher.forward(req, resp);
    }
}
