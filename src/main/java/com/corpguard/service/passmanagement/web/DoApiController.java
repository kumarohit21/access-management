package com.corpguard.service.passmanagement.web;

import com.corpguard.service.passmanagement.api.DoApi;
import com.corpguard.service.passmanagement.dto.GenericResponse;
import com.corpguard.service.passmanagement.dto.CardIssueRequest;
import com.corpguard.service.passmanagement.dto.CardIssuedResponse;
import com.corpguard.service.passmanagement.dto.Status;
import com.corpguard.service.passmanagement.service.AccessCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoApiController implements DoApi {

    private final AccessCardService accessCardService;

    public DoApiController(AccessCardService accessCardService) {
        this.accessCardService = accessCardService;
    }

    @Override
    public ResponseEntity<GenericResponse<CardIssuedResponse>> doIssueCard(CardIssueRequest cardIssueRequest) {
        GenericResponse.Builder<CardIssuedResponse> builder=   GenericResponse.builder();

        try {
            accessCardService.issueCardToEmployee(cardIssueRequest.empId(), cardIssueRequest.cardId());
            CardIssuedResponse response = new CardIssuedResponse(cardIssueRequest.cardId(),cardIssueRequest.empId());
            GenericResponse<CardIssuedResponse> body =   builder.data(response).status(Status.SUCCESS).build();
            return  ResponseEntity.ok(body);

        } catch (Exception e) {
            GenericResponse<CardIssuedResponse> body  = builder.message(e.getMessage()).status(Status.FAILED).build();
            return  ResponseEntity.internalServerError().body(body);
        }
    }
}
