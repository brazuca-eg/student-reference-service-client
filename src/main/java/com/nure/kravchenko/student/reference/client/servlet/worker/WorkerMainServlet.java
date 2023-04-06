package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.WorkerDto;
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
import java.util.Objects;

@WebServlet("/worker")
public class WorkerMainServlet extends HttpServlet {

    private static final long serialVersionUID = -133624879455004386L;

    private WorkerService workerService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.workerService = (WorkerService) ctx.getAttribute("workerService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        WorkerDto workerDto = workerService.getWorkerById(id, token);
        if (Objects.nonNull(workerDto)) {
            req.setAttribute("current", workerDto);
            FacultyDto facultyDto = workerService.getWorkerFaculty(id, token);
            req.setAttribute("faculty", facultyDto);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/worker_main.jsp");
        requestDispatcher.forward(req, resp);
    }

}
