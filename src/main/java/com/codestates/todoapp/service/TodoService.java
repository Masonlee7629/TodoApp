package com.codestates.todoapp.service;

import com.codestates.todoapp.entity.Todos;
import com.codestates.todoapp.exception.BusinessLogicException;
import com.codestates.todoapp.exception.ExceptionCode;
import com.codestates.todoapp.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todos createTodo(Todos todos) {
        verifyExistsTitle(todos.getTitle());
        return todoRepository.save(todos);
    }

    public Todos updateTodo(Todos todos) {
        Todos findTodos = findVerifiedTodo(todos.getTodoId());

        Optional.ofNullable(todos.getTitle())
                .ifPresent(title -> findTodos.setTitle(title));
        Optional.ofNullable(todos.isCompleted())
                .ifPresent(completed -> findTodos.setCompleted(completed));
        return todoRepository.save(findTodos);
    }

    public Todos findTodo(long todoId) {
        return findVerifiedTodo(todoId);
    }

    public List<Todos> findTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(long todoId) {
        Todos findTodos = findVerifiedTodo(todoId);
        todoRepository.delete(findTodos);
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    public void verifyExistsTitle(String title) {
        Optional<Todos> todos = todoRepository.findByTitle(title);
        if(todos.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
        }
    }

    public Todos findVerifiedTodo(long todoId) {
        Optional<Todos> optionalTodos = todoRepository.findById(todoId);
        Todos findTodos = optionalTodos.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));

        return findTodos;
    }
}
