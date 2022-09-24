package ru.iporsyak.first_spring_gradle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iporsyak.first_spring_gradle.entity.Category;
import ru.iporsyak.first_spring_gradle.entity.Stat;
import ru.iporsyak.first_spring_gradle.repo.CategoryRepository;
import ru.iporsyak.first_spring_gradle.repo.StatRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatService {
    private final StatRepository repository;

    public StatService(StatRepository repository) {
        this.repository = repository;
    }

    public Stat findStat(String email){
        return repository.findByUserEmail(email);
    }


}
