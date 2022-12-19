package br.com.lucasdev3.financesystemapi.repositories;

import br.com.lucasdev3.financesystemapi.entities.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends GenericRepository<Income, Integer> {

}
