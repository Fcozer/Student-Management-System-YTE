package yte.obs_demo_proje_v2.demo_obs_2.academician.controller.responses;

import yte.obs_demo_proje_v2.demo_obs_2.academician.entity.Academician;

public record AcademicianQueryModel(

        Long id,
        String name,
        String surname,
        String username,
        String password


) {

    public AcademicianQueryModel(Academician academician){
        this(
                academician.getId(),
                academician.getName(),
                academician.getSurname(),
                academician.getUsername(),
                academician.getPassword()

        );
    }
}
