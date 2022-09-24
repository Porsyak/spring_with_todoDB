package ru.iporsyak.first_spring_gradle.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iporsyak.first_spring_gradle.entity.Stat;

@Repository
/*
наследуемся не от JpaRepository т.к. нам не нужно лишние методы потому, что
связь OneToOne
*/
public interface StatRepository extends CrudRepository<Stat, Long> {
    // всегда получаем только один обьект, т.к. связь OneToOne
    Stat findByUserEmail(String email);
}
