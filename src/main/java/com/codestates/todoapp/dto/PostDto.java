package com.codestates.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class PostDto {
    @NotBlank
    private String title;

    private long todo_order;

    private boolean completed;
}
