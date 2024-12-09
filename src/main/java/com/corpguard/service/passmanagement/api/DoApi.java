package com.corpguard.service.passmanagement.api;


import com.corpguard.service.passmanagement.dto.GenericResponse;
import com.corpguard.service.passmanagement.dto.CardIssueRequest;
import com.corpguard.service.passmanagement.dto.CardIssuedResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition(info = @Info( title = " Access Card APIs", version = "v1"))
@RequestMapping(path = "/access-card/api/v1/do")
public interface DoApi {

    static final String SUCCESS_EXAMPLE = "{\n" +
            "  \"status\": \"SUCCESS\",\n" +
            "  \"message\": \"Card Issued Successfully\",\n" +
            "  \"data\": {\"cardId\":123,\"employeeId\":456}\n" +
            "}";

    static final String INTERAL_SERVER_ERR_EXAMPLE = "{\n" +
            "  \"status\": \"FAILED\",\n" +
            "  \"message\": \"Request Could not be processed. Reason: exception\"\n" +

            "}";
    static final String BAD_REQ_EXAMPLE = "{\n" +
            "  \"status\": \"BAD_REQUEST\",\n" +
            "  \"message\": \"Invalid Input\",\n" +
            "  \"errors\": [\n" +
            "    {\n" +
            "      \"field\": \"employeeId\",\n" +
            "      \"message\": \"employee id cannot be empty\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    static final String NOT_FOUND_EXAMPLE = "{\n" +
            "  \"status\": \"NOT_FOUND\",\n" +
            "  \"message\": \"Data not found for given input\"\n" +
            "}";

    static final String UNAUTHORIZED_EXAMPLE = "{\n" +
            "  \"status\": \"UNAUTHORIZED\",\n" +
            "  \"message\": \"Invalid Credentials\"\n" +
            "}";



    @PostMapping(value = "/issue/card", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "issue card to employee",content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class) ,examples = @ExampleObject( value = SUCCESS_EXAMPLE)) ),
            @ApiResponse(responseCode = "500", description = "Internal server error occurred",content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class), examples = @ExampleObject( value = INTERAL_SERVER_ERR_EXAMPLE) ) ),
            @ApiResponse(responseCode = "400", description = "Invalid Input",content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class) ,examples = @ExampleObject( value = BAD_REQ_EXAMPLE) ) ),
            @ApiResponse(responseCode = "404", description = "Employee or card data not found",content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class), examples = @ExampleObject( value = NOT_FOUND_EXAMPLE) ) ),
            @ApiResponse(responseCode = "401", description = "Unauthorized",content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenericResponse.class), examples = @ExampleObject( value = UNAUTHORIZED_EXAMPLE) ) )
    })
    @Operation(summary = "Issue access card to employee", description = "Use this api to issue given cardId to given employee Id")
    ResponseEntity<GenericResponse<CardIssuedResponse>> doIssueCard(@RequestBody CardIssueRequest cardIssueRequest);
}
