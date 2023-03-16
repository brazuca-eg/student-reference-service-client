package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.server.WorkerDto;
import com.nure.kravchenko.student.reference.client.service.StudentService;
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
import java.util.Objects;

@WebServlet("/worker")
public class WorkerMainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        WorkerService workerService = annotationConfigApplicationContext
                .getBean("workerService", WorkerService.class);

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
