package com.kmilewsk.github.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class UserOutputDto {
    private int id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private OffsetDateTime createdAt;
    private Double calculations;
}
