package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.payload.CreateFacultyDto;
import com.nure.kravchenko.student.reference.client.payload.CreateGroupDto;
import com.nure.kravchenko.student.reference.client.payload.CreateSpecialityDto;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.SpecialityDto;
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
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.ADMIN_CREATE_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.ADMIN_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.ADMIN_CREATION;

@WebServlet(ADMIN_CREATION)
public class CreationServlet extends HttpServlet {

    private static final long serialVersionUID = 6436614274872400258L;

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

        List<FacultyDto> faculties = adminService.getAllFaculties(token);
        req.setAttribute("faculties", faculties);

        List<SpecialityDto> specialities = adminService.getAllSpecialities(token);
        req.setAttribute("specialities", specialities);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(ADMIN_CREATE_PAGE);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");

        if (Objects.nonNull(req.getParameter("createFacultyButton"))) {
            CreateFacultyDto createFacultyDto = CreateFacultyDto.builder()
                    .shortName(req.getParameter("facultyShortName"))
                    .name(req.getParameter("facultyName"))
                    .build();

            adminService.createFaculty(createFacultyDto, token);

            doGet(req, resp);
        }

        if (Objects.nonNull(req.getParameter("createSpecialityButton"))) {
            CreateSpecialityDto createSpecialityDto = CreateSpecialityDto.builder()
                    .number(Integer.valueOf(req.getParameter("specialityNumber")))
                    .name(req.getParameter("specialityName"))
                    .shortName(req.getParameter("specialityShortName"))
                    .educationalProgram(req.getParameter("educationalProgram"))
                    .facultyId(Long.valueOf(req.getParameter("faculty")))
                    .build();

            adminService.createSpeciality(createSpecialityDto, token);

            doGet(req, resp);
        }

        if (Objects.nonNull(req.getParameter("createGroupButton"))) {
            CreateGroupDto createGroupDto = CreateGroupDto.builder()
                    .name(req.getParameter("groupName"))
                    .learnForm(req.getParameter("groupLearnForm"))
                    .degreeForm(req.getParameter("groupDegreeForm"))
                    .startYear(LocalDate.parse(req.getParameter("groupStartYear")))
                    .endYear(LocalDate.parse(req.getParameter("groupEndYear")))
                    .specialityId(Long.valueOf(req.getParameter("specialityForGroup")))
                    .build();


            adminService.createGroup(createGroupDto, token);

            doGet(req, resp);
        }
    }

}
