package com.mtwoo.alpha.api.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @Email
    private String email;
    @Size(min = 8, max = 32)
    private String password;
}
