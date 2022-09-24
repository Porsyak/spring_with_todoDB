package ru.iporsyak.first_spring_gradle.service;

import org.springframework.stereotype.Service;
import ru.iporsyak.first_spring_gradle.entity.Category;
import ru.iporsyak.first_spring_gradle.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@org.springframework.transaction.annotation.Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }

    public List<Category> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category) {
        return repository.save(category);
    }

    public void update(Category category) {
        repository.save(category);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Category> findByTitle(String title, String email) {
        return repository.findByTitle(title, email);
    }

    public Optional<Category> findId(long id) {
       return repository.findById(id);

    }

}
