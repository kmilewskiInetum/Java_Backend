package com.kmilewsk.github.Exception;

import org.springframework.http.HttpStatus;

public class GitHubClientNotFoundException extends Exception{

    public GitHubClientNotFoundException() {
    }

    public GitHubClientNotFoundException(String message) {
        super(message);
    }

    public GitHubClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitHubClientNotFoundException(Throwable cause) {
        super(cause);
    }

    public GitHubClientNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
