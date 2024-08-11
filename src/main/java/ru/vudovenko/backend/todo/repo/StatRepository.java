package ru.vudovenko.backend.todo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vudovenko.backend.todo.entity.Stat;

/**
 * Репозиторий для получения статистики пользователя
 * <p>
 * Всегда получаем только 1 объект, т.к. 1 пользователь содержит только 1 строку статистики (связь "один к одному").
 * Поэтому наследование от CrudRepository, а не от JpaRepository.
 * Нет необходимости методов для получения нескольких объектов статистики.
 */
@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

    /**
     * Метод для получения статистики пользователя по почте
     *
     * @param email почта пользователя
     * @return статистика пользователя
     */
    Stat findByUserEmail(String email);
}
