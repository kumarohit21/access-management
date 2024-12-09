package com.corpguard.service.passmanagement.api;


import com.corpguard.service.passmanagement.dto.AccessCardDTO;
import com.corpguard.service.passmanagement.dto.CardIssueRequest;
import com.corpguard.service.passmanagement.dto.CardIssuedResponse;
import com.corpguard.service.passmanagement.dto.GenericResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@OpenAPIDefinition(info = @Info( title = " Access Card fetch APIs", version = "v1"))
@RequestMapping(path = "/access-card/api/v1/fetch")
public interface FetchAPI {
    @PostMapping(value = "/cards", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Fetch Page", description = "Use this api to get access card")
    ResponseEntity<GenericResponse<Page<AccessCardDTO>>> fetchCards(@RequestParam Boolean active, @RequestParam Boolean issued, @RequestParam Integer page, @RequestParam Integer pageSize);
}
