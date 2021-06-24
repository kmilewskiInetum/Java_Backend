package com.kmilewsk.github.Web.Client;

import com.kmilewsk.github.Configuration.Web.Client.GitHubClientConfiguration;
import com.kmilewsk.github.Model.GitHubApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "GitHubApiClient", url = "${github.api.url}", configuration = GitHubClientConfiguration.class)
public interface GitHubApiClient {

    @GetMapping("/users/{login}")
    GitHubApiDto getUserInfo(@PathVariable String login);
}
