package ru.vudovenko.backend.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vudovenko.backend.todo.entity.Task;
import ru.vudovenko.backend.todo.repo.TaskRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Task add(Task task) {
        return repository.save(task);
    }

    public Task update(Task task) {
        return repository.save(task);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Page<Task> findByParams(String text, Boolean completed, Long priorityId,
                                   Long categoryId, String email, Date dateFrom,
                                   Date dateTo, PageRequest paging) {
        return repository.findByParams(text, completed, priorityId, categoryId,
                email, dateFrom, dateTo, paging);
    }

    public Task findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
