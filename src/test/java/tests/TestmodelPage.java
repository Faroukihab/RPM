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

import pages.LoginPage;
import pages.modelsPage;

public class TestmodelPage extends TestBase{
	LoginPage login;
	CSVReader reader;
	modelsPage models ;

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
	public void usercancreatenewmodel( ) throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {
			String modelName = csvCell[4];
			String categoryName = csvCell[5];
			String brandName = csvCell[6];
			models = new modelsPage(driver);
			models.gotomodelpage();
			models.creatnewmodel(modelName, categoryName, brandName);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//h2[@id='swal2-title' and text()='Device Model saved successfully.']")
					));

			// Assert that the success message is displayed
//			assertTrue(successMessage.isDisplayed(), "Success message is not displayed.");
			
		}
	}

	@Test
	public void usercangotoEditpage()
	{
		models = new modelsPage(driver);
		models.gotomodelpage();
		models.gotoeditpage();
		WebElement edit = driver.findElement(By.xpath("//h3[text()='Edit Device Model']"));

		// Assert the element's presence and visibility
		Assert.assertTrue(edit.isDisplayed(), "The 'Edit Model' header is not displayed on the page.");
	}

	@Test
	public void usercangotoShowpage()
	{
		models = new modelsPage(driver);
		models.gotomodelpage();	
		models.gotoshowpage();
		WebElement show = driver.findElement(By.xpath("//h3[text()='Device Model']"));

		// Assert the element's presence and visibility
		Assert.assertTrue(show.isDisplayed(), "The 'show model' header is not displayed on the page.");

	}
}