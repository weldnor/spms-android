package com.weldnor.spms.android.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private Long taskId;
    private Long projectId;
    private Long creatorId;
    private String name;
    private String description;
    private Long statusId;
}
