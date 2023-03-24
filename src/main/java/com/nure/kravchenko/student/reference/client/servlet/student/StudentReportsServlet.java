package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.RequestDto;
import com.nure.kravchenko.student.reference.client.server.RequestType;
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
import java.util.List;
import java.util.Objects;

@WebServlet("/studentReports")
public class StudentReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = annotationConfigApplicationContext
                .getBean("studentService", StudentService.class);

        String requestFilter = null;
        if (Objects.nonNull(req.getParameter("reasonNameFilter"))) {
            requestFilter = "reasonName";
        }
        List<RequestDto> requests = studentService.getRequestForStudent(id, RequestType.APPROVED, requestFilter, token);
        req.setAttribute("requests", requests);

        if (Objects.nonNull(req.getParameter("downloadReport"))) {
            String s3FileName = req.getParameter("s3FileName");
            req.getSession().setAttribute("s3FileName", s3FileName);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/download");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_reports.jsp");
        requestDispatcher.forward(req, resp);
    }

}
