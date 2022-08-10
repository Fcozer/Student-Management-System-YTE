package yte.obs_demo_proje_v2.demo_obs_2.academician.controller.request;

import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;

public record UpdateAcademicianRequest(

        String name,

        String surname,

        String username,

        String password
) {


    public Academician toDomainEntity() {
        return new Academician(name, surname, username, password);
    }


}
