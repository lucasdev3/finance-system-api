package br.com.lucasdev3.financesystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.lucasdev3.financesystemapi.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
