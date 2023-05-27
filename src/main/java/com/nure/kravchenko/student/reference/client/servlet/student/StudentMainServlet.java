package com.nure.kravchenko.student.reference.client.servlet.student;

import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentToUniInfoDto;
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
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.STUDENT_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.STUDENT_MAIN;

@WebServlet(STUDENT_MAIN)
public class StudentMainServlet extends HttpServlet {

    private static final long serialVersionUID = 5473885369742362015L;

    private StudentService studentService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.studentService = (StudentService) ctx.getAttribute(STUDENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        StudentDto studentDto = studentService.getStudentById(id, token);
        if (Objects.nonNull(studentDto)) {
            req.setAttribute("current", studentDto);
            StudentToUniInfoDto studentToUniInfoDto = studentService.getUniInfoByStudent(id, token);
            if (Objects.nonNull(studentToUniInfoDto)) {
                req.setAttribute("uniInfo", studentToUniInfoDto);
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/student_main.jsp");
        requestDispatcher.forward(req, resp);
    }

}
