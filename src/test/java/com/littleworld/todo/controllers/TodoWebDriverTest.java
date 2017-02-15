package com.littleworld.todo.controllers;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring-context.xml" })
public class TodoWebDriverTest {

  private WebDriver driver;

  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/Users/littleworld/Downloads/chromedriver");
    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.close();
  }

   @Test
  public void Todo() throws Exception {
    driver.get("http://localhost:8080/todo/todo");      
    driver.findElement(By.id("id")).sendKeys("0");
    driver.findElement(By.id("task")).sendKeys("task0");
    driver.findElement(By.className("button")).submit();
   
    // after submit; on the next page
    assertEquals(driver.getCurrentUrl(), "http://localhost:8080/todo/todos");   
    assertEquals(driver.getTitle(), "todo list");
    assertTrue(driver.findElement(By.xpath("//tbody//td[contains(text(), '0')]")).isDisplayed());
    assertTrue(driver.findElement(By.xpath("//tbody//td[contains(text(), 'task')]")).isDisplayed());

  } 
}

