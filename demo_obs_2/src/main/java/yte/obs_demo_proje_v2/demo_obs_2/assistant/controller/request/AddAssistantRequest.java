package yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request;

import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAssistantRequest(

        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,

        String username,


        String password
) {

    public Assistant toDomainEntity() {
        return new Assistant(name, surname, username, password);
    }
}
