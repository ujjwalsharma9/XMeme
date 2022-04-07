package com.crio.xmeme.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;


@Data
public class MemeRequestDto {
    
    private String id;
    

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "url is mandatory")
    private String url;

    @NotBlank(message = "caption is mandatory")
    private String caption;
}