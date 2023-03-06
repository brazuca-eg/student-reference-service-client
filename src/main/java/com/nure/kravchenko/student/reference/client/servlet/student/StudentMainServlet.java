package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = annotationConfigApplicationContext
                .getBean("studentService", StudentService.class);

        StudentDto studentDto = studentService.getStudentById(id, token);
        if (Objects.nonNull(studentDto)) {
            req.setAttribute("current", studentDto);
            StudentGroupDto studentGroupDto = studentService.getGroupByStudent(id, token);
            if (Objects.nonNull(studentGroupDto)) {
                req.setAttribute("group", studentGroupDto);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_main.jsp");
        requestDispatcher.forward(req, resp);
    }

}
