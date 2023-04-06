package com.nure.kravchenko.student.reference.client.servlet.auth;

import com.nure.kravchenko.student.reference.client.payload.RegistrationDto;
import com.nure.kravchenko.student.reference.client.service.AuthService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 538442085280973742L;

    private AuthService authService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.authService = (AuthService) ctx.getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/register.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (Objects.nonNull(req.getParameter("submitButton"))) {
            String password1 = req.getParameter("password");
            String password2 = req.getParameter("password2");
            if (!StringUtils.equals(password1, password2)) {
                req.setAttribute("error", "Passwords should match with each other");
                doGet(req, resp);
            }
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setEmail(req.getParameter("email"));
            registrationDto.setPassword(password1);
            registrationDto.setName(req.getParameter("name"));
            registrationDto.setSurname(req.getParameter("surname"));
            registrationDto.setFatherhood(req.getParameter("fatherhood"));
            char gender = 'M';
            if (StringUtils.equalsIgnoreCase(req.getParameter("gender"), "Female")) {
                gender = 'F';
            }
            registrationDto.setGender(gender);
            String role = "Student";
            if (StringUtils.equalsIgnoreCase(req.getParameter("role"), "Worker")) {
                role = "WORKER";
            }
            registrationDto.setRole(role);

            boolean registerResult = authService.register(registrationDto);
            if (registerResult) {
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                req.setAttribute("error", "Error while creating");
                doGet(req, resp);
            }
        }
    }

}
