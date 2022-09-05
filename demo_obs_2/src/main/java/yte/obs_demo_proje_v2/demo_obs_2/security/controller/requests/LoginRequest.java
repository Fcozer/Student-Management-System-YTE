package yte.obs_demo_proje_v2.demo_obs_2.security.controller.requests;

import javax.validation.constraints.NotBlank;
public record LoginRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}


