package ru.vudovenko.backend.todo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;
import java.util.Set;

/**
 * пользователь - основной объект, с которым связаны все остальные (через внешние ключи)
 */
@Entity
@Table(name = "user_data", schema = "todolist", catalog = "postgres")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    @Column(name = "userpassword")
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Task> tasks;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Category> categories;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Priority> priorities;
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Activity activity; // активность пользователя (активация и любые другие)
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Stat stat; // общая статистика пользователя по всем задачам

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

    @Override
    public String toString() {
        return username;
    }
}
