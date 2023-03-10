package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
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
import java.util.Objects;

@WebServlet("/admin/main")
public class AdminMainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean("adminService", AdminService.class);

        WorkerDto workerDto = adminService.getAdminById(id, token);
        if (Objects.nonNull(workerDto)) {
            req.setAttribute("current", workerDto);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_main.jsp");
        requestDispatcher.forward(req, resp);
    }

}
