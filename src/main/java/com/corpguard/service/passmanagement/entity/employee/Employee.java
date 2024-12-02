package com.corpguard.service.passmanagement.entity.employee;

import com.corpguard.service.passmanagement.entity.accesscard.EmployeeAccessCard;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(nullable = false, length = 100)
    private String empName;

    @Column(nullable = false, length = 100)
    private String empDept;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAccessCard> accessCards;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public List<EmployeeAccessCard> getAccessCards() {
        return accessCards;
    }

    public void setAccessCards(List<EmployeeAccessCard> accessCards) {
        this.accessCards = accessCards;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(empId, employee.empId) && Objects.equals(empName, employee.empName) && Objects.equals(empDept, employee.empDept) && Objects.equals(accessCards, employee.accessCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empName, empDept, accessCards);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empDept='" + empDept + '\'' +
                ", accessCards=" + accessCards +
                '}';
    }
}
