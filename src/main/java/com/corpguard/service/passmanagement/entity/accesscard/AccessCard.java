package com.corpguard.service.passmanagement.entity.accesscard;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "access_card_tbl")
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(nullable = false)
    private Boolean active = false;

    @Column(nullable = false)
    private Boolean issues = false;

    @OneToMany(mappedBy = "accessCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAccessCard> employeeAccessCards;

    // getter

    public Integer getCardId() {
        return cardId;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getIssues() {
        return issues;
    }

    public List<EmployeeAccessCard> getEmployeeAccessCards() {
        return employeeAccessCards;
    }

    //Setter

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setIssues(Boolean issues) {
        this.issues = issues;
    }

    public void setEmployeeAccessCards(List<EmployeeAccessCard> employeeAccessCards) {
        this.employeeAccessCards = employeeAccessCards;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AccessCard that = (AccessCard) o;
        return Objects.equals(cardId, that.cardId) && Objects.equals(active, that.active) && Objects.equals(issues, that.issues) && Objects.equals(employeeAccessCards, that.employeeAccessCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, active, issues, employeeAccessCards);
    }


    @Override
    public String toString() {
        return "AccessCard{" +
                "cardId=" + cardId +
                ", active=" + active +
                ", issues=" + issues +
                ", employeeAccessCards=" + employeeAccessCards +
                '}';
    }
}
