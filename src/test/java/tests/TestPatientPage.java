package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


import pages.LoginPage;
import pages.PatientPage;

public class TestPatientPage extends TestBase {

	LoginPage login;
	CSVReader reader;
	PatientPage patient;

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
	public void validateusercancreatpatient() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();
		String name = csvCell[15] ;
		String height = csvCell[16] ;
		String number = csvCell[17] ;
		String secondnumber = csvCell[18] ;
		String insurance = csvCell[19] ;
		String address = csvCell[20] ;
		String policy = csvCell[21] ;
		patient = new PatientPage(driver);
		patient.gotoPatientpage();
		patient.AddnewPatient(name,height,number,secondnumber, insurance,address,policy);

		WebElement AddBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/patients/create' and contains(@class, 'btn-primary')]"));
		assertTrue(AddBtn.isDisplayed(), "Redirected to index page successfully");
	}

	@Test
	public void validateusercangotopatientEditPage() throws InterruptedException
	{ 
		patient = new PatientPage(driver);
		patient.gotoPatientpage();
		patient.gotoPatientEditPage();
		WebElement edit = driver.findElement(By.xpath("//h3[text()='Edit Patient Details']"));
		Assert.assertTrue(edit.isDisplayed(), "The 'Edit patient' header is not displayed on the page.");
	}

	@Test
	public void validateusercangotopatientShowPage() throws InterruptedException
	{
		patient = new PatientPage(driver);
		patient.gotoPatientpage();
		patient.gotoPatientShowPage();
		
		WebElement show = driver.findElement(By.xpath("//h3[text()='Patient Details']"));
		Assert.assertTrue(show.isDisplayed(), "The 'show patient' header is not displayed on the page.");
	}

	@Test
	public void validateusercangotoDevicepage() throws InterruptedException
	{
		patient = new PatientPage(driver);
		patient.gotoPatientpage();
		patient.usercangotoDevicesPage();
		
		WebElement show = driver.findElement(By.xpath("//h3[text()='Patient Devices']"));
		Assert.assertTrue(show.isDisplayed(), "The 'show patient' header is not displayed on the page.");
	}
	@Test 
	public void validateusercantcreatepatientwithinvaliddata()
	{
		patient = new PatientPage(driver);
	    patient.gotoPatientpage();
	    WebElement AddpatientBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/patients/create' and contains(@class, 'btn-primary')]"));
	    AddpatientBtn.click();
     	WebElement savepatienttBtn =  driver.findElement(By.id("height"));
      	savepatienttBtn.click();
     	WebElement form = driver.findElement(By.xpath("//div[@class='form-group mt-3 col-4']//input[@id='height']"));
     	Assert.assertTrue(form.isDisplayed(), "No Error Message Appeared");
	}

}
