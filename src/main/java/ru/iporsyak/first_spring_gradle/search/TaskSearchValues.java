package ru.iporsyak.first_spring_gradle.search;

import lombok. *;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskSearchValues {
    // поля для поиска, все типы обьекты, что бы можно было бы пердать null
    private String title;
    private Integer completed;
    private Long categoryId;
    private Long priorityId;
    private String email;
    private Date dateFrom; // для задания периода, тем самым будут находится задачи попадающие под период
    private Date dateTo;

    // по страничность
    private Integer sortNumber;
    private Integer pageSize;

    // сортировка
    private String sortColum;
    private String sortDirection;

    // такие же названия должны быть у обьекта на frontend
}
