package com.codestates.todoapp.controller;

import com.codestates.todoapp.dto.PatchDto;
import com.codestates.todoapp.dto.PostDto;
import com.codestates.todoapp.entity.Todos;
import com.codestates.todoapp.mapper.TodoMapper;
import com.codestates.todoapp.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin(origins = "https://todobackend.com/client/index.html?http://localhost:8090/v1")
@RestController
@RequestMapping("/v1")
@Validated
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @PostMapping
    public ResponseEntity postToDo(@Valid @RequestBody PostDto postDto) {
        Todos todoMap = todoMapper.postDtoToTodo(postDto);
        Todos todos = todoService.createTodo(todoMap);

        return new ResponseEntity<>(todoMapper.todoToResponseDto(todos), HttpStatus.CREATED);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchToDo(@PathVariable("todo-id") @Positive long todoId,
                                     @Valid @RequestBody PatchDto patchDto) {
        patchDto.setTodoId(todoId);

        Todos todos = todoService.updateTodo(todoMapper.patchDtoToTodo(patchDto));

        return new ResponseEntity<>(todoMapper.todoToResponseDto(todos), HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getToDo(@PathVariable("todo-id") @Positive long todoId) {
        Todos todos = todoService.findTodo(todoId);
        return new ResponseEntity<>(todoMapper.todoToResponseDto(todos), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getToDos() {
        List<Todos> todosList = todoService.findTodos();
        return new ResponseEntity<>(todoMapper.todosToResponseDtos(todosList), HttpStatus.OK);
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity deleteToDo(@PathVariable("todo-id") @Positive long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteToDos() {
        todoService.deleteTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
