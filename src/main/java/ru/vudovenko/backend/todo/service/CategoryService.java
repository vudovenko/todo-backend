package ru.vudovenko.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.vudovenko.backend.todo.entity.Category;
import ru.vudovenko.backend.todo.repo.CategoryRepository;

import java.util.Optional;

@Service
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе выполняются несолько SQL запросов и возникнет исключение - то все выполненные операции откатятся (Rollback)
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Long id) {
        Optional<Category> categoryOptional = repository.findById(id);
        return categoryOptional
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
    }
}
