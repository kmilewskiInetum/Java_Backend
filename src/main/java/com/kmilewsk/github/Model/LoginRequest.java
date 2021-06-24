package com.kmilewsk.github.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Id
    @GeneratedValue()
    private Long id;
    private String login;
    private int requestCount = 1;
    @Version
    private Long version;

}
