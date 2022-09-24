package ru.iporsyak.first_spring_gradle.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iporsyak.first_spring_gradle.entity.Category;
import ru.iporsyak.first_spring_gradle.entity.Priority;
import ru.iporsyak.first_spring_gradle.search.PrioritySearchValues;
import ru.iporsyak.first_spring_gradle.service.PriorityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {
    private final PriorityService service;

    public PriorityController(PriorityService service) {
        this.service = service;
    }

    @PostMapping("/all")
    public List<Priority> all(@RequestBody String email) {
        return service.findAll(email);
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {
        if (priority.getId() != 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.add(priority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping ("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {
        if (priority.getId() == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.update(priority);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<Optional<Priority>> findId(@RequestBody long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> delete(@PathVariable("id") long id) {
        try {
            service.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Priority> search(@RequestBody PrioritySearchValues prioritySearchValues) {
        if (prioritySearchValues.getEmail() == null || prioritySearchValues.getEmail().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        service.find(prioritySearchValues.getName(), prioritySearchValues.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

