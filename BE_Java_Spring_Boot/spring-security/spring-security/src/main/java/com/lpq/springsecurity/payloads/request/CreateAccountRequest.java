package com.lpq.springsecurity.payloads.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

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
