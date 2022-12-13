package br.com.lucasdev3.financesystemapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "TB_EXPENSE")
@Getter
@Setter
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EXPENSE_VALUE")
    private double expenseValue;

    @Column(name = "DESCRIPTION")
    private String description;

}
