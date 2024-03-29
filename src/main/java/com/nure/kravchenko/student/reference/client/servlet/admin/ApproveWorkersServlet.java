package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.payload.ApproveWorkerDto;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.WorkerDto;
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
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.ADMIN_APPROVAL_WORKERS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.ADMIN_APPROVE_WORKERS;

@WebServlet(ADMIN_APPROVE_WORKERS)
public class ApproveWorkersServlet extends HttpServlet {

    private static final long serialVersionUID = -3039044990665628912L;

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

        List<WorkerDto> waitingApprovalWorkers = adminService.getWaitingApproveWorkers(token);
        req.setAttribute("waitingApprovalWorkers", waitingApprovalWorkers);

        List<FacultyDto> faculties = adminService.getAllFaculties(token);
        req.setAttribute("faculties", faculties);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(ADMIN_APPROVAL_WORKERS_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");

        if (Objects.nonNull(req.getParameter("approveWorkerButton"))) {
            Long workerId = Long.valueOf(req.getParameter("workerId"));
            String jobTitle = req.getParameter("jobTitle");
            Long facultyId = Long.valueOf(req.getParameter("faculty"));
            ApproveWorkerDto approveWorkerDto = new ApproveWorkerDto();
            approveWorkerDto.setJobTitle(jobTitle);
            approveWorkerDto.setFacultyId(facultyId);

            adminService.approveWorkerRegistration(workerId, approveWorkerDto, token);
            doGet(req, resp);
        }
    }

}
