package com.weldnor.spms.android.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicAuthRequest {
    private String username;
    private String password;
}
