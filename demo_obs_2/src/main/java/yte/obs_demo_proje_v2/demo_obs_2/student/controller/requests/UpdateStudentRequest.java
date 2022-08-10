package yte.obs_demo_proje_v2.demo_obs_2.student.controller.requests;

import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String email,
        String username,

        String password
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, username, password);
    }

}
