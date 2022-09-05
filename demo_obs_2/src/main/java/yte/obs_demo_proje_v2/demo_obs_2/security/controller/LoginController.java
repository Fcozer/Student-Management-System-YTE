package yte.obs_demo_proje_v2.demo_obs_2.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.SecurityRecord;
import yte.obs_demo_proje_v2.demo_obs_2.security.controller.requests.LoginRequest;
import yte.obs_demo_proje_v2.demo_obs_2.security.service.LoginService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public SecurityRecord login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);

    }
}
