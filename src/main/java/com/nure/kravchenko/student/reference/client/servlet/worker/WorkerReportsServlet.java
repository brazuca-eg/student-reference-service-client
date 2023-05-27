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
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.WORKER_REPORTS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.WORKER_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.WORKER_REPORTS;

@WebServlet(WORKER_REPORTS)
public class WorkerReportsServlet extends HttpServlet {

    private static final long serialVersionUID = 6648876982597062700L;

    private WorkerService workerService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.workerService = (WorkerService) ctx.getAttribute(WORKER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        String requestFilter = null;
        if(Objects.nonNull(req.getParameter("requestFilterButton"))){
            requestFilter = req.getParameter("requestFilter");
        }

        List<WorkerRequestDto> assignedReports = workerService
                .getAssignedWorkerRequests(id, true, requestFilter, token);
        req.setAttribute("assignedReports", assignedReports);

        if (Objects.nonNull(req.getParameter("downloadReport"))) {
            String s3FileName = req.getParameter("s3FileName");
            req.getSession().setAttribute("s3FileName", s3FileName);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/download");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WORKER_REPORTS_PAGE);
        requestDispatcher.forward(req, resp);
    }
}
