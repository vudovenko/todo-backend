package ru.vudovenko.backend.todo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

/**
 * пользователь - основной объект, с которым связаны все остальные (через внешние ключи)
 */
@Entity
@Table(name = "user_data", schema = "todolist", catalog = "postgres")
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @ToString.Include
    private String username;

    @Column(name = "userpassword")
    private String password;

//    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
//    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
