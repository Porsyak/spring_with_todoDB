package ru.iporsyak.first_spring_gradle.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iporsyak.first_spring_gradle.entity.Category;
import ru.iporsyak.first_spring_gradle.search.CategorySearchValues;
import ru.iporsyak.first_spring_gradle.service.CategoryService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/category")
public class  CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/id")
    public Optional<Category> findById() {
        return categoryService.findById(60136);
    }

    @PostMapping("/all")
    public List<Category> findAll(@RequestBody String email) {
        return categoryService.findAll(email);

    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != 0) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        if (category.getTitle() == null || category.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        if (category.getId() == 0) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        if (category.getTitle() == null || category.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        categoryService.update(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") long id) {
        try {
            categoryService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        List<Category> categoryList = categoryService
                .findByTitle(categorySearchValues.getTitle(), categorySearchValues.getEmail());
        return ResponseEntity.ok(categoryList);

    }

    @PostMapping("/id2")
    public ResponseEntity<Optional<Category>> findId(@RequestBody long id){
       return ResponseEntity.ok(categoryService.findId(id));
    }


}

//record CategorySearchValues(String title, String email){}
