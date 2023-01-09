package br.com.lucasdev3.financesystemapi.entities;

import br.com.lucasdev3.financesystemapi.models.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false, updatable = false, insertable = false)
  private Integer id;
  @Column(name = "NAME", unique = true, nullable = false)
  @NotBlank(message = "name is mandatory")
  private String name;

  @Column(name = "TYPE", unique = true, nullable = false)
  @NotBlank(message = "type is mandatory")
  private String type;

  @OneToMany(mappedBy = "category")
  @JsonIgnore
  private Set<Expense> expenses;

  @OneToMany(mappedBy = "category")
  @JsonIgnore
  private Set<Income> incomes;

  public Category(CategoryModel model) {
    this.name = model.getName();
  }
}
