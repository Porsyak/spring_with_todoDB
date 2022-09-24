package ru.iporsyak.first_spring_gradle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iporsyak.first_spring_gradle.entity.Priority;
import ru.iporsyak.first_spring_gradle.repo.PriorityRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PriorityService {
    private final PriorityRepository repository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.repository = priorityRepository;
    }

    public List<Priority> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Priority add(Priority priority){
        return repository.save(priority);
    }

    public void update(Priority priority){
        repository.save(priority);
    }

    public void deleteById(long id){
        repository.deleteById(id);
    }

    public Optional<Priority> findById(long id){
        return repository.findById(id);
    }

    public List<Priority> find(String title, String email){
        return repository.findByTitle(title, email);
    }
}
