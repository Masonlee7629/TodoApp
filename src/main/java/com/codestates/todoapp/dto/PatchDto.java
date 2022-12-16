package com.codestates.todoapp.dto;

import lombok.Getter;

@Getter
public class PatchDto {
    private long todoId;
    private String title;
    private boolean completed;

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }
}
