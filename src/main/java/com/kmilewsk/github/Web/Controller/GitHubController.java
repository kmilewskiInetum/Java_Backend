package com.kmilewsk.github.Web.Controller;

import com.kmilewsk.github.Model.UserOutputDto;
import com.kmilewsk.github.Service.GitHubService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping(value = "/users/{login}")
    @ResponseStatus(HttpStatus.OK)
    public UserOutputDto getUserInfo(@PathVariable String login){
         return gitHubService.getInfoForUser(login); }

}
