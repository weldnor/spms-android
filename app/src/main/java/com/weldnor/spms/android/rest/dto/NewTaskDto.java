package com.weldnor.spms.android.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskDto {
    private Long projectId;
    private Long creatorId;
    private String name;
    private String description;
    private Long statusId;
}
