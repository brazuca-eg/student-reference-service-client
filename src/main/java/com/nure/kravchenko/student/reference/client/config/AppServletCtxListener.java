package com.nure.kravchenko.student.reference.client.config;

import com.nure.kravchenko.student.reference.client.service.AdminService;
import com.nure.kravchenko.student.reference.client.service.AuthService;
import com.nure.kravchenko.student.reference.client.service.StudentService;
import com.nure.kravchenko.student.reference.client.service.WorkerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.*;

@WebListener
public class AppServletCtxListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AuthService authService = annotationConfigApplicationContext
                .getBean(AUTH_SERVICE, AuthService.class);
        StudentService studentService = annotationConfigApplicationContext
                .getBean(STUDENT_SERVICE, StudentService.class);
        WorkerService workerService = annotationConfigApplicationContext
                .getBean(WORKER_SERVICE, WorkerService.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean(ADMIN_SERVICE, AdminService.class);

        context.setAttribute(AUTH_SERVICE, authService);
        context.setAttribute(STUDENT_SERVICE, studentService);
        context.setAttribute(WORKER_SERVICE, workerService);
        context.setAttribute(ADMIN_SERVICE, adminService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.removeAttribute(AUTH_SERVICE);
        context.removeAttribute(STUDENT_SERVICE);
        context.removeAttribute(WORKER_SERVICE);
        context.removeAttribute(ADMIN_SERVICE);
    }
}
