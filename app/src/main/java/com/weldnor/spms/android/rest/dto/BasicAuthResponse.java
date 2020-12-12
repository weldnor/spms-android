package com.weldnor.spms.android.rest.dto;

import lombok.Data;

@Data
public class BasicAuthResponse {
    private String token;
    private long userId;
}
