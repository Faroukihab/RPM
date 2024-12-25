package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.lang.classfile.WritableElement;
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

import pages.CategoryPage;
import pages.LoginPage;

public class TestCategoryPage extends TestBase {

	LoginPage login;
	CSVReader reader;
	CategoryPage category;

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
	public void UserCanCreatnewCategory() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "\\src\\test\\java\\data\\data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {

			String categoryName = csvCell[2];

			category = new CategoryPage(driver);

			category.gotocategoryPage();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			category.createnewCategoty(categoryName);


			WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//h2[@id='swal2-title' and text()='Category saved successfully.']") 
					));

			// Assert that the success message is displayed
			assertTrue(successMessage.isDisplayed(), "Success message is not displayed."); 
		}
	}
	@Test
	public void gotoEditPage()
	{
		category = new CategoryPage(driver);
		category.gotocategoryPage();	
		category.gotoeditpage();

		WebElement edit = driver.findElement(By.xpath("//h3[text()='Edit Category']"));

		// Assert the element's presence and visibility
		Assert.assertTrue(edit.isDisplayed(), "The 'Edit Category' header is not displayed on the page.");
	}

	@Test
	public void gotoShowpage()
	{
		category = new CategoryPage(driver);
		category.gotocategoryPage();
		category.gotoshowpage();
		WebElement show = driver.findElement(By.xpath("//h3[text()='Category']"));

		// Assert the element's presence and visibility
		Assert.assertTrue(show.isDisplayed(), "The 'show Category' header is not displayed on the page.");
	}


}
