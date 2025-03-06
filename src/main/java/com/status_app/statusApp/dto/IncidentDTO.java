package com.status_app.statusApp.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncidentDTO {

    private String id;

    private String serviceId;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    private LocalDateTime lastUpdatedAt;

}