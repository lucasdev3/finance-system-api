package br.com.lucasdev3.financesystemapi.entities;

import br.com.lucasdev3.financesystemapi.models.CategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private Integer id;
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
