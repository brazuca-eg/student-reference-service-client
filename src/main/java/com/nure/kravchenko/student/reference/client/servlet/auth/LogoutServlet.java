package com.nure.kravchenko.student.reference.client.servlet.auth;

import com.nure.kravchenko.student.reference.client.service.AuthService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.AUTH_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.AUTH_LOGIN;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.AUTH_LOGOUT;

@WebServlet(AUTH_LOGOUT)
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = -3149824078466009445L;

    private AuthService authService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.authService = (AuthService) ctx.getAttribute(AUTH_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        authService.logout();

        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.removeAttribute("userId");
        session.removeAttribute("token");

        response.sendRedirect(request.getContextPath() + AUTH_LOGIN);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
