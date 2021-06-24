package com.kmilewsk.github.Configuration.Web.Client;

import com.kmilewsk.github.Exception.GitHubClientApiError;
import com.kmilewsk.github.Exception.GitHubClientNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignApiDecoder implements ErrorDecoder {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {

        var httpStatus = HttpStatus.valueOf(response.status());

        if (httpStatus.equals(HttpStatus.NOT_FOUND)) {
            logger.error("Error took place when using Feign client to send " + response.request().url() + ". Status code " + response.status() + ", methodKey = " + methodKey);
            var userLogin = response.request().url().substring(response.request().url().lastIndexOf("/") + 1);
            return new GitHubClientNotFoundException("Can not find result for " + userLogin);
        } else if (httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
            return new GitHubClientApiError();
        } else {
            return new Exception(response.reason());
        }
    }

}
