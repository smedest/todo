
package com.littleworld.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.littleworld.todo.model.Todo;
import com.littleworld.todo.services.TodoService;


@Controller
public class TodoController {

    @Autowired  private TodoService todoService;

    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoForm";
    }

    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public String todoSubmit(Todo todo) {
        todoService.createTodo(todo);
        return "redirect:todos";
    }

   @RequestMapping(value="/todo/{index}", method=RequestMethod.GET)
    public String todoFindById(@PathVariable int index, Model model) {
        Todo todo = todoService.getTodoById(index) ;
        model.addAttribute("todo", todo);
        return "todoView";
    }

    @RequestMapping(value="/todos", method=RequestMethod.GET)
    public String todoGetAll(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todoList";
    }

    //curl  http://localhost:8080/todo/list
    @ResponseBody
    @RequestMapping(value = "/listTodo", method = RequestMethod.GET)
    public List<Todo> findAllTodos() {
      return todoService.getAllTodos();
    }
}

