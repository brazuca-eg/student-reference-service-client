package com.nure.kravchenko.student.reference.client.servlet.admin;

import com.nure.kravchenko.student.reference.client.config.AppConfig;
import com.nure.kravchenko.student.reference.client.payload.CreateFacultyDto;
import com.nure.kravchenko.student.reference.client.payload.CreateGroupDto;
import com.nure.kravchenko.student.reference.client.payload.CreateSpecialityDto;
import com.nure.kravchenko.student.reference.client.server.FacultyDto;
import com.nure.kravchenko.student.reference.client.server.SpecialityDto;
import com.nure.kravchenko.student.reference.client.service.AdminService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/admin/creation")
public class CreationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");


        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean("adminService", AdminService.class);

        List<FacultyDto> faculties = adminService.getAllFaculties(token);
        req.setAttribute("faculties", faculties);

        List<SpecialityDto> specialities = adminService.getAllSpecialities(token);
        req.setAttribute("specialities", specialities);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin_create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("userId");
        String token = (String) session.getAttribute("token");

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        AdminService adminService = annotationConfigApplicationContext
                .getBean("adminService", AdminService.class);

        if (Objects.nonNull(req.getParameter("createFacultyButton"))) {
            CreateFacultyDto createFacultyDto = CreateFacultyDto.builder()
                    .shortName(req.getParameter("shortName"))
                    .name(req.getParameter("name"))
                    .build();

            adminService.createFaculty(createFacultyDto, token);

            doGet(req, resp);
        }

        if (Objects.nonNull(req.getParameter("createSpecialityButton"))) {
            CreateSpecialityDto createSpecialityDto = CreateSpecialityDto.builder()
                    .number(Integer.valueOf(req.getParameter("specialityNumber")))
                    .name(req.getParameter("specialityName"))
                    .shortName(req.getParameter("specialityShortName"))
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
                    .startYear(LocalDate.parse(req.getParameter("groupEndYear")))
                    .endYear(LocalDate.parse(req.getParameter("groupEndYear")))
                    .specialityId(Long.valueOf(req.getParameter("speciality")))
                    .build();


            adminService.createGroup(createGroupDto, token);

            doGet(req, resp);
        }

    }
}
