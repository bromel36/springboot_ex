package com.bromel.exercise.validate;

import com.bromel.exercise.dto.request.StudentRequest;
import org.springframework.stereotype.Component;

@Component
public class StudentValidate {
    private void checkAge(Integer age) {
        if (age == null) {
            throw new IllegalArgumentException("Khong duoc de trong tuoi");
        }
        if (age < 7) {
            throw new IllegalArgumentException("Hoc sinh phai tu du 7 tuoi");
        }
    }

    private void checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Khong duoc de trong ten");
        }
        if (!name.matches("^[A-Za-z\\s]+$")) {
            throw new IllegalArgumentException("Sai dinh dang ten");
        }
    }

    private void checkAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Khong duoc de trong dia chi");
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Khong duoc de trong so dien thoai");
        }
        if (!phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})")) {
            throw new IllegalArgumentException("Sai dinh dang so dien thoai");
        }
    }

    private void checkSchool(String school) {
        if (school == null || school.isBlank()) {
            throw new IllegalArgumentException("Khong duoc de trong truong hoc");
        }
    }

    public void checkStudent(StudentRequest request) {
        checkAge(request.getAge());
        checkAddress(request.getAddress());
        checkName(request.getName());
        checkPhoneNumber(request.getPhoneNumber());
        checkSchool(request.getSchool());
    }
}
