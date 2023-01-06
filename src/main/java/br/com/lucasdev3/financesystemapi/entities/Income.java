package br.com.lucasdev3.financesystemapi.entities;

import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_INCOME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false, updatable = false, insertable = false)
    private UUID id;

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
