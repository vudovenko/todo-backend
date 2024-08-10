package ru.vudovenko.backend.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vudovenko.backend.todo.entity.Priority;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

    @Query("""
            from Priority p
            where lower(p.title) like lower(concat('%', :title, '%'))
            and p.user.email = :email
            order by p.title asc
            """)
    List<Priority> findByTitle(@Param("title") String title, @Param("email") String email);

    /**
     * Поиск приоритетов пользователя по почте в порядке возрастания id
     *
     * @param email почта пользователя
     * @return список приоритетов пользователя
     */
    List<Priority> findByUserEmailOrderByIdAsc(String email);
}
