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

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.STUDENT_DENIED_REQUESTS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.STUDENT_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.STUDENT_DENIED_REQUESTS;

@WebServlet(STUDENT_DENIED_REQUESTS)
public class StudentDeniedRequestsServlet extends HttpServlet {

    private static final long serialVersionUID = -8123129913040882413L;

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

        List<RequestDto> studentDeniedRequests = studentService
                .getRequestForStudent(id, RequestType.DENIED, StringUtils.EMPTY, token);
        req.setAttribute("studentDeniedRequests", studentDeniedRequests);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(STUDENT_DENIED_REQUESTS_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
