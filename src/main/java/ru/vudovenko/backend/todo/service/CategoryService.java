package ru.vudovenko.backend.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.vudovenko.backend.todo.entity.Category;
import ru.vudovenko.backend.todo.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе выполняются несолько SQL запросов и возникнет исключение - то все выполненные операции откатятся (Rollback)
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category findById(Long id) {
        Optional<Category> categoryOptional = repository.findById(id);

        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            throw new EmptyResultDataAccessException("Category with id " + id + " not found", 1);
        }
    }

    public List<Category> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public Category update(Category category) {
        return repository.save(category);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
