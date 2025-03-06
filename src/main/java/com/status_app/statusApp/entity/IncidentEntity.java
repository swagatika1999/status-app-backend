package com.status_app.statusApp.entity;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("incidents")
@Data
public class IncidentEntity {

    @Id
    private String id;

    private String serviceId;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    private LocalDateTime lastUpdatedAt;
}