package com.kmilewsk.github.Service;

import com.kmilewsk.github.Model.LoginRequest;
import com.kmilewsk.github.Model.UserOutputDto;
import com.kmilewsk.github.Repository.LoginRequestRepository;
import com.kmilewsk.github.Web.Client.GitHubApiClient;
import org.springframework.stereotype.Service;

@Service
public class GitHubService {

    private final LoginRequestRepository loginRequestRepository;
    private final GitHubApiClient gitHubApiClient;

    public GitHubService(LoginRequestRepository loginRequestRepository, GitHubApiClient gitHubApiClient) {
        this.loginRequestRepository = loginRequestRepository;
        this.gitHubApiClient = gitHubApiClient;
    }

    public UserOutputDto getInfoForUser(String login){
        increaseLoginRequest(login);
        var github = gitHubApiClient.getUserInfo(login);
        return UserOutputDto.builder()
                .id(github.getId())
                .login(github.getLogin())
                .name(github.getName())
                .type(github.getType())
                .createdAt(github.getCreated_at())
                .avatarUrl(github.getAvatar_url())
                .calculations(getCalculation(github.getFollowers(), github.getPublic_repos()))
                .build();
    }

    private Double getCalculation(double followersUrl, double publicRepos){
        return  6 / followersUrl * (2 + publicRepos);
    }

    private void increaseLoginRequest(String login) {
        loginRequestRepository.findByLogin(login)
                .ifPresentOrElse(
                        loginRequest -> {
                            loginRequest.setRequestCount(loginRequest.getRequestCount() + 1);
                            loginRequestRepository.save(loginRequest);
                        },
                        () -> loginRequestRepository.save(LoginRequest.builder()
                                .login(login)
                                .build()));
    }
}
