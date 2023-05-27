package com.nure.kravchenko.student.reference.client.servlet.auth;

import com.nure.kravchenko.student.reference.client.payload.RegistrationDto;
import com.nure.kravchenko.student.reference.client.service.AuthService;
import com.nure.kravchenko.student.reference.client.service.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.AUTH_REGISTER_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.AUTH_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.AUTH_LOGIN;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.AUTH_REGISTER;

@WebServlet(AUTH_REGISTER)
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 538442085280973742L;

    private AuthService authService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.authService = (AuthService) ctx.getAttribute(AUTH_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(AUTH_REGISTER_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (Objects.nonNull(req.getParameter("submitButton"))) {
            RegistrationDto registrationDto = new RegistrationDto();
            registrationDto.setEmail(req.getParameter("email"));
            registrationDto.setName(req.getParameter("name"));
            registrationDto.setSurname(req.getParameter("surname"));
            registrationDto.setFatherhood(req.getParameter("fatherhood"));
            String password1 = req.getParameter("password");
            String password2 = req.getParameter("password2");
            if (!StringUtils.equals(password1, password2)) {
                req.setAttribute("errorResponse", "Паролі мають буди ідентичними");
                doGet(req, resp);
                return;
            }
            registrationDto.setPassword(password1);
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

            List<String> validationMessages = ValidationUtil.validateRegistration(registrationDto);
            if(!validationMessages.isEmpty()){
                String errorResponse = StringUtils.EMPTY;
                for (String validationMessage : validationMessages) {
                    errorResponse = errorResponse.concat(validationMessage);
                }
                req.setAttribute("errorResponse", errorResponse);
                doGet(req, resp);
                return;
            }

            boolean registerResult = authService.register(registrationDto);
            if (registerResult) {
                resp.sendRedirect(req.getContextPath() + AUTH_LOGIN);
            } else {
                req.setAttribute("error", "Проблеми з реєстрацією");
                doGet(req, resp);
            }
        }
    }

}
