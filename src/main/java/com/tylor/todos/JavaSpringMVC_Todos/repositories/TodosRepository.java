package com.tylor.todos.JavaSpringMVC_Todos.repositories;


import com.tylor.todos.JavaSpringMVC_Todos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodosRepository extends JpaRepository<Todo, Long> {
}