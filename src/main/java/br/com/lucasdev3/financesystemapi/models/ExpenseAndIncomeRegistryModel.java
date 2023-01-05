package br.com.lucasdev3.financesystemapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseAndIncomeRegistryModel {

  private String title;

  private Double value = 0d;

  private String description;

  private Integer categoryId;

}
