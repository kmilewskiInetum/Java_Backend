package com.kmilewsk.github.Web.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.kmilewsk.github.Model.GitHubApiDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.OffsetDateTime;

public class GitHubMocks {

    public static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        var objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/users/exist"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString( new GitHubApiDto("exist",1,"","","exist",1,1, OffsetDateTime.parse("2021-06-23T17:01:03.24Z"))))));

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/users/not-exist"))
                .willReturn(WireMock.notFound()));

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/users/server-not-working"))
                .willReturn(WireMock.serverError()));
    }

}