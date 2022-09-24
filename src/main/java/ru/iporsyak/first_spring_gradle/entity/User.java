package ru.iporsyak.first_spring_gradle.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "user_data", schema = "todolist", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @Column(name = "userpassword", nullable = false, length = -1)
    private String userpassword;

    @Column(name = "username", nullable = false, length = -1)
    private String username;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

//    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();


//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Task> tasks;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Category> categories;
//
//    @OneToMany(mappedBy = "user")
//    private List<Priority> priorities;
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Activity activity;
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Stat stat;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
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
