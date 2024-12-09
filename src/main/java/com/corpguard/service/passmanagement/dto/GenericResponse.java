package com.corpguard.service.passmanagement.dto;

import java.util.List;

public class GenericResponse<T> {

    private final Status status;
    private final String message;
    private final T data;
    private final List<Error> errors;

    // Private constructor to force the use of the builder pattern
    private GenericResponse(Builder<T> builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.errors = builder.errors;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public static <U> Builder<U> builder(){
        return new Builder<>();
    }

    // Builder pattern for ApiResponse
    public static class Builder<T> {
        private Status status;
        private String message;
        private T data;
        private List<Error> errors;

        public Builder<T> status(Status status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> errors(List<Error> errors) {
            this.errors = errors;
            return this;
        }

        public GenericResponse<T> build() {
            return new GenericResponse<>(this);
        }
    }


}


