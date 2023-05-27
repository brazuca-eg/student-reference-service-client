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
import java.util.List;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.ADMIN_APPROVAL_STUDENTS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.ADMIN_SHOW_WAITING_STUDENTS;

@WebServlet(ADMIN_SHOW_WAITING_STUDENTS)
public class ApproveStudentsServlet extends HttpServlet {

    private static final long serialVersionUID = 5178997234001144463L;

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

        List<StudentDto> waitingApprovalStudents = adminService.getWaitingApproveStudents(token);
        req.setAttribute("waitingApprovalStudents", waitingApprovalStudents);

        List<StudentGroupDto> studentGroups = adminService.getAllGroups(token);
        req.setAttribute("studentGroups", studentGroups);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher(ADMIN_APPROVAL_STUDENTS_PAGE);
        requestDispatcher.forward(req, resp);
    }

}
