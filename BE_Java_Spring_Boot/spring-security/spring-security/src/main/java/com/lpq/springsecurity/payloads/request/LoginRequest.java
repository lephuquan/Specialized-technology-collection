package com.lpq.springsecurity.payloads.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";

    @NotBlank(message = "{api.account.email.blank}")
    @Email(regexp = VALID_EMAIL_ADDRESS_REGEX, message = "{api.account.email.wrong.format}")
    private String email;

    @NotBlank(message = "{api.account.password.blank} " )
    private String password;
}
