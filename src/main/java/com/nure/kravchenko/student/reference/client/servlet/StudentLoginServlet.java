package com.nure.kravchenko.student.reference.client.servlet;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.model.Student;
import com.nure.kravchenko.student.reference.client.payload.StudentLoginPayload;
import com.nure.kravchenko.student.reference.client.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/student/login")
public class StudentLoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getParameter("submitButton"))) {
            if (StringUtils.isBlank(req.getParameter("email")) || StringUtils.isBlank(req.getParameter("password"))) {
                req.setAttribute("error", "All fields should be entered for login operation");
                doGet(req, resp);
            } else {
                AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
                StudentService studentService = annotationConfigApplicationContext.getBean("studentService", StudentService.class);

                StudentLoginPayload studentLoginPayload = new StudentLoginPayload();
                studentLoginPayload.setEmail(req.getParameter("email"));
                studentLoginPayload.setPassword(req.getParameter("password"));

                Student student = studentService.login(studentLoginPayload);
                if (student == null) {
                    req.setAttribute("error", "No user with such params");
                    doGet(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    session.setAttribute("current", student);
                    resp.sendRedirect(req.getContextPath() + "/main");
                }
            }
        }
    }
}
