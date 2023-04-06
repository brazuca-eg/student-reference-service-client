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
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/searchRequest")
public class FindRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 660788011919846701L;

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

        if (Objects.nonNull(req.getParameter("clearButton"))) {
            req.setAttribute("assignedReports", null);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_search_request.jsp");
            requestDispatcher.forward(req, resp);
        }


        if (Objects.nonNull(req.getParameter("searchButton"))) {
            List<WorkerRequestDto> assignedReports = workerService.getAssignedWorkerRequests(id, true, token);
            String searchParams = req.getParameter("searchRequest");
            String filter = req.getParameter("searchParam");
            if (filter.equalsIgnoreCase("byStudent")) {
                assignedReports = assignedReports.stream()
                        .filter(workerRequestDto -> workerRequestDto.getStudentFullName().contains(searchParams))
                        .collect(Collectors.toList());
            }
            if (filter.equalsIgnoreCase("byGroup")) {
                assignedReports = assignedReports.stream()
                        .filter(workerRequestDto -> workerRequestDto.getGroupName().contains(searchParams))
                        .collect(Collectors.toList());
            }
            if (filter.equalsIgnoreCase("byDate")) {
                LocalDate localDate = LocalDate.parse(searchParams);

                assignedReports = assignedReports.stream()
                        .filter(workerRequestDto -> workerRequestDto.getEndDate().getYear() == localDate.getYear())
                        .filter(workerRequestDto -> workerRequestDto.getEndDate().getMonth() == localDate.getMonth())
                        .filter(workerRequestDto -> workerRequestDto.getEndDate().getDayOfMonth() == localDate.getDayOfMonth())
                        .collect(Collectors.toList());
            }

            req.setAttribute("assignedReports", assignedReports);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_search_request.jsp");
            requestDispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_search_request.jsp");
        requestDispatcher.forward(req, resp);
    }

}
