package yte.obs_demo_proje_v2.demo_obs_2.common.response;

public record SecurityRecord(
        ResponseType responseType,
        String message,
        String isAuthority
) {
}
