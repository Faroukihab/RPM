package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.helper;

public class TestBase {

    public static WebDriver driver;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";

    @SuppressWarnings("deprecation")
    @BeforeMethod(alwaysRun = true)
    @Parameters({ "browser" })
    public void startDriver(@Optional("edge") String browserName) {
        System.out.println("Initializing WebDriver for browser: " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
            // Initialize Firefox driver here if needed
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  // Consider using a shorter wait time
        driver.navigate().to("http://194.37.80.195:8888/");
    }

    @AfterMethod
    public void stopDriver(ITestResult result) {
        // Capture screenshot if the test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test case failed!");
            System.out.println("Taking a screenshot....");
            helper.captureScreenshot(driver, result.getName());
        }

        // Ensure driver is quit after each test
        if (driver != null) {
            driver.quit();
            driver = null;  // Avoid reuse in subsequent tests
        }
    }
}
