package ru.iporsyak.first_spring_gradle.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.iporsyak.first_spring_gradle.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // либо пусто, либо какое то значение
    @Query("select t " +
            "from Task t " +
            "where :title is not null or :title = '' " +
            "and :completed is null or t.completed =:completed " +
            "and :priorityId is null or t.priority =:priorityId " +
            "and :categoryId is null or t.category =:categoryId " +
            "and :email is null or t.user.email =:email " +
            // ищем по периоду пвемени
            "and ((cast(:dateFrom as timestamp) is null) or t.taskDate >= :dateFrom) " +
            "and ((cast(:dateTo as timestamp) is null) or t.taskDate <= :dateTo)")
    Page<Task> findByParams(@Param("title") String title,
                            @Param("completed") Integer completed,
                            @Param("priorityId") Long priorityId,
                            @Param("categoryId") Long categoryId,
                            @Param("email") String email,
                            @Param("dateFrom") Date dateFrom,
                            @Param("dateTo") Date dateTo,
                            Pageable pageable);

    List<Task> findByUserEmailOrderByTitleAsc(String user_email);

}
