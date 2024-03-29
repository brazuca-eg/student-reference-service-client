package com.nure.kravchenko.student.reference.client.service.filter;

import com.nure.kravchenko.student.reference.client.filter.ReportFilter;
import com.nure.kravchenko.student.reference.client.server.WorkerRequestDto;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerSearchReportFilter {

    private WorkerSearchReportFilter() {
        //private constructor for WorkerSearchReportFilter class.
    }

    public static List<WorkerRequestDto> filterReports(List<WorkerRequestDto> reports, ReportFilter filter){
        if(!reports.isEmpty()){
            String studentFullName = filter.getStudentFullName();
            String groupName = filter.getGroupName();
            LocalDate reportStartDate = filter.getReportStartDate();
            LocalDate reportEndDate = filter.getReportEndDate();
            String reasonName = filter.getReasonName();
            String specialityName = filter.getSpecialityName();
            String educationalProgram = filter.getEducationalProgram();

            List<Predicate<WorkerRequestDto>> predicates = new ArrayList<>();
            if (StringUtils.isNoneBlank(studentFullName)) {
                Predicate<WorkerRequestDto> fullNamePredicate =
                        (a) -> a.getStudentFullName().contains(studentFullName);
                predicates.add(fullNamePredicate);
            }
            if (StringUtils.isNoneBlank(groupName)) {
                Predicate<WorkerRequestDto> groupNamePredicate =
                        (a) -> a.getGroupName().contains(groupName);
                predicates.add(groupNamePredicate);
            }
            if (Objects.nonNull(reportStartDate) && Objects.nonNull(reportEndDate)) {
                Predicate<WorkerRequestDto> reportDatePredicate =
                        (a) ->  a.getEndDate().toLocalDate().plusDays(1).isAfter(reportStartDate)
                                && a.getEndDate().toLocalDate().minusDays(1).isBefore(reportEndDate);

                predicates.add(reportDatePredicate);
            }
            if (StringUtils.isNoneBlank(reasonName)) {
                Predicate<WorkerRequestDto> reasonNamePredicate =
                        (a) -> a.getReasonName().contains(reasonName);
                predicates.add(reasonNamePredicate);
            }
            if (StringUtils.isNoneBlank(specialityName) && StringUtils.isNoneBlank(educationalProgram)) {
                Predicate<WorkerRequestDto> specialityNamePredicate =
                        (a) -> a.getSpecialityName().equalsIgnoreCase(specialityName);
                predicates.add(specialityNamePredicate);
                Predicate<WorkerRequestDto> educationalProgramPredicate =
                        (a) -> a.getEducationalProgram().equalsIgnoreCase(educationalProgram);
                predicates.add(educationalProgramPredicate);
            }
            for (Predicate<WorkerRequestDto> predicate : predicates) {
                reports = reports
                        .stream()
                        .filter(predicate)
                        .collect(Collectors.toList());
            }
        }
        return reports;
    }
}
