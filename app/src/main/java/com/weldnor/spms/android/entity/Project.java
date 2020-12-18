package com.weldnor.spms.android.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private long projectId;
    private String name;
    private String description;
    private long ownerId;
}
