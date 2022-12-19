package br.com.lucasdev3.financesystemapi.entities;

import br.com.lucasdev3.financesystemapi.models.ExpenseAndIncomeRegistryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity(name = "TB_INCOME")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Income extends GenericEntity {

    @Column(name = "TITLE")
    @NotBlank(message = "title is mandatory")
    private String title;

    @Column(name = "INCOME_VALUE")
    private Double incomeValue;

    @NotBlank(message = "description is mandatory")
    @Column(name = "DESCRIPTION")
    private String description;

    public Income(ExpenseAndIncomeRegistryModel model) {
        this.title = model.getTitle();
        this.incomeValue = model.getValue();
        this.description = model.getDescription();
    }

}
