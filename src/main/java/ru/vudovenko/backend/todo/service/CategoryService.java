package ru.vudovenko.backend.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vudovenko.backend.todo.entity.Category;
import ru.vudovenko.backend.todo.repo.CategoryRepository;

import java.util.List;

@Service
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе выполняются несолько SQL запросов и возникнет исключение - то все выполненные операции откатятся (Rollback)
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return repository.save(category);
    }
}
