package com.lpq.springsecurity.payloads.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";

    @NotBlank(message = "{api.account.email.blank}")
    @Email(regexp = VALID_EMAIL_ADDRESS_REGEX, message = "{api.account.email.wrong.format}")
    private String email;

    @NotBlank(message = "{api.account.password.blank}")
    private String password;
}
