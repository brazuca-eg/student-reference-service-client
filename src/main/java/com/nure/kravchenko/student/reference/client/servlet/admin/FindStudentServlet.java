package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.ADMIN_SEARCH_STUDENTS;

@WebServlet(ADMIN_SEARCH_STUDENTS)
public class FindStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 3053046071705907785L;

    private AdminService adminService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.adminService = (AdminService) ctx.getAttribute(ADMIN_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        req.setCharacterEncoding("UTF-8");

        List<StudentGroupDto> studentGroups = adminService.getAllGroups(token);
        req.setAttribute("studentGroups", studentGroups);

        if (Objects.nonNull(req.getParameter("searchButton"))) {
            String groupName = req.getParameter("studentGroup");
            List<StudentDto> searchedStudents = adminService.getStudentsByGroup(groupName, token);
            req.setAttribute("searchedStudents", searchedStudents);
            redirect(req, resp);
        } else if (Objects.nonNull(req.getParameter("clearButton"))) {
            req.setAttribute("searchedStudents", Collections.emptyList());
            redirect(req, resp);
        } else {
            redirect(req, resp);
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_find_students.jsp");
        requestDispatcher.forward(req, resp);
    }
}
