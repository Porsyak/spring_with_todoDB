package ru.iporsyak.first_spring_gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iporsyak.first_spring_gradle.entity.Category;
import ru.iporsyak.first_spring_gradle.entity.Priority;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    List<Priority> findByUserEmailOrderByTitleAsc(String email);

    //TODO BAD QUERY, throw UnsatisfiedDependencyException.
//    @Query("select p " +
//            "from Priority p " +
//            "where (:title is not null or :title = '' " +
//            "or lower(p.title) like lower(concat('%', :title, '%' ))) " +
//            "and p.user.email =: email order by p.title asc ")
//    List<Priority> findByTitle(@Param("title") String title, @Param("email") String email);
//}

    @Query("select c " +
            "from Priority c " +
            "where c.user.email = :email " +
            "or c.title = concat('%', :title, '%' )")
    List<Priority> findByTitle(@Param("title")String title, @Param("email")String email);
}
