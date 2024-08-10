package ru.vudovenko.backend.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vudovenko.backend.todo.entity.Priority;
import ru.vudovenko.backend.todo.repo.PriorityRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PriorityService {

    private final PriorityRepository repository;

    public List<Priority> findAll(String email) {
        return repository.findByUserEmailOrderByIdAsc(email);
    }

    public Priority add(Priority priority) {
        return repository.save(priority);
    }

    public Priority update(Priority priority) {
        return repository.save(priority);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Priority findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Priority> findByTitle(String title, String email) {
        return repository.findByTitle(title, email);
    }
}
