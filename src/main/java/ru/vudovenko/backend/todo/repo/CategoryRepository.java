package ru.vudovenko.backend.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vudovenko.backend.todo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
