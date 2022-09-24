package ru.iporsyak.first_spring_gradle.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iporsyak.first_spring_gradle.entity.Task;
import ru.iporsyak.first_spring_gradle.search.TaskSearchValues;
import ru.iporsyak.first_spring_gradle.service.TaskService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    public static final String ID_COLUM = "id"; //имя столбца id
    private final String ALL = "/all";
    private final String ADD = "/add";
    private final String UPDATE = "/update";
    private final String SEARCH = "/search";
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(ALL)
    public ResponseEntity<List<Task>> findAll(@RequestBody String email) {
        return ResponseEntity.ok(taskService.findAll(email));
    }

    @PostMapping(ADD)
    public ResponseEntity<Task> add(@RequestBody Task task) {
        //проверка, id создается автоматически в БД
        if (task.getId() != 0) return new ResponseEntity<>(HttpStatus.CONFLICT);
        //проверка, если передоли пустое значение
        if (task.getTitle() == null || task.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        taskService.add(task);
        return ResponseEntity.ok(taskService.add(task));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Task> update(@RequestBody Task task) {
        //проверка, id создается автоматически в БД
        if (task.getId() != 0) return new ResponseEntity<>(HttpStatus.CONFLICT);
        //проверка, если передоли пустое значение
        if (task.getTitle() == null || task.getTitle().trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(taskService.update(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable("id") long id) {
        try {
            taskService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/id")
    public ResponseEntity<Task> findById(@RequestBody long id) {
        Task task = null;
        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException exc) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping(SEARCH)
    // поиск по любым параметрам TaskSearchValues
    public ResponseEntity<Page<Task>> search(TaskSearchValues taskSearchValues) {

        String title = taskSearchValues.getTitle() != null ?
                taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted() != null ?
                taskSearchValues.getCompleted() : null;
        Long priorityId = taskSearchValues.getPriorityId() != null ?
                taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ?
                taskSearchValues.getCategoryId() : null;
        String sortColum = taskSearchValues.getSortColum() != null ?
                taskSearchValues.getSortColum() : null;
        String sortDirection = taskSearchValues.getSortDirection() != null ?
                taskSearchValues.getSortDirection() : null;
        Integer pageNumber = taskSearchValues.getSortNumber() != null ?
                taskSearchValues.getSortNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ?
                taskSearchValues.getPageSize() : null;
        String email = taskSearchValues.getEmail() != null ?
                taskSearchValues.getEmail() : null;

        // проверка на обязательные параметры
        if (email == null || email.trim().length() == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        /*
         что бы захватить все задачи по дате, не зависимо от времени
         можно выставить время с 00:00 - 23:59
         */
        Date dateFrom = null;
        Date dateTo = null;
        /*
        TODO ЭТО СКОРЕЕ ВСЕГО НЕ ПРАВИЛЬНО.
         ТАК СО ВРЕМЕНЕМ РАБОТАТЬ НЕЛЬЗЯ
        */
        // выставить 00:00 с начало даты(если она указанна)
        if (taskSearchValues.getDateFrom() != null) {
            Calendar calendarFrom = Calendar.getInstance();
            calendarFrom.setTime(taskSearchValues.getDateFrom());
            calendarFrom.set(Calendar.HOUR_OF_DAY, 0);
            calendarFrom.set(Calendar.MINUTE, 0);
            calendarFrom.set(Calendar.SECOND, 0);
            calendarFrom.set(Calendar.MILLISECOND, 0);
            dateFrom = calendarFrom.getTime();
        }
        // выставить 23:59 для конечной даты(если она указанна)
        if (taskSearchValues.getDateTo() != null) {
            Calendar calendarTo = Calendar.getInstance();
            calendarTo.setTime(taskSearchValues.getDateFrom());
            calendarTo.set(Calendar.HOUR_OF_DAY, 23);
            calendarTo.set(Calendar.MINUTE, 59);
            calendarTo.set(Calendar.SECOND, 59);
            calendarTo.set(Calendar.MILLISECOND, 999);
            dateTo = calendarTo.getTime();
        }

        // направление сортировки
        Sort.Direction direction = sortDirection == null
                || sortDirection.trim().length() == 0
                || sortDirection.trim().equals("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;

        // обьект сортировки, который содержит столбец и напрасление
        Sort sort = Sort.by(direction, sortColum, ID_COLUM);
        // обьект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        // результат запроса с постраничным выводом
        Page<Task> result = taskService.findByParams(
                title, completed, priorityId, categoryId, email, dateFrom, dateTo, pageRequest);
        return ResponseEntity.ok(result);
    }


}
