package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.payload.ApproveStudentRegisterDto;
import com.nure.kravchenko.student.reference.client.payload.UpdateStudentStatusDto;
import com.nure.kravchenko.student.reference.client.payload.UpdateStudentTicketDto;
import com.nure.kravchenko.student.reference.client.server.StudentDto;
import com.nure.kravchenko.student.reference.client.server.StudentGroupDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;
import com.nure.kravchenko.student.reference.client.service.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;

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
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.ADMIN_SHOW_STUDENT_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;

@WebServlet("/adminShowStudent/*")
public class WatchStudentServlet extends HttpServlet {

    private static final long serialVersionUID = -8001213719392216900L;

    private AdminService adminService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.adminService = (AdminService) ctx.getAttribute(ADMIN_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        req.setCharacterEncoding("UTF-8");

        Long studentId = Long.valueOf(req.getPathInfo().substring(1));

        StudentDto studentDto = adminService.getStudentById(studentId, token);
        req.setAttribute("student", studentDto);

        if (studentDto.isApproved()) {
            req.setAttribute("studentGroup", adminService.getGroupByStudentId(studentId, token));
        } else {
            req.setAttribute("studentGroup", "Не додано");
        }

        List<StudentGroupDto> studentGroups = adminService.getAllGroups(token);
        req.setAttribute("studentGroups", studentGroups.stream()
                .sorted(Comparator.comparing(StudentGroupDto::getName))
                .collect(Collectors.toList()));

        List<String> nonActiveReasons = Arrays.asList("Академічна відпустка", "Відраховано", "Зміна освітньої форми");
        req.setAttribute("nonActiveReasons", nonActiveReasons);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(ADMIN_SHOW_STUDENT_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long studentId = Long.valueOf(req.getPathInfo().substring(1));

        if (Objects.nonNull(req.getParameter("approveStudentButton"))) {
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

        if (Objects.nonNull(req.getParameter("changeGroupButton"))) {
            Long groupId = Long.valueOf(req.getParameter("changedGroup"));

            adminService.updateGroupForStudent(studentId, groupId , token);

            doGet(req, resp);
        }

        if (Objects.nonNull(req.getParameter("changeTicketButton"))) {
            String changedSerialNumber = req.getParameter("changedSerialNumber");
            String changedNumber = req.getParameter("changedNumber");
            LocalDate changedStartDate = LocalDate.parse(req.getParameter("changedStartDate"));
            LocalDate changedEndDate = LocalDate.parse(req.getParameter("changedEndDate"));
            UpdateStudentTicketDto updateStudentTicketDto = UpdateStudentTicketDto.builder()
                    .number(changedNumber)
                    .serialNumber(changedSerialNumber)
                    .startDate(changedStartDate)
                    .endDate(changedEndDate)
                    .build();

            String validatedMessage = ValidationUtil.validateStudentTicket(updateStudentTicketDto);
            if(StringUtils.isNotBlank(validatedMessage)){
                req.setAttribute("errorResponse", validatedMessage);
                doGet(req, resp);
                return;
            }
            adminService.updateStudentTicket(studentId, updateStudentTicketDto , token);

            doGet(req, resp);
        }
    }
}
