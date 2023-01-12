package br.com.lucasdev3.financesystemapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.lucasdev3.financesystemapi.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  boolean existsByName(String name);

}
