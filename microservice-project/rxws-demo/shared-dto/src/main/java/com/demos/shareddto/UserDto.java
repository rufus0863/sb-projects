package com.demos.shareddto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String nicName;
    private String email;
    private String phoneNumber;
}