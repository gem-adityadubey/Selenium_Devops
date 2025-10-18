import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGoogle {

    @Test
    public void testGoogleTitle() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");   // Run in background
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        assertTrue(title.contains("Google"));
        driver.quit();
    }
}
