package ru.iporsyak.first_spring_gradle.search;

import lombok. *;

 //возможные значения по которым можно искать категории
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategorySearchValues {
    private String title; // накоеже название как и на backend
    private String email;
}