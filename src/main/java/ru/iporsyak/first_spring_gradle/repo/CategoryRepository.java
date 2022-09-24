package ru.iporsyak.first_spring_gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iporsyak.first_spring_gradle.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUserEmailOrderByTitleAsc(String email);

    @Query("select c " +
            "from Category c " +
            "where c.user.email = :email " +
            "or c.title = concat('%', :title, '%' )")
    List<Category> findByTitle(@Param("title")String title,@Param("email")String email);
}
