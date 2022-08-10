package yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests;

import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddStudentRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        @Email
        String email,


        String username,


        String password
) {
        public Student toDomainEntity() {
                return new Student(name, surname, email, username, password);
        }

}
