package com.kmilewsk.github.Web.Controller.Advice;

import com.kmilewsk.github.Exception.GitHubClientApiError;
import com.kmilewsk.github.Exception.GitHubClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GitHubControllerAdvice {

    @ExceptionHandler(GitHubClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map gitHubClientNotFoundExceptionHandler(Exception exception){
        return Map.of(
                "status", HttpStatus.NOT_FOUND.value() ,
                "message", exception.getCause().getMessage()
        );
    }

    @ExceptionHandler(GitHubClientApiError.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map gitHubClientNotWorking(){
        return Map.of(
                "status", HttpStatus.NO_CONTENT.value() ,
                "message", "GitHub service is currently not working"
        );
    }
}
