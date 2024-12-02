package com.corpguard.service.passmanagement.service;

import jakarta.transaction.Transactional;

public interface AccessCardService {

    // Issue a card to an employee
    @Transactional
    void issueCardToEmployee(Integer empId, Integer cardId);

    // Mark the card as returned for an employee
    @Transactional
    void returnCard(Integer empId, Integer cardId);
}
