package com.nure.kravchenko.student.reference.client.filter;

import com.nure.kravchenko.student.reference.client.model.Role;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebFilter(filterName = "indexingFilter", urlPatterns = {"/ ", "/login/*", "/register/*"})
public class IndexFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("IndexFilter init...");
    }

    @Override
    public void destroy() {
        log.info("IndexFilter destroy...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Long id = (Long) req.getSession().getAttribute("userId");
        String role = (String) req.getSession().getAttribute("role");
        if (id != null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            if (role.equalsIgnoreCase(Role.STUDENT.name())) {
                response.sendRedirect(req.getContextPath() + "/student");
                return;
            } else if (role.equalsIgnoreCase(Role.WORKER.name())) {
                response.sendRedirect(req.getContextPath() + "/worker");
                return;
            } else if (role.equalsIgnoreCase(Role.ADMIN.name())) {
                response.sendRedirect(req.getContextPath() + "/admin");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
