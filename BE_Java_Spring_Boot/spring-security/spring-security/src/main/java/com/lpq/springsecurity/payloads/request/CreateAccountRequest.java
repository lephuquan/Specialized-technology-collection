package com.lpq.springsecurity.payloads.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateAccountRequest {


    @NotNull
    private String username;

    @NotNull
    @Email(regexp = AuthRequest.VALID_EMAIL_ADDRESS_REGEX, message = "{api.account.email.wrong.format}")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String fullName;

    @Pattern(regexp = "^\\d+$", message = "{api.contact.phone-only-number}")
    private String phoneNumber;

}
