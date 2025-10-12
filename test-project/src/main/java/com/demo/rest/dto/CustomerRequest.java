package com.demo.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
