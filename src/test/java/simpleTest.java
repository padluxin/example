import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class simpleTest {
    private static WebDriver driver;
    private static String headless = System.getProperty("env.HEADLESS", "false");


    @BeforeTest
    public void beforeTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless.equalsIgnoreCase("true")) {
            options.addArguments("--start-fullscreen");
        } else {
            options.addArguments("--headless", "--window-size=1920,1200");
        }
        driver = new ChromeDriver(options);
        Thread.sleep(1000);
    }

    @Test
    public void googleTest() {
        driver.get("https://google.com");
        driver.getTitle().equalsIgnoreCase("Google");
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//*[text() = 'Selenium (software) - Wikipedia']")).isDisplayed();
    }

    @AfterTest
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
