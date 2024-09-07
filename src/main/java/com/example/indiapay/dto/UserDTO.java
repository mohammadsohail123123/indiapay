package com.example.indiapay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude
public class UserDTO {

    @NotNull
    @NotEmpty
    private String firstname;
    private String lastname;
    @Email
    private String email;

}
