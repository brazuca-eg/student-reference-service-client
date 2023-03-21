package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import com.nure.kravchenko.student.reference.client.service.WorkerService;
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

@WebServlet("/workerDeniedReports")
public class WorkerDeniedReportsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        WorkerService workerService = annotationConfigApplicationContext
                .getBean("workerService", WorkerService.class);

        List<WorkerRequestDto> assignedDeniedReports = workerService.getAssignedWorkerRequests(id, false, token);
        req.setAttribute("assignedDeniedReports", assignedDeniedReports);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_denied_reports.jsp");
        requestDispatcher.forward(req, resp);
    }
}
