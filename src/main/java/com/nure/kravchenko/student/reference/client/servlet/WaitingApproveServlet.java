package com.nure.kravchenko.student.reference.client.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.WAITING_PATH;

@WebServlet(WAITING_PATH)
public class WaitingApproveServlet extends HttpServlet {
    
    private static final long serialVersionUID = 5083116333228963939L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/waiting_approve.jsp");
        requestDispatcher.forward(req, resp);
    }

}
