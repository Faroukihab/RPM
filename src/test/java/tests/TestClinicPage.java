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
import pages.ClinicPage;
public class TestClinicPage extends TestBase{


    LoginPage login;
    CSVReader reader;
    ClinicPage clinic;

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
    public void usercanCreateNewClinic() throws CsvValidationException, IOException
    {
    	String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));
        String[] csvCell = reader.readNext();
    	String clinicName = csvCell[7] ;
        clinic = new ClinicPage(driver);
    	clinic.gotoClinicPage();
    	clinic.CreateClinic(clinicName);
    	
    	 WebElement AddBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/clinics/create']"));

    	 assertTrue(AddBtn.isDisplayed(), "Success message is not displayed.");

    	
    }
    @Test
    public void usercanGotoEditPage()
    {
    	clinic = new ClinicPage(driver);
    	clinic.gotoClinicPage();
    	clinic.gotoClinicEditPage();
    	WebElement updateClinicButton = driver.findElement(By.xpath("//button[text()='Update Clinic']"));

		// Assert the element's presence and visibility
		assertTrue(updateClinicButton.isDisplayed(), "The 'Edit Category' header is not displayed on the page.");
    }
    @Test
    public void usercanGotoshowPage()
    {
       clinic = new ClinicPage(driver);
    	clinic.gotoClinicPage();
    	clinic.gotoClinicShowPage();
    	WebElement show = driver.findElement(By.xpath("//h3[text()='Clinic']"));

		// Assert the element's presence and visibility
		Assert.assertTrue(show.isDisplayed(), "The 'show Category' header is not displayed on the page.");
    }
    @Test
    public void usercantCreateClinicWithInvalidInpute()
    {
    	
    	 clinic = new ClinicPage(driver);
     	clinic.gotoClinicPage();
     	WebElement AddClinicBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/clinics/create']"));
     	AddClinicBtn.click();
     	WebElement saveClinicBtn = driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'btn btn-primary mt-3')]"));
      	saveClinicBtn.click();
     	WebElement form = driver.findElement(By.xpath("//form[@action='http://194.37.80.195:8888/clinics']"));
     	Assert.assertTrue(form.isDisplayed(), "No Error Message Appeared");
    }
    
}
