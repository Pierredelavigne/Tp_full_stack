package org.example.thymeleaf.controller;

import org.example.thymeleaf.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TodoController {

    private List<Todo> todos = new ArrayList<>(List.of(
        new Todo(1, "Suivre le top cours", false),
        new Todo(3, "Faire du sport", false),
        new Todo(4, "Rester concentrer", false),
        new Todo(5, "Dormir", true)
    ));

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todos);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute Todo todo) {
        todo.setId(generateId());
        todos.add(todo);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Todo todo = findTodoById(id);
        model.addAttribute("todo", todo);
        return "update";
    }

    @PostMapping("/update")
    public String updateTodo(@ModelAttribute Todo updatedTodo) {
        Todo todo = findTodoById(updatedTodo.getId());
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todo.setStatus(updatedTodo.isStatus());
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable int id) {
        todos.removeIf(t -> t.getId() == id);
        return "redirect:/";
    }

    private Todo findTodoById(int id) {
        return todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    private int generateId() {
        return todos.stream().mapToInt(Todo::getId).max().orElse(0) + 1;
    }
}
