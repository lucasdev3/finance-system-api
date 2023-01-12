package br.com.lucasdev3.financesystemapi.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_INCOME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false, updatable = false, insertable = false)
  private Integer id;

  @Column(name = "TITLE", nullable = false)
  @NotBlank(message = "title is mandatory")
  private String title;

  @Column(name = "INCOME_VALUE", nullable = false)
  private Double incomeValue;

  @Column(name = "DESCRIPTION", nullable = false)
  @NotBlank(message = "description is mandatory")
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  @NotNull
  private Category category;

  public Income(ExpenseAndIncomeRegistryModel model, Category category) {
    this.title = model.getTitle();
    this.incomeValue = model.getValue();
    this.description = model.getDescription();
    this.category = category;
  }

}
