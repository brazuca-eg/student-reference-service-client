package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import com.nure.kravchenko.student.reference.client.service.WorkerService;

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

@WebServlet("/workerDeniedReports")
public class WorkerDeniedReportsServlet extends HttpServlet {

    private static final long serialVersionUID = 1169149075048057707L;

    private WorkerService workerService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.workerService = (WorkerService) ctx.getAttribute("workerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        List<WorkerRequestDto> assignedDeniedReports = workerService
                .getAssignedWorkerRequests(id, false, null, token);
        req.setAttribute("assignedDeniedReports", assignedDeniedReports);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_denied_reports.jsp");
        requestDispatcher.forward(req, resp);
    }
}
