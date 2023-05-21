package com.nure.kravchenko.student.reference.client.service.utils;

import com.nure.kravchenko.student.reference.client.payload.RegistrationDto;
import com.nure.kravchenko.student.reference.client.payload.UpdateStudentTicketDto;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationUtil {

    private static final int TWO_CHARS_NUM = 2;

    private ValidationUtil() {
        //private constructor for ValidationUtil class.
    }

    public static List<String> validateRegistration(RegistrationDto registrationDto) {
        List<String> validateMessages = new ArrayList<>();

        String name = registrationDto.getFatherhood();
        if (StringUtils.isBlank(name)) {
            validateMessages.add("Поле з ім'ям повинно бути заповненим. ");
        }
        if (name.length() < TWO_CHARS_NUM) {
            validateMessages.add("Ім'я повинно мати щонайменш 2 символи. ");
        }

        String surname = registrationDto.getFatherhood();
        if (StringUtils.isBlank(surname)) {
            validateMessages.add("Поле з прізвищем повинно бути заповненим. ");
        }
        if (surname.length() < TWO_CHARS_NUM) {
            validateMessages.add("Прізвище повинно мати щонайменш 2 символи. ");
        }

        String fatherhood = registrationDto.getFatherhood();
        if (StringUtils.isBlank(fatherhood)) {
            validateMessages.add("Поле з ім'ям по батьковим повинно бути заповненим. ");
        }
        if (fatherhood.length() < TWO_CHARS_NUM) {
            validateMessages.add("Ім'я по батькове повинно мати щонайменш 2 символи. ");
        }

        if (StringUtils.isBlank(registrationDto.getEmail())) {
            validateMessages.add("Поле з Email повинно бути заповненим. ");
        }

        if (Objects.isNull(registrationDto.getGender())) {
            validateMessages.add("Ви повинні обрати стать. ");
        }

        String password = registrationDto.getPassword();
        if (StringUtils.isBlank(password)) {
            validateMessages.add("Поле з паролем повинно бути заповненим. ");
        }
        if (password.length() < 6) {
            validateMessages.add("Пароль повинен мати щонайменш 6 символів. ");
        }

        if (Objects.isNull(registrationDto.getRole())) {
            validateMessages.add("Ви повинні обрати вашу роль у системі. ");
        }

        return validateMessages;
    }

    public static String validateStudentTicket(UpdateStudentTicketDto ticketDto) {
        List<String> validateMessages = new ArrayList<>();
        String changedSerialNumber = ticketDto.getSerialNumber();
        String changedNumber = ticketDto.getNumber();
        LocalDate changedStartDate = ticketDto.getStartDate();
        LocalDate changedEndDate = ticketDto.getEndDate();

        if(changedNumber.length() < 8 || changedNumber.length() > 12){
            validateMessages.add("Номер квитка має невідповідну кількість символів. ");
        }
        if(changedSerialNumber.length() != TWO_CHARS_NUM){
            validateMessages.add("Серійний номер квитка має складатися з 2 символів. ");
        }

        if(!validateMessages.isEmpty()) {
            String errorResponse = StringUtils.EMPTY;
            for (String validationMessage : validateMessages) {
                errorResponse = errorResponse.concat(validationMessage);
            }
            return errorResponse;
        }
        return StringUtils.EMPTY;
    }

}
