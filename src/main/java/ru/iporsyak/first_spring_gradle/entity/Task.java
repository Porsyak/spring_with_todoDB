package ru.iporsyak.first_spring_gradle.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "todolist", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@Getter
@Setter
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    @Column(name = "title", nullable = false, length = -1)
    private String title;


    @Column(name = "completed", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean  completed;


    @Column(name = "task_date")
    private Date taskDate;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    // TODO THIS IS FROM HIBERNATE
//    @Basic
//    @Column(name = "category_id", nullable = true)
//    private Long categoryId;
//    @Basic
//    @Column(name = "priority_id", nullable = true)
//    private Long priorityId;
//    @Basic
//    @Column(name = "user_id", nullable = false)
//    private long userId;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title;
    }
}
