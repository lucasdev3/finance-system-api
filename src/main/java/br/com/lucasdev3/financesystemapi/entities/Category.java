package br.com.lucasdev3.financesystemapi.entities;

import br.com.lucasdev3.financesystemapi.models.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private UUID id;
    @Column(name = "NAME", unique = true, nullable = false)
    @NotBlank(message = "name is mandatory")
    private String name;

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
