package br.com.lucasdev3.financesystemapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRegistryModel {

    private String title;
    private double expenseValue;
    private String description;

}
