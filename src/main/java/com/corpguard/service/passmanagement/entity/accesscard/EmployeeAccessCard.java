package com.corpguard.service.passmanagement.entity.accesscard;

import com.corpguard.service.passmanagement.entity.employee.Employee;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "employee_access_card_tbl")
public class EmployeeAccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    @Column(nullable = false)
    private Integer empId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private AccessCard accessCard;

    @Column(nullable = false)
    private LocalDateTime issuedDt;

    @Column
    private LocalDateTime returnDt;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public AccessCard getAccessCard() {
        return accessCard;
    }

    public void setAccessCard(AccessCard accessCard) {
        this.accessCard = accessCard;
    }

    public LocalDateTime getIssuedDt() {
        return issuedDt;
    }

    public void setIssuedDt(LocalDateTime issuedDt) {
        this.issuedDt = issuedDt;
    }

    public LocalDateTime getReturnDt() {
        return returnDt;
    }

    public void setReturnDt(LocalDateTime returnDt) {
        this.returnDt = returnDt;
    }
}
