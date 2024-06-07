package com.tylor.todos.JavaSpringMVC_Todos.controllers;

import com.tylor.todos.JavaSpringMVC_Todos.models.Todo;
import com.tylor.todos.JavaSpringMVC_Todos.repositories.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodosController {

    @Autowired
    private TodosRepository todosRepository;

    @GetMapping("/todos")
    public String getTodos(Model model) {
        List<Todo> todos = todosRepository.findAll();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @PostMapping("/todos/add")
    public String addTodo(@RequestParam String title, @RequestParam String description) {
        Todo newTodo = new Todo();
        newTodo.setTitle(title);
        newTodo.setDescription(description);
        newTodo.setCompleted(false);
        todosRepository.save(newTodo);
        return "redirect:/todos";
    }

    @PostMapping("/todos/delete")
    public String deleteTodo(@RequestParam Long id) {
        todosRepository.deleteById(id);
        return "redirect:/todos";
    }

    @PostMapping("/todos/update-completed")
    public String updateCompleted(@RequestBody TodoUpdateRequest request) {
        Long todoId = request.getId();
        boolean completed = request.isCompleted();

        // Retrieve the todo from the repository
        Todo todo = todosRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found with ID: " + todoId));

        // Update the completion status
        todo.setCompleted(completed);
        todosRepository.save(todo);

        return "Completed status updated successfully";
    }

    static class TodoUpdateRequest {
        private Long id;
        private boolean completed;

        // Getters and setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
