package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import com.nure.kravchenko.student.reference.client.service.WorkerService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.WORKER_REQUESTS_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.WORKER_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.DOWNLOAD_PATH;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.WORKER_REQUESTS;

@WebServlet(WORKER_REQUESTS)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class WorkerRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 4857955056132617622L;

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
        if (Objects.nonNull(req.getParameter("requestFilterButton"))) {
            requestFilter = req.getParameter("requestFilter");
        }

        List<WorkerRequestDto> waitingRequestDtos = workerService
                .getNonAssignedRequestsByWorkerFaculty(id, requestFilter, token);

        req.setAttribute("waitingRequests", waitingRequestDtos);

        if (Objects.nonNull(req.getParameter("downloadReport"))) {
            String s3FileName = req.getParameter("s3FileName");
            req.getSession().setAttribute("s3FileName", s3FileName);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(DOWNLOAD_PATH);
            dispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WORKER_REQUESTS_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");
        if (Objects.nonNull(req.getParameter("approveRequest"))) {
            Part part = req.getPart("signFile");
            String imageName = part.getSubmittedFileName();
            if(StringUtils.isNoneBlank(imageName)){
                if(!imageName.endsWith("jpg")){
                    req.setAttribute("errorResponse", "Підпис має бути у форматі \"jpg\"");
                    doGet(req, resp);
                }
            } else {
                req.setAttribute("errorResponse",
                        "Для підтвердження надання довідки необхідно завантажити електронний підрис");
                doGet(req, resp);
            }
            InputStream fileContent = part.getInputStream();
            byte[] imageBytes = fileContent.readAllBytes();

            Long requestId = Long.valueOf(req.getParameter("approveRequest"));
            workerService.approveRequest(id, requestId, true, StringUtils.EMPTY, imageBytes, token);
        }
        if (Objects.nonNull(req.getParameter("denyRequestButton"))) {
            Long deniedRequestId = Long.valueOf(req.getParameter("deniedRequestId"));
            String comment = req.getParameter("deniedComment");
            workerService.approveRequest(id, deniedRequestId, false, comment, null, token);
        }
        doGet(req, resp);
    }
}
