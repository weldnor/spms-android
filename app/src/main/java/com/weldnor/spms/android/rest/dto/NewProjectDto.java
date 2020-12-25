package com.weldnor.spms.android.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProjectDto {
    private String name;
    private String description;
    private long ownerId;
}
