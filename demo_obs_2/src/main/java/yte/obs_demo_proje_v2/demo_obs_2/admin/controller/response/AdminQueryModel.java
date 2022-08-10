package yte.obs_demo_proje_v2.demo_obs_2.admin.controller.response;

import yte.obs_demo_proje_v2.demo_obs_2.admin.entity.Admin;
import yte.obs_demo_proje_v2.demo_obs_2.assistant.entity.Assistant;

public record AdminQueryModel(
        Long id,
        String username,
        String password
) {
    public AdminQueryModel(Admin admin) {
        this(
                admin.getId(),
                admin.getUsername(),
                admin.getPassword()

        );
    }
}