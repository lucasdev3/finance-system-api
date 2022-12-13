package br.com.lucasdev3.financesystemapi.repositories;

import br.com.lucasdev3.financesystemapi.entities.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, String> {
}
