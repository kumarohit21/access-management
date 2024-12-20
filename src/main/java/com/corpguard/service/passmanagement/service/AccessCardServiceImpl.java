package com.corpguard.service.passmanagement.service;

import com.corpguard.service.passmanagement.dto.AccessCardDTO;
import com.corpguard.service.passmanagement.entity.accesscard.AccessCard;
import com.corpguard.service.passmanagement.repo.accesscard.AccessCardRepository;
import com.corpguard.service.passmanagement.repo.accesscard.EmployeeAccessCardRepository;
import com.corpguard.service.passmanagement.repo.employee.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccessCardServiceImpl implements AccessCardService{

    private final EmployeeRepository employeeRepository;
    private final AccessCardRepository accessCardRepository;
    private final EmployeeAccessCardRepository employeeAccessCardRepository;

    public AccessCardServiceImpl(EmployeeRepository employeeRepository, AccessCardRepository accessCardRepository, EmployeeAccessCardRepository employeeAccessCardRepository) {
        this.employeeRepository = employeeRepository;
        this.accessCardRepository = accessCardRepository;
        this.employeeAccessCardRepository = employeeAccessCardRepository;
    }

    // Issue a card to an employee
    @Transactional
    @Override
    public void issueCardToEmployee(Integer empId, Integer cardId) {
        // Step 1: Deactivate any previously active cards for the employee
        employeeAccessCardRepository.deactivateAllActiveCardsForEmployee(empId);

        // Step 2: Issue the new card (insert into Employee_Access_Card table)
        employeeAccessCardRepository.issueCardToEmployee(empId, cardId);

        // Step 3: Update AccessCard table to set active = TRUE and issues = TRUE for the new card
        accessCardRepository.issueAndActivateCard(cardId);
    }

    // Mark the card as returned for an employee
    @Transactional
    @Override
    public void returnCard(Integer empId, Integer cardId) {
        // Step 1: Set return date in Employee_Access_Card table
        employeeAccessCardRepository.returnCardForEmployee(empId, cardId);

        // Step 2: Mark the card as inactive in Access_Card table
        accessCardRepository.returnAndDeactivate(cardId);
    }

    @Override
    public Page<AccessCardDTO> fetchCards(Boolean active, Boolean issues, Pageable pageable) {
        Page<AccessCard> cardsByActiveAndIssued = accessCardRepository.getCardsByActiveAndIssues(active, issues, pageable);
        List<AccessCardDTO> data =  cardsByActiveAndIssued.get().map(a-> new AccessCardDTO(a.getCardId(),a.getIssues(),a.getActive())).toList();
        return new PageImpl<>(data,cardsByActiveAndIssued.getPageable(),cardsByActiveAndIssued.getTotalPages());

    }

}
