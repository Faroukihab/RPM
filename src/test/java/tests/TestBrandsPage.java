package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.BrandsPage;
import pages.LoginPage;

public class TestBrandsPage extends TestBase {

    LoginPage login;
    CSVReader reader;
    BrandsPage brand;

    @BeforeMethod
    public void userLogin() throws CsvValidationException, IOException {
        String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));
        String[] csvCell = reader.readNext();
        String email = csvCell[0];
        String password = csvCell[1];

        login = new LoginPage(driver);
        login.Userlogin(email, password);
    }

   

    @Test
    public void userCanEnterEditPage() {
        brand = new BrandsPage(driver);
        brand.gotoBrandPage();
        brand.gotoeditpage();

        String actualEditURL = driver.getCurrentUrl();
        String expectedEditURL = "http://194.37.80.195:8888/brands/apple/edit";

        Assert.assertEquals(actualEditURL, expectedEditURL, "The current URL is not as expected.");
    }

    @Test
    public void userCanEnterShowPage() {
        brand = new BrandsPage(driver);
        brand.gotoBrandPage();
        brand.gotoshowpage();

        String actualShowURL = driver.getCurrentUrl();
        String expectedShowURL = "http://194.37.80.195:8888/brands/apple";

        Assert.assertEquals(actualShowURL, expectedShowURL, "The current URL is not as expected.");
 }
    @Test
    public void userCanCreateANewBrand() throws CsvValidationException, IOException {
        String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));
        String[] csvCell;

        while ((csvCell = reader.readNext()) != null) {
            String brandName = csvCell[3];
            brand = new BrandsPage(driver);
            brand.gotoBrandPage();
            brand.CreateNewBrand(brandName);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[@id='swal2-title' and text()='Brand created successfully.']")
            ));

            assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");
        }
    }
}
