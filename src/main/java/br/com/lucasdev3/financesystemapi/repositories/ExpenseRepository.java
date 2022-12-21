package br.com.lucasdev3.financesystemapi.repositories;

import br.com.lucasdev3.financesystemapi.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
