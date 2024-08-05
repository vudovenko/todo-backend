package ru.vudovenko.backend.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

/**
 * справочноное значение - приоритет пользователя
 * может использовать для своих задач
 */
@Entity
@Table(name = "priority", schema = "todolist", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Priority {

    // указываем, что поле заполняется в БД
    // нужно, когда добавляем новый объект и он возвращается уже с новым id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ToString.Include
    private String title;
    private String color;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
//    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return id.equals(priority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
