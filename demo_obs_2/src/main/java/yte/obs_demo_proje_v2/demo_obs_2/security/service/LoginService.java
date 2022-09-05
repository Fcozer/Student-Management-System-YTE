package yte.obs_demo_proje_v2.demo_obs_2.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.ResponseType;
import yte.obs_demo_proje_v2.demo_obs_2.common.response.SecurityRecord;
import yte.obs_demo_proje_v2.demo_obs_2.security.controller.requests.LoginRequest;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    public SecurityRecord login(LoginRequest loginRequest) {
        var preAuthentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authority = "";
        try {
            Authentication postAuthentication = authenticationManager.authenticate(preAuthentication);
            SecurityContext newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(postAuthentication);
            SecurityContextHolder.setContext(newContext);

            authority = newContext.getAuthentication().getAuthorities().stream().toList().get(0).getAuthority();

            return new SecurityRecord(ResponseType.SUCCESS, "Login is successful",authority);
        } catch (AuthenticationException e) {
            return new SecurityRecord(ResponseType.ERROR, "Authentication exception: %s".formatted(e.getMessage()), authority);
        }
    }
}
