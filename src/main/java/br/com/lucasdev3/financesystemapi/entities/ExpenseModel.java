package br.com.lucasdev3.financesystemapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "TB_EXPENSE")
@Getter
@Setter
public class ExpenseModel {

    @Id
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EXPENSE_VALUE")
    private double expenseValue;

    @Column(name = "DESCRIPTION")
    private String description;

}
