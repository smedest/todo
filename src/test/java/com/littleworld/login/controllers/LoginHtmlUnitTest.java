package com.littleworld.login.controllers;

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
public class LoginHtmlUnitTest {

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

    HtmlPage loginFormPage = webClient.getPage("http://localhost/spring-test/login");
    HtmlForm form = loginFormPage.getHtmlElementById("loginform");
    HtmlTextInput idInput = loginFormPage.getHtmlElementById("id");
    idInput.setValueAttribute("0");
    HtmlTextInput nameInput = loginFormPage.getHtmlElementById("name");
    nameInput.setValueAttribute("name0");
    HtmlTextInput passwordInput = loginFormPage.getHtmlElementById("password");
    passwordInput.setValueAttribute("password0");

    HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
    HtmlPage newLoginPage = submit.click();
    
    assertThat(newLoginPage.getUrl().toString(), is("http://localhost/spring-test/logins"));
    assertThat(newLoginPage.getTitleText(), is("login list"));
    assertTrue(newLoginPage.asText().contains("0"));
    assertTrue(newLoginPage.asText().contains("name0"));
    assertTrue(newLoginPage.asText().contains("password0"));

  }

}

