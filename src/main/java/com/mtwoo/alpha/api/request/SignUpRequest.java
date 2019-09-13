package com.mtwoo.alpha.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 64)
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    private String password;
}
