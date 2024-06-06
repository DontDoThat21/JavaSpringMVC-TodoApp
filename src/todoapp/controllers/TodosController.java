package todoapp.controllers;

import todoapp.models.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TodosController {

    @GetMapping("/todos")
    public String getTodos(Model model) {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo(1L, "Buy groceries", "Milk, Bread, Butter, Chicken Dinners!", false));
        todos.add(new Todo(2L, "Study Spring MVC", "Read the documentation and tutorials", false));
        todos.add(new Todo(3L, "Exercise", "Go for a bike ride.", true));
        todos.add(new Todo(4L, "Pray", "Praise Tate Classic W. WARNING: Incredibly stupid brain rot and NSFW. https://www.youtube.com/watch?v=VhgLyvP0Y5A", true));

        model.addAttribute("todos", todos);
        return "todos";
    }
}
