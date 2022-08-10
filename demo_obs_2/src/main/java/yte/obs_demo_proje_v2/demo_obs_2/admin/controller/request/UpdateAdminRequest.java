package yte.obs_demo_proje_v2.demo_obs_2.admin.controller.request;

import yte.obs_demo_proje_v2.demo_obs_2.admin.entity.Admin;

public record UpdateAdminRequest(
        String username,
        String password
) {
    public Admin toDomainEntity() {
        return new Admin(username, password);

    }

}