package ru.vudovenko.backend.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vudovenko.backend.todo.entity.Stat;
import ru.vudovenko.backend.todo.repo.StatRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class StatService {

    private final StatRepository repository;

    public Stat findStat(String email) {
        return repository.findByUserEmail(email);
    }
}
