package com.kmilewsk.github.Web.Controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kmilewsk.github.Repository.LoginRequestRepository;
import com.kmilewsk.github.Service.GitHubService;
import com.kmilewsk.github.Web.Client.GitHubApiClient;
import com.kmilewsk.github.Web.Client.GitHubApiTestConfig;
import com.kmilewsk.github.Web.Client.GitHubMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { GitHubApiTestConfig.class })
@SpringBootTest
class GitHubControllerTest {

    @Autowired
    GitHubService gitHubService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    GitHubApiClient gitHubApiClient;

    @Mock
    LoginRequestRepository loginRequestRepository;

    @BeforeEach
    void setUp() throws IOException {
        GitHubMocks.setupMockBooksResponse(wireMockServer);
    }

    @Test
    void controllerReturnCorrectDtoForExistingUser() throws Exception {
        mockMvc.perform(get("/users/exist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("login").value("exist"))
                .andExpect(jsonPath("name").value("exist"))
                .andExpect(jsonPath("calculations").value(18.0))
                .andExpect(jsonPath("createdAt").value("2021-06-23T17:01:03.24Z"))
        ;
    }

    @Test
    void controllerReturnErrorDtoForNotExistingUser() throws Exception {
        mockMvc.perform(get("/users/not-exist"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void controllerReturnErrorForServerError() throws Exception {
        mockMvc.perform(get("/users/server-not-working"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("status").value(204))
                .andExpect(jsonPath("message").value("GitHub service is currently not working"))
        ;
    }






}