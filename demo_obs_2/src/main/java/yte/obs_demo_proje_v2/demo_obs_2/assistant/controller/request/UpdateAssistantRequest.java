package yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.request;

import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;
import yte.obs_demo_proje_v2.demo_obs_2.student.entity.Student;

public record UpdateAssistantRequest(

        String name,
        String surname,
        String username,

        String password
) {
    public Assistant toDomainEntity() {
        return new Assistant(name, surname, username, password);
    }

}
