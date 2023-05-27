package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.server.RequestDto;
import com.nure.kravchenko.student.reference.client.server.RequestType;
import com.nure.kravchenko.student.reference.client.service.StudentService;
import org.apache.commons.lang3.StringUtils;

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

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.STUDENT_REQUESTS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.STUDENT_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.STUDENT_REQUESTS;

@WebServlet(STUDENT_REQUESTS)
public class StudentRequestsServlet extends HttpServlet {

    private static final long serialVersionUID = 5641637694892996002L;

    private StudentService studentService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.studentService = (StudentService) ctx.getAttribute(STUDENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        List<RequestDto> studentRequests = studentService
                .getRequestForStudent(id, RequestType.NEW, StringUtils.EMPTY, token);
        req.setAttribute("studentRequests", studentRequests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(STUDENT_REQUESTS_PAGE);
        requestDispatcher.forward(req, resp);
    }

}
