package core;

import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    private static WebDriver driver = null;

    @BeforeSuite()
    public void beforeSuite() {
        startDriver();

    }

    @BeforeSpec(tags = {"sampleTests"})
    public void anythingBeforeSpec() {
        // log in if required
    }

    @AfterSpec(tags = {"sampleTests"})
    public void anythingAfterSpec(){
        //log out if required
    }

    @AfterSuite
    public void afterSuite() {
        stopDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    private void startDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
            System.out.println("chrome path : "+System.getenv("CHROME_DRIVER"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
                System.out.println("Error starting driver : " + e.getMessage());
            }
        }
    }

    private void stopDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
