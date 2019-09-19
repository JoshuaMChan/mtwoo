package com.mtwoo.alpha.api.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PostRequest {
    @Size(min = 4, max = 64)
    private String title;
    @NotBlank
    private String context;
    @Email
    private String email;
    @Size(min = 1, message = "Must at least have 1 tag.")
    @Size(max = 5, message = "Can not have more then 5 tags.")
    private List<String> tags;
}
