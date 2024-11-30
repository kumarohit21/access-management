package com.corpguard.service.passmanagement.service;

import com.corpguard.service.passmanagement.employee.entity.Employee;
import com.corpguard.service.passmanagement.employee.repo.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AccessCardServiceImpl implements AccessCardService{

    private static EmployeeRepository employeeRepository;

    @Transactional
    public void issueCard(Integer empId, Integer cardId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        AccessCard accessCard = accessCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Access Card not found"));

        if (accessCard.getActive()) {
            throw new RuntimeException("Card is already active");
        }

        EmployeeAccessCard employeeAccessCard = new EmployeeAccessCard();
        employeeAccessCard.setEmployee(employee);
        employeeAccessCard.setAccessCard(accessCard);
        employeeAccessCard.setIssuedDt(LocalDateTime.now());

        accessCard.setActive(true);
        accessCard.setIssues(true);

        employeeAccessCardRepository.save(employeeAccessCard);
        accessCardRepository.save(accessCard);
    }
}
