package com.nure.kravchenko.student.reference.client.servlet.worker;

import com.nure.kravchenko.student.reference.client.filter.ReportFilter;
import com.nure.kravchenko.student.reference.client.server.ReasonDto;
import com.nure.kravchenko.student.reference.client.server.SpecialityDto;
import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import com.nure.kravchenko.student.reference.client.service.WorkerService;
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
import java.util.List;
import java.util.Objects;

import static com.nure.kravchenko.student.reference.client.service.filter.WorkerSearchReportFilter.filterReports;
import static com.nure.kravchenko.student.reference.client.service.utils.PageConstants.WORKER_SEARCH_REQUEST_PAGE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServiceConstants.WORKER_SERVICE;
import static com.nure.kravchenko.student.reference.client.service.utils.ServletPathConstants.WORKER_SEARCH_REQUESTS;

@WebServlet(WORKER_SEARCH_REQUESTS)
public class FindRequestServlet extends HttpServlet {

    private static final long serialVersionUID = 660788011919846701L;

    private WorkerService workerService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        this.workerService = (WorkerService) ctx.getAttribute(WORKER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        Long id = (Long) session.getAttribute("userId");
        req.setCharacterEncoding("UTF-8");

        List<ReasonDto> reasons = workerService.getAllRequestReasons(token);
        req.setAttribute("reasons", reasons);

        List<SpecialityDto> specialities = workerService.getAllSpecialities(token);
        req.setAttribute("specialities", specialities);

        if (Objects.nonNull(req.getParameter("searchButton"))) {
            List<WorkerRequestDto> assignedReports = workerService
                    .getAssignedWorkerRequests(id, true, null, token);

            String errorResponse = StringUtils.EMPTY;
            ReportFilter filter = new ReportFilter();
            if (req.getParameter("fullNameParam") != null) {
                String fullName = req.getParameter("fullNameToSearch");
                if (StringUtils.isBlank(fullName)) {
                    errorResponse = errorResponse.concat("Ви не ввели ПІБ студента;\n");
                }
                filter.setStudentFullName(fullName);
            }
            if (req.getParameter("groupParam") != null) {
                String groupToSearch = req.getParameter("groupToSearch");
                if (StringUtils.isBlank(groupToSearch)) {
                    errorResponse = errorResponse.concat("Ви не ввели групу;\n");
                }
                filter.setGroupName(groupToSearch);
            }
            if (req.getParameter("dateParam") != null) {
                LocalDate startDate = LocalDate.parse(req.getParameter("startDateToSearch"));
                LocalDate endDate = LocalDate.parse(req.getParameter("endDateToSearch"));

                if (Objects.isNull(startDate)) {
                    errorResponse = errorResponse.concat("Ви не обрали початкову дату;\n");
                }
                if (Objects.isNull(endDate)) {
                    errorResponse = errorResponse.concat("Ви не обрали кінцеву дату;\n");
                }
                if (startDate.isAfter(endDate)) {
                    errorResponse = errorResponse.concat("Початкова дата повинна бути до кінцевої;\n");
                }
                filter.setReportStartDate(startDate);
                filter.setReportEndDate(endDate);
            }
            if (req.getParameter("reasonParam") != null) {
                String reasonName = req.getParameter("reasonToSearch");
                if (Objects.isNull(reasonName)) {
                    errorResponse = errorResponse.concat("Ви не обрали місце подання;\n");
                }
                filter.setReasonName(reasonName);
            }
            if (req.getParameter("specialityParam") != null) {
                Long specialityId = Long.valueOf(req.getParameter("specialityToSearch"));
                if (specialityId == null) {
                    errorResponse = errorResponse.concat("Ви не обрали спеціальність та освітню програму;\n");
                }
                SpecialityDto specialityDto = specialities.stream()
                        .filter(a -> Objects.equals(a.getId(), specialityId))
                        .findFirst().get();
                filter.setSpecialityName(specialityDto.getName());
                filter.setEducationalProgram(specialityDto.getEducationalProgram());
            }

            if (StringUtils.isNoneBlank(errorResponse)) {
                req.setAttribute("errorResponse", errorResponse);
                redirect(req, resp);
            } else {
                req.setAttribute("assignedReports", filterReports(assignedReports, filter));
                redirect(req, resp);
            }
        } else if (Objects.nonNull(req.getParameter("clearButton"))) {
            req.setAttribute("assignedReports", null);
            redirect(req, resp);
        } else {
            redirect(req, resp);
        }
    }

    private void redirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(WORKER_SEARCH_REQUEST_PAGE);
        requestDispatcher.forward(req, resp);
    }

}
