package com.corpguard.service.passmanagement.repo.accesscard;

import com.corpguard.service.passmanagement.entity.accesscard.AccessCard;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface AccessCardRepository extends PagingAndSortingRepository<AccessCard, Integer> {

    // Activate the card and mark it as issued (set active = true, issues = true)
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.active = true, ac.issues = true WHERE ac.cardId = :cardId")
    void issueAndActivateCard(Integer cardId);

    // Mark the card as issued (set issues = true)
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.issues = true WHERE ac.cardId = :cardId")
    void issueCard(Integer cardId);

    // Activate the card (set active = true)
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.active = true WHERE ac.cardId = :cardId")
    void activateCard(Integer cardId);

    // Deactivate the card and mark it as not issued (set active = false, issues = false)
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.active = false, ac.issues = false WHERE ac.cardId = :cardId")
    void returnAndDeactivate(Integer cardId);

    // Find a card by its cardId (standard find method provided by JpaRepository)
    AccessCard findByCardId(Integer cardId);

    Page<AccessCard> getCardsByActiveAndIssues(Boolean active, Boolean issues, Pageable pageable);

}