package yte.obs_demo_proje_v2.demo_obs_2.common.response;

import org.aspectj.bridge.Message;

public record MessageResponse(
        ResponseType responseType,
        String message

) {
}
