package com.mtwoo.alpha.api.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequest {
    private String title;
    private String context;
    private String email;
}
