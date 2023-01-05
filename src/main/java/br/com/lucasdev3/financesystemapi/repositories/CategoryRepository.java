package br.com.lucasdev3.financesystemapi.repositories;

import br.com.lucasdev3.financesystemapi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  boolean existsByName(String name);

}
