package com.corpguard.service.passmanagement.dto;

public class Error {

    private String field;
    private String message;

    // Private constructor to force the use of the builder pattern
    private Error(Builder builder) {
        this.field = builder.field;
        this.message = builder.message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    // Builder pattern for Error
    public static class Builder {
        private String field;
        private String message;

        public Builder setField(String field) {
            this.field = field;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            return new Error(this);
        }
    }

    // Setter methods (optional, again useful for manual modifications)
    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
