package ru.iporsyak.first_spring_gradle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iporsyak.first_spring_gradle.entity.Stat;
import ru.iporsyak.first_spring_gradle.service.StatService;

@RestController
public class StatController {
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;

    }

    @PostMapping("/stat")
    public ResponseEntity<Stat> findByEmail(@RequestBody String email) {
        return ResponseEntity.ok(statService.findStat(email));
    }


}
