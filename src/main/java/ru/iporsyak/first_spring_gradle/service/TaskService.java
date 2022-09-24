package ru.iporsyak.first_spring_gradle.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iporsyak.first_spring_gradle.entity.Task;
import ru.iporsyak.first_spring_gradle.repo.TaskRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(String email) {
        return taskRepository.findByUserEmailOrderByTitleAsc(email);
    }

    public Task add(Task task) { // метод save создает новый обьект
        // если его не было или обновляет существующий.
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(long id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> findByParams(String text, Integer completed, Long priorityId,
                                   Long categoryId, String email, Date deleteFrom,
                                   Date deleteTo, PageRequest pageRequest) {
        return taskRepository.
                findByParams(text, completed, priorityId, categoryId, email,
                deleteFrom, deleteTo, pageRequest);

    }

    public Task findById(long id) {
        return taskRepository.findById(id).orElseThrow();
    }


}
