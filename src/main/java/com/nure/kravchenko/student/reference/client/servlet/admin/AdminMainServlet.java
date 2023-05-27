package com.nure.kravchenko.student.reference.client.servlet.admin;

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
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.ADMIN_MAIN;

@WebServlet(ADMIN_MAIN)
public class AdminMainServlet extends HttpServlet {

    private static final long serialVersionUID = -4486288083776439149L;

    private AdminService adminService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.adminService = (AdminService) ctx.getAttribute(ADMIN_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        WorkerDto workerDto = adminService.getAdminById(id, token);
        if (Objects.nonNull(workerDto)) {
            req.setAttribute("current", workerDto);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_main.jsp");
        requestDispatcher.forward(req, resp);
    }

}
