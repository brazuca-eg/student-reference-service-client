package com.nure.kravchenko.student.reference.client.filter;

import com.nure.kravchenko.student.reference.client.model.Role;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Log4j
@WebFilter(filterName = "security", urlPatterns = {"/student/*", "/admin/*", "/worker/*", "/login/*", "/register/*"})
public class SecurityFilter implements Filter {

    public static final String ERROR_403 = "error_403.jsp";

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("SecurityFilter init...");
    }

    @Override
    public void destroy() {
        log.info("SecurityFilter destroy...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Long id = (Long) req.getSession().getAttribute("userId");
        String role = (String) req.getSession().getAttribute("role");
        String currentUrl = ((HttpServletRequest) servletRequest).getRequestURI();
        if (id == null) {
            if (!currentUrl.contains("login") && !currentUrl.contains("register")) {
                servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
            }
        } else if (Objects.isNull(role) && currentUrl.contains("logout")) {
            servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
        } else if (role.equalsIgnoreCase(Role.STUDENT.name())) {
            if (StringUtils.containsIgnoreCase(currentUrl, Role.WORKER.name()) ||
                    StringUtils.containsIgnoreCase(currentUrl, Role.ADMIN.name())) {
                servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
        } else if (role.equalsIgnoreCase(Role.WORKER.name())) {
            if (!StringUtils.containsIgnoreCase(currentUrl, Role.WORKER.name())) {
                servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
        } else if (role.equalsIgnoreCase(Role.ADMIN.name())) {
            if (!StringUtils.containsIgnoreCase(currentUrl, Role.ADMIN.name())) {
                servletRequest.getRequestDispatcher(ERROR_403).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
