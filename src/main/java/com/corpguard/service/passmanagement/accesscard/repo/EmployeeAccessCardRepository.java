package com.corpguard.service.passmanagement.accesscard.repo;

import com.corpguard.service.passmanagement.accesscard.entity.EmployeeAccessCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeAccessCardRepository extends JpaRepository<EmployeeAccessCard, Integer> {
    List<EmployeeAccessCard> findByEmployeeEmpIdAndReturnDtIsNull(Integer empId);
    List<EmployeeAccessCard> findByAccessCardCardIdAndReturnDtIsNull(Integer cardId);
}
