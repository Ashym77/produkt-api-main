package com.example.produktapi.service;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {

/*

    @Test
    void Checkiftitelmatches() {

        //hämta in webdriver

        WebDriver driver = new ChromeDriver();

        //navigerar till
        driver.get("https://www.svtplay.se/");

        //tesstars om titeln matchar
        assertEquals("SVT Play", driver.getTitle(), "Titels stämmer inte med förväntan");

        driver.quit();
    }

    @Test
    void checkingIfTitleMatches_UsingChromeDriver() {

        //hämta in webdriver

        WebDriver driver = new ChromeDriver();

        //navigerar till
        driver.get("https://www.sti.se/");

        //tesstars om titeln matchar
        assertEquals("STI - YH-program och vidareutbildningar inom teknik & IT"
                , driver.getTitle(), "Titels stämmer inte med förväntan");

        driver.quit();
    }


    @Test
    void testIfTitleMatches_WhenUsingMicrosoftEdgeDriver() {

        WebDriver driver = new EdgeDriver();

        driver.get("https://www.sti.se/");


        assertEquals("STI - YH-program och vidareutbildningar inom teknik & IT", driver.getTitle(),
                "Titels stämmer inte med förväntan");
        driver.quit();
    }

    @Test
    public void checkSvtText() {

        WebDriver driver = new EdgeDriver();

        driver.get("https://svtplay.se");

        WebElement textDemo = driver.findElement(By.xpath("//*[text()='Hårt sex']"));

        assertEquals("Hårt sex", textDemo.getText());

        driver.quit();
    }

    @Test
    void webaplititle() {


        WebDriver driver = new EdgeDriver();

        driver.get("https://java22.netlify.app//");


        assertEquals("Webbutik", driver.getTitle(),
                "Titels stämmer inte med förväntan");

        driver.quit();

    }


    @Test
    void checkh1text() {

        WebDriver driver = new EdgeDriver();

            driver.get("https://java22.netlify.app/");

        String h1text = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/h1")).getText();


        assertEquals("Testdriven utveckling - projekt", h1text, "Rubriken verkar inte stämma");

        driver.quit();
    }

    @Test
    void checkamountofproductshouldbe20InLIST() {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://java22.netlify.app/");


        List<WebElement> products = driver.findElements(By.className("productItem"));

        assertEquals(20, products.size(), "antalet produkter stämmer inte ");
        driver.quit();

    }


    @Test
    void CheckpriceofMBJWomensSolShort() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://java22.netlify.app/");



        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//p[contains(text(), 'Snygg och bara att sätta på sig om man har köpt rätt storlek')]")));

        // ta ut priset från texten
        String textOfPrice = priceElement.getText();
        String priceOfProduct = textOfPrice.replaceAll("[^\\d.]", "");
        //Vissa elements behöver man ta bort punkten efter d i regexen


        assertEquals("9.85", priceOfProduct, "priset match inte");

        driver.quit();
    }


    @Test
    void checkPriceofFjallravenFoldsack() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://java22.netlify.app/");



        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//p[contains(text(), 'Fin väska me plats för dator')]")));

        // ta ut priset från texten
        String textOfPrice = priceElement.getText();
        String priceOfProduct = textOfPrice.replaceAll("[^\\d.]", "");



        assertEquals("109.95", priceOfProduct, "priset match inte");

        driver.quit();
    }


    @Test
    void checkPriceOfPiercedOwlRoseGoldRing() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://java22.netlify.app/");



        WebElement priceElement = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//p[contains(text(), 'Något med en uggla, och i guld och lite dubbel stål')]")));

        // ta ut priset från texten
        String textOfPrice = priceElement.getText();
        String priceOfProduct = textOfPrice.replaceAll("[^\\d.]", "");



        assertEquals("10.99", priceOfProduct, "priset match inte");

        driver.quit();
    }
    */

}
