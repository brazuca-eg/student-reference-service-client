package com.nure.kravchenko.student.reference.client.servlet.auth;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.model.Role;
import com.nure.kravchenko.student.reference.client.payload.LoginDto;
import com.nure.kravchenko.student.reference.client.server.UserLoggedInDto;
import com.nure.kravchenko.student.reference.client.service.AuthService;
import org.apache.commons.lang3.StringUtils;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getParameter("submitButton"))) {
            if (StringUtils.isBlank(req.getParameter("email")) || StringUtils.isBlank(req.getParameter("password"))) {
                req.setAttribute("error", "All fields should be entered for login operation");
                doGet(req, resp);
            } else {
                AnnotationConfigApplicationContext annotationConfigApplicationContext =
                        new AnnotationConfigApplicationContext(AppConfig.class);
                AuthService authService = annotationConfigApplicationContext
                        .getBean("authService", AuthService.class);

                LoginDto loginDto = new LoginDto();
                loginDto.setEmail(req.getParameter("email"));
                loginDto.setPassword(req.getParameter("password"));

                UserLoggedInDto userLoggedInDto = authService.login(loginDto);
                if (Objects.isNull(userLoggedInDto)) {
                    req.setAttribute("error", "No user with such params");
                    doGet(req, resp);
                } else {
                    boolean isApprovedAccount = userLoggedInDto.isApproved();
                    String role = userLoggedInDto.getRole();
                    if (StringUtils.equalsIgnoreCase(Role.STUDENT.name(), role)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("role", role);
                        session.setAttribute("userId", userLoggedInDto.getId());
                        session.setAttribute("token", userLoggedInDto.getToken());
                    }
                    if (isApprovedAccount) {
                        resp.sendRedirect(req.getContextPath() + "/student/main");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                    }
                }
            }
        }
    }

}
