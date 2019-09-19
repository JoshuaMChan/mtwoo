package com.mtwoo.alpha.api.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TagRequest {
    @Size(min = 1, max = 32)
    private String name;
}