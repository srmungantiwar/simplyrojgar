package com.simplyrojgar.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String role;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
