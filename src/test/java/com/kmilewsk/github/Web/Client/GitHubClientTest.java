package com.kmilewsk.github.Web.Client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kmilewsk.github.Exception.GitHubClientNotFoundException;
import com.kmilewsk.github.Model.GitHubApiDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.io.IOException;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { GitHubApiTestConfig.class })
class GitHubClientTest {

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    GitHubApiClient gitHubApiClient;

    @BeforeEach
    void setUp() throws IOException {
        GitHubMocks.setupMockBooksResponse(wireMockServer);
    }

    @Test
    void getUserReturnInfo() {
        var result = gitHubApiClient.getUserInfo("exist");
        var excepted = new GitHubApiDto("exist",1,"","","exist",1,1, OffsetDateTime.parse("2021-06-23T17:01:03.24Z"));
        assertEquals(result,excepted);
    }

//    @Test
//    void getUserNotReturnInfo() {
//        assertThrows(GitHubClientNotFoundException.class , () -> gitHubApiClient.getUserInfo("not-exist"));
//    }

}