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

import pages.DoctorPage;
import pages.LoginPage;

public class TestDoctorPage extends TestBase {

	LoginPage login;
    CSVReader reader;
    DoctorPage doctor;

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
	public void validatetheusercsngotoDoctorshowPage()
	{
		doctor = new DoctorPage(driver);
		doctor.gotoDoctorPage();
		doctor.gotoDoctorVeiwPage();
		WebElement show = driver.findElement(By.xpath("//h3[text()='Doctor']"));

		Assert.assertTrue(show.isDisplayed(), "The 'show doctor' header is not displayed on the page.");
	}
	
	@Test
	public void vlidatetheUsercangotoDoctorEditPage()
	{
		doctor = new DoctorPage(driver);
		doctor.gotoDoctorPage();
		doctor.gotoDoctorEditPage();
		WebElement edit = driver.findElement(By.xpath("//h3[text()='Edit Doctor']"));

		
		Assert.assertTrue(edit.isDisplayed(), "The 'Edit doctor' header is not displayed on the page.");
		
	}
	
	@Test
	public void validatetheUserCanCreateDoctor() throws CsvValidationException, IOException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
        reader = new CSVReader(new FileReader(csvFile));
        String[] csvCell = reader.readNext();
        String doctorName = csvCell[8] ;
        String doctormail = csvCell[9] ;
        String PractionerID = csvCell[10] ;
        String doctornumber = csvCell[11] ;
        
		doctor = new DoctorPage(driver);
		doctor.gotoDoctorPage();
		doctor.AddNewDoctor(doctorName,doctormail, PractionerID,  doctornumber);
		
		WebElement AddBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/doctors/create' and contains(@class, 'btn btn-primary')]"));

    	 assertTrue(AddBtn.isDisplayed(), "Success message is not displayed.");
	}

	@Test
	public void validatetheusercantCreateDoctorWithInavaidData()
	{
		doctor = new DoctorPage(driver);
		doctor.gotoDoctorPage();
		WebElement AdddoctorBtn = driver.findElement(By.xpath("//a[@href='http://194.37.80.195:8888/doctors/create' and contains(@class, 'btn btn-primary')]"));
     	AdddoctorBtn.click();
     	WebElement savedoctorBtn =  driver.findElement(By.xpath("//button[contains(@class, 'mr-2') and contains(@class, 'btn-primary') and contains(@class, 'mixin')]"));
      	savedoctorBtn.click();
     	WebElement form = driver.findElement(By.xpath("//form[@action='http://194.37.80.195:8888/doctors']"));
     	Assert.assertTrue(form.isDisplayed(), "No Error Message Appeared");
	}
}
