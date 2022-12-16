package com.codestates.todoapp.repository;

import com.codestates.todoapp.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todos, Long> {
    Optional<Todos> findByTitle(String title);
    Optional<Todos> findById(long todoId);
}
