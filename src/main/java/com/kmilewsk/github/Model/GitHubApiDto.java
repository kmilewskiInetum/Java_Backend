package com.kmilewsk.github.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubApiDto {
    private String login;
    private int id;
    private String avatar_url;
    private String type;
    private String name;
    private int public_repos;
    private int followers;
    private OffsetDateTime created_at;
}
