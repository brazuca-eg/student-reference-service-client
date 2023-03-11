package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.payload.CreateRequestDto;
import com.nure.kravchenko.student.reference.client.server.ReasonDto;
import com.nure.kravchenko.student.reference.client.server.RequestDto;
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

@WebServlet("/student/requests")
public class StudentRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService studentService = annotationConfigApplicationContext
                .getBean("studentService", StudentService.class);

        List<ReasonDto> reasons = studentService.getAllRequestReasonsForStudent(token);
        req.setAttribute("reasons", reasons);

        String requestFilter = null;
        if(Objects.nonNull(req.getParameter("reasonNameFilter"))){
            requestFilter = "reasonName";
        }
        List<RequestDto> requests = studentService.getRequestForStudent(id, requestFilter, token);
        req.setAttribute("requests", requests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_requests.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        if (Objects.nonNull(req.getParameter("submitButton"))) {
            String serialNumber = req.getParameter("serialNumber");
            String number = req.getParameter("number");
            String reasonName = req.getParameter("requestReason");
            CreateRequestDto createRequestDto = new CreateRequestDto();
            createRequestDto.setSerialNumber(serialNumber);
            createRequestDto.setNumber(number);
            createRequestDto.setReasonName(reasonName);

            AnnotationConfigApplicationContext annotationConfigApplicationContext =
                    new AnnotationConfigApplicationContext(AppConfig.class);
            StudentService studentService = annotationConfigApplicationContext
                    .getBean("studentService", StudentService.class);
            studentService.createRequest(id, createRequestDto, token);
            doGet(req, resp);
        }
    }

}
