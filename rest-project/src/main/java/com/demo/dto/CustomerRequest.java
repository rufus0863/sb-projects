package com.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerRequest {
    @NotBlank(message = "{customer.name.notBlank}")
    @Size(min = 2, max = 60, message = "{customer.name.size}")
    private String name;

    @NotBlank(message = "{customer.email.notBlank}")
    @Email(message = "{customer.email.valid}")
    @Size(max = 120, message = "{customer.email.size}")
    private String email;

    @Past(message = "{customer.birthDate.past}")
    private LocalDate birthDate;

    public CustomerRequest() { }
    public CustomerRequest(String name, String email, LocalDate birthDate) {
        this.name = name; this.email = email; this.birthDate = birthDate;
    }

}
