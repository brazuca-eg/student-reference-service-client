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
            LocalDate reportEndDate = filter.getReportDate();
            String reasonName = filter.getReasonName();

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
            if (Objects.nonNull(reportEndDate)) {
                Predicate<WorkerRequestDto> reportDateYearPredicate =
                        (a) -> a.getEndDate().getYear() == reportEndDate.getYear();
                predicates.add(reportDateYearPredicate);

                Predicate<WorkerRequestDto> reportDateMonthPredicate =
                        (a) -> a.getEndDate().getMonth() == reportEndDate.getMonth();
                predicates.add(reportDateMonthPredicate);

                Predicate<WorkerRequestDto> reportDateDayPredicate =
                        (a) -> a.getEndDate().getDayOfMonth() == reportEndDate.getDayOfMonth();
                predicates.add(reportDateDayPredicate);
            }
            if (StringUtils.isNoneBlank(reasonName)) {
                Predicate<WorkerRequestDto> reasonNamePredicate =
                        (a) -> a.getReasonName().contains(reasonName);
                predicates.add(reasonNamePredicate);
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
