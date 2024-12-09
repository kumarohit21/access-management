package com.corpguard.service.passmanagement.service;

import com.corpguard.service.passmanagement.dto.AccessCardDTO;
import com.corpguard.service.passmanagement.entity.accesscard.AccessCard;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccessCardService {

    // Issue a card to an employee
    @Transactional
    void issueCardToEmployee(Integer empId, Integer cardId);

    // Mark the card as returned for an employee
    @Transactional
    void returnCard(Integer empId, Integer cardId);

    Page<AccessCardDTO> fetchCards(Boolean active, Boolean issues, Pageable pageable);
}
