package yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request;

import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAcademicianRequest(
        @NotBlank
        @Size(max = 25)
        String name,

        @Size(max = 25)
        @NotBlank
        String surname,

        String username,

        String password
) {

    public Academician toDomainEntity() {
        return new Academician(name, surname, username, password);
    }


}
