package com.bromel.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String name;

    private Integer age;

    private String address;

    private String phoneNumber;

    private String school;
}
