package com.weldnor.spms.android.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private long userId;
    private String username;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String password;
    private String email;
}
