package com.weldnor.spms.android.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long userId;
    private String username;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String password;
    private String email;
}
