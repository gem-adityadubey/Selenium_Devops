import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGoogle {

    private static final Logger logger = LoggerFactory.getLogger(TestGoogle.class);

    @Test
    public void testGoogleTitle() throws MalformedURLException {

        logger.info("Starting Google title test");
        System.out.println("=== Test Started: Google Title Test ===");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        logger.debug("Chrome options configured: {}", options);
        System.out.println("ChromeOptions configured");

        WebDriver driver = null;

        try {
            logger.info("Initializing RemoteWebDriver");
            System.out.println("Connecting to Selenium server at http://localhost:4444/wd/hub");

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    options
            );

            logger.info("RemoteWebDriver initialized successfully");
            System.out.println("RemoteWebDriver initialized");

            driver.get("https://www.google.com");
            logger.info("Navigated to Google homepage");
            System.out.println("Navigated to Google homepage");

            String title = driver.getTitle();
            logger.info("Page title fetched: {}", title);
            System.out.println("Page title: " + title);

            assertTrue(title.contains("Google"), "Title does not contain 'Google'");
            logger.info("Assertion passed: Title contains 'Google'");
            System.out.println("Assertion passed: Title contains 'Google'");

        } catch (Exception e) {
            logger.error("Test failed due to exception", e);
            System.out.println("Test failed due to exception: " + e.getMessage());
            throw e;
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully");
                System.out.println("Browser closed successfully");
            }
        }

        System.out.println("=== Test Finished: Google Title Test ===");
    }
}