package com.littleworld.todo.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import com.littleworld.todo.model.Todo;
import com.littleworld.todo.services.TodoService;


public class TodoStandaloneControllerTest {

  private MockMvc mockMvc;

  @Mock
  private TodoService todoServiceMock;

  @InjectMocks
  TodoController todoController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(todoController)
        .setViewResolvers(viewResolver())
        .build();
  }

  private ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    //viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Test
  public void testTodos() throws Exception {
    List<Todo> todos = new ArrayList<Todo>();
    todos.add(new Todo(0, "task0"));
    todos.add(new Todo(1, "task1"));

    when( todoServiceMock.getAllTodos()).thenReturn(todos);
   
    mockMvc.perform(get("/todos"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(view().name("todoList"))
    .andExpect(model().attribute("todos",hasSize(2)))

    .andExpect(model().attribute("todos", hasItem(
      allOf( 
        hasProperty("id", is(0)),
        hasProperty("task", is("task0"))
    ))))
    .andExpect(model().attribute("todos", hasItem(
      allOf( 
        hasProperty("id", is(1)),
        hasProperty("task", is("task1"))
    ))));
  }

  @Test
  public void testTodoRest() throws Exception {
    List<Todo> todos = new ArrayList<Todo>();
    todos.add(new Todo(0, "task0"));
    todos.add(new Todo(1, "task1"));

    when( todoServiceMock.getAllTodos()).thenReturn(todos);

      mockMvc.perform(get("/listTodo"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
    
      .andExpect(jsonPath("$[0].id", is(0)))
      .andExpect(jsonPath("$[0].task", is("task0"))) 
      .andExpect(jsonPath("$[1].id", is(1)))
      .andExpect(jsonPath("$[1].task", is("task1"))) ;
    
    verify(todoServiceMock, times(1)).getAllTodos();
  }
}

 
