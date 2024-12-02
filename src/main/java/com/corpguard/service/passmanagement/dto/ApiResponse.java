package com.corpguard.service.passmanagement.dto;

import java.util.List;

public class ApiResponse<T> {

    private Status status;
    private String message;
    private T data;
    private List<Error> errors;
}


enum Status{
    SUCCESS,FAILED,BAD_REQUEST,UNAUTHORIZED
}

class Error{
    private String field;
    private String message;
}