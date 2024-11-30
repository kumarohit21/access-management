package com.corpguard.service.passmanagement.accesscard.repo;

import com.corpguard.service.passmanagement.accesscard.entity.AccessCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCardRepository extends JpaRepository<AccessCard, Integer> {
    // Add custom queries if needed
}
