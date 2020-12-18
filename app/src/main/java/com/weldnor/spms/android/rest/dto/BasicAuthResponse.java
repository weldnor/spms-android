package com.weldnor.spms.android.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BasicAuthResponse {
    private String token;
    @JsonProperty("user_id")
    private long userId;
}
