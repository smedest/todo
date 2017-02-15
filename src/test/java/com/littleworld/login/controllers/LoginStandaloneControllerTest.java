package com.littleworld.login.controllers;
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


import com.littleworld.login.model.Login;
import com.littleworld.login.services.LoginService;


public class LoginStandaloneControllerTest {

  private MockMvc mockMvc;

  @Mock
  private LoginService loginServiceMock;

  @InjectMocks
  LoginController loginController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(loginController)
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
  public void testLogins() throws Exception {
    List<Login> logins = new ArrayList<Login>();
    logins.add(new Login(0, "name0", "password0"));
 
    when( loginServiceMock.getAllLogins()).thenReturn(logins);
   
    mockMvc.perform(get("/logins"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(view().name("loginList"))
    .andExpect(model().attribute("logins",hasSize(1)))

    .andExpect(model().attribute("logins", hasItem(
      allOf( 
        hasProperty("id", is(0)),
        hasProperty("name", is("name0")),
        hasProperty("password", is("password0"))
    ))));
   
  }

  @Test
  public void testLoginRest() throws Exception {
 
   
  }
}

 
