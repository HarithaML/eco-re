/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.common;

import com.project.enums.ResponseStatus;

/**
 * @author hari
 */
public class Response {
    private ResponseStatus status;
    private String message;

    private Response(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResponseBuilder {
        private ResponseStatus status;
        private String message;

        public ResponseBuilder withStatus(ResponseStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Response build() {
            return new Response(status, message);
        }
    }

}
