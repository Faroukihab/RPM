package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.AssistantPage;
import pages.LoginPage;

public class TestAssistantPage extends TestBase{

	LoginPage login;
	CSVReader reader;
	AssistantPage assist;
    Locators locators;
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
	public void validateUserCanCreateNewAssistant() throws CsvValidationException, IOException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();
		String assistname = csvCell[12] ;
		String assistmail = csvCell[13] ;
		String assistcontact = csvCell[14] ;
		locators = new Locators();
		assist = new AssistantPage(driver);
		assist.gotoAssistantPage();
		assist.Addnewassistant(assistname, assistmail, assistcontact);
		WebElement AddBtn = driver.findElement(By.cssSelector("a[data-test_id='create_button']"));
		assertTrue(AddBtn.isDisplayed(), "Success message is not displayed.");
	}
	
	@Test
	public void validateusercanGotoAssistantEditPage()
	{
		assist = new AssistantPage(driver);
		assist.gotoAssistantPage();
		assist.gotoAssistantEditPage();
		WebElement edit = driver.findElement(By.xpath("//h3[text()='Edit Assistant']"));


		Assert.assertTrue(edit.isDisplayed(), "The 'Edit assistant' header is not displayed on the page.");

	}

	@Test
	public void validateusercanGotoShowPage()
	{
		assist = new AssistantPage(driver);
		assist.gotoAssistantPage();
		assist.gotoAssistantShowPage();
		WebElement show = driver.findElement(By.xpath("//h3[text()='Assistant']"));
		Assert.assertTrue(show.isDisplayed(), "The 'show assistant' header is not displayed on the page.");
	}

	@Test
	public void validateusercantAddAssistantWithInvalidData()
	{
		assist = new AssistantPage(driver);
		assist.gotoAssistantPage();
		WebElement AddassistantBtn = driver.findElement(By.cssSelector("a[data-test_id='create_button']"));
		AddassistantBtn.click();
     	locators.SaveBtn =  driver.findElement(By.xpath("//button[text()='Save' and contains(@class, 'btn-primary')]"));
		locators.SaveBtn.click();
     	WebElement form = driver.findElement(By.xpath("//form[@action='http://194.37.80.195:8888/assistants']"));
     	Assert.assertTrue(form.isDisplayed(), "No Error Message Appeared");
	}
}
