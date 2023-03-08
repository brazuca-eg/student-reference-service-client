package com.nure.kravchenko.student.reference.client.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequestDto {

    private String serialNumber;

    private String number;

    private String reasonName;

}
