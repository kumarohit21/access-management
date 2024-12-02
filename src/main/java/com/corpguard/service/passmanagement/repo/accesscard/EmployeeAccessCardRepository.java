package com.corpguard.service.passmanagement.repo.accesscard;

import com.corpguard.service.passmanagement.entity.accesscard.EmployeeAccessCard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeAccessCardRepository extends JpaRepository<EmployeeAccessCard, Integer> {

    // Deactivate all active cards for an employee
    @Modifying
    @Transactional
    @Query("UPDATE EmployeeAccessCard eac SET eac.accessCard.active = false " +
            "WHERE eac.employee.empId = :empId AND eac.returnDt IS NULL")
    void deactivateAllActiveCardsForEmployee(Integer empId);

    // Issue a card to an employee (inserting into Employee_Access_Card table)
    @Modifying
    @Transactional
    @Query("INSERT INTO EmployeeAccessCard (employee, accessCard, issuedDt) " +
            "VALUES (SELECT e FROM Employee e WHERE e.empId = :empId, " +
            "SELECT ac FROM AccessCard ac WHERE ac.cardId = :cardId, CURRENT_TIMESTAMP)")
    void issueCardToEmployee(Integer empId, Integer cardId);

    // Return a specific card for an employee (update the return date)
    @Modifying
    @Transactional
    @Query("UPDATE EmployeeAccessCard eac SET eac.returnDt = CURRENT_TIMESTAMP " +
            "WHERE eac.employee.empId = :empId AND eac.accessCard.cardId = :cardId AND eac.returnDt IS NULL")
    void returnCardForEmployee(Integer empId, Integer cardId);

    // Mark the card as inactive in the AccessCard table
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.active = false WHERE ac.cardId = :cardId")
    int deactivateCard(Integer cardId);

    // Mark the card as active in the AccessCard table
    @Modifying
    @Transactional
    @Query("UPDATE AccessCard ac SET ac.active = true, ac.issues = true WHERE ac.cardId = :cardId")
    int activateCard(Integer cardId);

    // Find all active cards for an employee
    @Query("SELECT eac FROM EmployeeAccessCard eac WHERE eac.employee.empId = :empId AND eac.returnDt IS NULL")
    List<EmployeeAccessCard> findActiveCardsByEmployee(Integer empId);

}
