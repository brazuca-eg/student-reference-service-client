package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.payload.ApproveWorkerDto;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.WorkerDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;
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

@WebServlet("/adminApproveWorkers")
public class ApproveWorkersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean("adminService", AdminService.class);

        List<WorkerDto> waitingApprovalWorkers = adminService.getWaitingApproveWorkers(token);
        req.setAttribute("waitingApprovalWorkers", waitingApprovalWorkers);

        List<FacultyDto> faculties = adminService.getAllFaculties(token);
        req.setAttribute("faculties", faculties);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_approval_workers.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        if (Objects.nonNull(req.getParameter("approveWorkerButton"))) {
            AnnotationConfigApplicationContext annotationConfigApplicationContext =
                    new AnnotationConfigApplicationContext(AppConfig.class);
            AdminService adminService = annotationConfigApplicationContext
                    .getBean("adminService", AdminService.class);

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
