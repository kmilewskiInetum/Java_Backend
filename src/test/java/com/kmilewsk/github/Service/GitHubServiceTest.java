package com.kmilewsk.github.Service;

import com.kmilewsk.github.Model.GitHubApiDto;
import com.kmilewsk.github.Model.UserOutputDto;
import com.kmilewsk.github.Repository.LoginRequestRepository;
import com.kmilewsk.github.Web.Client.GitHubApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class GitHubServiceTest {

    @MockBean
    GitHubApiClient gitHubApiClient;

    @MockBean
    LoginRequestRepository loginRequestRepository;

    @Autowired
    GitHubService gitHubService;


    @Test
    void getInfoForUserNotNull() {
        Mockito.when(gitHubApiClient.getUserInfo("test")).thenReturn(new GitHubApiDto("test",1,"","","test",1,1, OffsetDateTime.parse("2021-06-23T17:01:03.24Z")));

        var result = gitHubService.getInfoForUser("test");

        assertNotNull(result);

    }

    @Test
    void getInfoForUserReturnCorectDto() {
        Mockito.when(gitHubApiClient.getUserInfo("test")).thenReturn(new GitHubApiDto("test",1,"","","test",1,1, OffsetDateTime.parse("2021-06-23T17:01:03.24Z")));

        var result = gitHubService.getInfoForUser("test");

        var expect = new UserOutputDto(1,"test","test","","",OffsetDateTime.parse("2021-06-23T17:01:03.24Z"),18.0);
        assertEquals(result, expect);

    }
}