package yte.obs_demo_proje_v2.demo_obs_2.assistant.controller.response;

import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;

public record AssistantQueryModel(

        Long id,
        String name,
        String surname,
        String username,
        String password


) {

    public AssistantQueryModel(Assistant assistant) {
        this(
                assistant.getId(),
                assistant.getName(),
                assistant.getSurname(),
                assistant.getUsername(),
                assistant.getPassword()


        );
    }
}
