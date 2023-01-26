package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

public class AutomationClassTest {
    private static WebDriver chromeDriver;
    private static WebDriver edgeDriver;

    @BeforeAll
    public static void setUpDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setHeadless(true);
        WebDriverManager.edgedriver().setup();
        edgeDriver = new EdgeDriver(edgeOptions);
        edgeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() throws Exception {
        edgeDriver.quit();
        chromeDriver.quit();
    }

    @Test
    public void testSearchBoxChrome() {
        chromeDriver.get("http://automationpractice.pl/index.php");
        WebElement searchbox = chromeDriver.findElement(By.id("search_query_top"));
        searchbox.sendKeys("t-shirt");
        searchbox.submit();
        WebElement counter = chromeDriver.findElement(By.cssSelector("div.product-count"));
        assertNotNull(counter);
    }
    @Test
    public void testSearchBoxEdge() {
        edgeDriver.get("http://automationpractice.pl/index.php");
        WebElement searchbox = edgeDriver.findElement(By.id("search_query_top"));
        searchbox.sendKeys("t-shirt");
        searchbox.submit();
        WebElement counter = edgeDriver.findElement(By.cssSelector("div.product-count"));
        assertNotNull(counter);
    }

    @Test
    public void testGoToCartChrome() {
        chromeDriver.get("http://automationpractice.pl/index.php");
        WebElement cartButton = chromeDriver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        cartButton.click();
        assertEquals("http://automationpractice.pl/index.php?controller=order", chromeDriver.getCurrentUrl());
    }

    @Test
    public void testGoToCartEdge() {
        edgeDriver.get("http://automationpractice.pl/index.php");
        WebElement cartButton = edgeDriver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        cartButton.click();
        assertEquals("http://automationpractice.pl/index.php?controller=order", edgeDriver.getCurrentUrl());
    }

    @Test
    public void testMyOrdersRedirectsToLoginChrome() {
        chromeDriver.get("http://automationpractice.pl/index.php");
        WebElement myOrders = chromeDriver.findElement(By.xpath("//a[@title='My orders']"));
        myOrders.click();
        assertTrue(chromeDriver.getCurrentUrl().contains("authentication"));
    }

    @Test
    public void testMyOrdersRedirectsToLoginEdge() {
        edgeDriver.get("http://automationpractice.pl/index.php");
        WebElement myOrders = edgeDriver.findElement(By.xpath("//a[@title='My orders']"));
        myOrders.click();
        assertTrue(edgeDriver.getCurrentUrl().contains("authentication"));
    }
}
