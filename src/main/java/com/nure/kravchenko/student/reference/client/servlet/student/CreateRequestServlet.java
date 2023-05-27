package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.payload.CreateRequestDto;
import com.nure.kravchenko.student.reference.client.server.ReasonDto;
import com.nure.kravchenko.student.reference.client.service.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.STUDENT_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.STUDENT_CREATE_REQUEST;

@WebServlet(STUDENT_CREATE_REQUEST)
public class CreateRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 7856680687685113511L;

    private StudentService studentService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.studentService = (StudentService) ctx.getAttribute(STUDENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");

        List<ReasonDto> reasons = studentService.getAllRequestReasonsForStudent(token);
        req.setAttribute("reasons", reasons);
        req.setAttribute("student", studentService.getStudentById((Long) session.getAttribute("userId"), token));

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_create_request.jsp");
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

            studentService.createRequest(id, createRequestDto, token);

            req.setAttribute("response", "Запит на надання довідки створено");
            doGet(req, resp);
        }
    }
}
