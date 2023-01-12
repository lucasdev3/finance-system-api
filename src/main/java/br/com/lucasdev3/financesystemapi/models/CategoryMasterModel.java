package br.com.lucasdev3.financesystemapi.models;

import java.util.Set;
import br.com.lucasdev3.financesystemapi.entities.Expense;
import br.com.lucasdev3.financesystemapi.entities.Income;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMasterModel {

  private Integer id;
  private String name;
  private Set<Expense> expenses;
  private Set<Income> incomes;

}
