package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoJpaResource {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
//        return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodoById(@PathVariable int id) {
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo) {
//        todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos/{id}")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        todo.setId(null);
        todo.setUsername(username);
        return todoRepository.save(todo);
//        return todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
    }
}


