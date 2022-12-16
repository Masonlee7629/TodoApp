package com.codestates.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    private long todoId;
    private String title;
    private long todo_order;
    private boolean completed;
}
