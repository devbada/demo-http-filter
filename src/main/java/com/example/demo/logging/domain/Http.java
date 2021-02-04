package com.example.demo.logging.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @since       2021.01.26
 * @author      minam
 * //  http
 **********************************************************************************************************************/
@Data
@Builder
public class Http {

    // ("요청")
    private Request request;

    // ("응답")
    private Response response;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    // ("요청")
    public static class Request {

        // ("method")
        private String method;

        // ("URL")
        private String url;

        // ("headers")
        private Map<String, String> headers;

        // ("body")
        private String body;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    // ("응답")
    public static class Response {

        // ("status")
        private Integer status;

        // ("headers")
        private Map<String, String> headers;

        // ("body")
        private String body;
    }
}
