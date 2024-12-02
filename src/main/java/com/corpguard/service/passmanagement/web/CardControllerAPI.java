package com.corpguard.service.passmanagement.web;


import com.corpguard.service.passmanagement.dto.ApiResponse;
import com.corpguard.service.passmanagement.dto.CardIssueRequest;
import com.corpguard.service.passmanagement.dto.CardIssuedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CardControllerAPI {

    @PostMapping(value = "/do/issue/card", consumes = "application/json", produces = "application/json")
    ResponseEntity<ApiResponse<CardIssuedResponse>> doIssueCard(@RequestBody CardIssueRequest cardIssueRequest);
}
