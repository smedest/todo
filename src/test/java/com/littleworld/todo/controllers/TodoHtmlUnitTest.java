package com.littleworld.todo.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring-context.xml" })
public class TodoHtmlUnitTest {

  @Autowired
  WebApplicationContext context;

  WebClient webClient;

  @Before
  public void setup() {
    webClient = MockMvcWebClientBuilder
      .webAppContextSetup(context)
      .contextPath("/spring-test")
      .build();
  }
  @Test
  public void testCreateTodo() throws Exception {

    HtmlPage todoFormPage = webClient.getPage("http://localhost/spring-test/todo");
    HtmlForm form = todoFormPage.getHtmlElementById("todoform");
    HtmlTextInput idInput = todoFormPage.getHtmlElementById("id");
    idInput.setValueAttribute("0");
    HtmlTextInput taskInput = todoFormPage.getHtmlElementById("task");
    taskInput.setValueAttribute("task0");

    HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
    HtmlPage newTodoPage = submit.click();
    
    assertThat(newTodoPage.getUrl().toString(), is("http://localhost/spring-test/todos"));
    assertThat(newTodoPage.getTitleText(), is("todo list"));
    assertTrue(newTodoPage.asText().contains("0"));
    assertTrue(newTodoPage.asText().contains("task0"));

  }

}

