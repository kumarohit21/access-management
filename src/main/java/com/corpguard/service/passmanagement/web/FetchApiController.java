package com.corpguard.service.passmanagement.web;

import com.corpguard.service.passmanagement.api.FetchAPI;
import com.corpguard.service.passmanagement.dto.AccessCardDTO;
import com.corpguard.service.passmanagement.dto.GenericResponse;
import com.corpguard.service.passmanagement.dto.Status;
import com.corpguard.service.passmanagement.service.AccessCardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchApiController implements FetchAPI {

    private final AccessCardService  accessCardService;

    public FetchApiController(AccessCardService accessCardService) {
        this.accessCardService = accessCardService;
    }

    @Override
    public ResponseEntity<GenericResponse<Page<AccessCardDTO>>> fetchCards(Boolean active, Boolean issued, Integer page, Integer pageSize) {
        GenericResponse.Builder<Page<AccessCardDTO>> builder = GenericResponse.builder();
        try{
           Page<AccessCardDTO> accessCardDTOS = accessCardService.fetchCards(active, issued, PageRequest.of(page, pageSize));
            GenericResponse<Page<AccessCardDTO>> response = builder.data(accessCardDTOS).status(Status.SUCCESS).build();

            return ResponseEntity.ok(response);

       }catch (Exception e){
            GenericResponse<Page<AccessCardDTO>> response = builder.message(e.getMessage()).status(Status.FAILED).build();
            return ResponseEntity.internalServerError().body(response);
       }
    }
}
