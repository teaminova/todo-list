package com.example.todolist.controller;

import com.example.todolist.entity.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public String listTodos(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @GetMapping("/create")
    public String createTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "create";
    }

    @PostMapping("/create")
    public String saveTodo(@ModelAttribute("todo") Todo todo) {
        todoService.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editTodoForm(@PathVariable Long id, Model model) {
        model.addAttribute("todo", todoService.findById(id).orElse(null));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute("todo") Todo todo) {
        todo.setId(id);
        todoService.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}
