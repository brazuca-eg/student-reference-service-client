package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import com.nure.kravchenko.student.reference.client.service.WorkerService;
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
import java.util.Objects;

@WebServlet("/workerRequests")
public class WorkerRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 4857955056132617622L;

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

        List<WorkerRequestDto> waitingRequestDtos = workerService.getNonAssignedRequestsByWorkerFaculty(id, token);
        req.setAttribute("waitingRequests", waitingRequestDtos);

        if (Objects.nonNull(req.getParameter("downloadReport"))) {
            String s3FileName = req.getParameter("s3FileName");
            req.getSession().setAttribute("s3FileName", s3FileName);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/download");
            dispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_requests.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");

        if (Objects.nonNull(req.getParameter("approveRequest"))) {
            Long requestId = Long.valueOf(req.getParameter("approveRequest"));
            workerService.approveRequest(id, requestId, true, StringUtils.EMPTY, token);
        }
        if (Objects.nonNull(req.getParameter("denyRequestButton"))) {
            Long deniedRequestId = Long.valueOf(req.getParameter("deniedRequestId"));
            String comment = req.getParameter("deniedComment");
            workerService.approveRequest(id, deniedRequestId, false, comment, token);
        }

        doGet(req, resp);
    }
}
