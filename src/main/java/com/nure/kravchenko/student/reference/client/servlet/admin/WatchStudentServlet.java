package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.payload.ApproveStudentRegisterDto;
import com.nure.kravchenko.student.reference.client.payload.UpdateStudentStatusDto;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@WebServlet("/adminShowStudent/*")
public class WatchStudentServlet extends HttpServlet {

    private static final long serialVersionUID = -8001213719392216900L;

    private AdminService adminService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.adminService = (AdminService) ctx.getAttribute("adminService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");

        Long studentId = Long.valueOf(req.getPathInfo().substring(1));

        StudentDto studentDto = adminService.getStudentById(studentId, token);
        req.setAttribute("student", studentDto);

        List<StudentGroupDto> studentGroups = adminService.getAllGroups(token);
        req.setAttribute("studentGroups", studentGroups);

        List<String> nonActiveReasons = Arrays.asList("Академічна відпустка", "Відраховано", "Зміна освітньої форми");
        req.setAttribute("nonActiveReasons", nonActiveReasons);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_show_student.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");

        if (Objects.nonNull(req.getParameter("approveStudentButton"))) {
            Long studentId = Long.valueOf(req.getPathInfo().substring(1));
            String groupName = req.getParameter("studentGroup");
            String serialNumber = req.getParameter("serialNumber");
            String number = req.getParameter("number");
            LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(req.getParameter("endDate"));

            ApproveStudentRegisterDto approveStudentRegisterDto = ApproveStudentRegisterDto.builder()
                    .groupName(groupName)
                    .number(number)
                    .serialNumber(serialNumber)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();

            adminService.approveStudentRegistration(studentId, approveStudentRegisterDto, token);

            doGet(req, resp);
        }

        if (Objects.nonNull(req.getParameter("changeStatusButton"))) {
            boolean isActive = Boolean.parseBoolean(req.getParameter("activeFalse"));
            String statusDescription = req.getParameter("nonActiveReason");
            LocalDate endStatusDate = LocalDate.parse(req.getParameter("endStatusDate"));
            UpdateStudentStatusDto updateStudentStatusDto = UpdateStudentStatusDto.builder()
                    .status(isActive)
                    .description(statusDescription)
                    .endDate(endStatusDate)
                    .build();
            adminService.updateStudentStatus(Long.valueOf(req.getPathInfo().substring(1)),
                    updateStudentStatusDto, token);

            doGet(req, resp);
        }
    }
}
