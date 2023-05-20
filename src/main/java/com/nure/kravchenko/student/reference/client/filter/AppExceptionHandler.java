package com.nure.kravchenko.student.reference.client.filter;

import com.google.gson.Gson;
import com.nure.kravchenko.student.reference.client.server.ErrorResponse;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AppExceptionHandler")
public class AppExceptionHandler extends HttpServlet {

    private static final long serialVersionUID = -646085210127247463L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, ServletException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String previousPath = (String) request.getAttribute("javax.servlet.forward.request_uri");
        int slashIndex = previousPath.lastIndexOf("/");
        int statusCode = 500;
        String userMsg = "Server Error";
        if (throwable instanceof HttpClientErrorException) {
            HttpClientErrorException exception = (HttpClientErrorException) throwable;
            statusCode = exception.getRawStatusCode();
            String serverFullMsg = exception.getMessage();
            int start = serverFullMsg.indexOf('{');
            int end = serverFullMsg.lastIndexOf('}') + 1;
            Gson gson = new Gson();
            ErrorResponse errorResponse = gson.fromJson(serverFullMsg.substring(start, end), ErrorResponse.class);
            userMsg = errorResponse.getErrorDescription();
        }

        if (statusCode == 400) {
            request.setAttribute("errorResponse", userMsg);

            if (previousPath.substring(slashIndex).equals("/login")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
            if (previousPath.substring(slashIndex).equals("/register")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/register.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        if (statusCode == 403) {
            request.setAttribute("exc", throwable.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
        }

        if (statusCode == 404) {
            request.setAttribute("errorResponse", userMsg);

            if (previousPath.substring(slashIndex).equals("/login")) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        }

        if (statusCode == 405) {
            request.setAttribute("errorResponse", userMsg);

            if (previousPath.substring(slashIndex).equals("/createRequest")) {
                request.getRequestDispatcher("/student_create_request.jsp").forward(request, response);
            }
        }
    }

}
