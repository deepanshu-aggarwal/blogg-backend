package com.blogg.app.dto.auth.response;

import lombok.Setter;

@Setter
public class LoginResponse {
    private String username;
    private String accessToken;
    private String refreshToken;
}
