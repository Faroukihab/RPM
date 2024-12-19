package pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientPage extends pageBase{
	Locators locators;
	public PatientPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
	}


	public void gotoPatientpage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.clinicmanageBtn);
		clickButton( locators.patientBtn);
	}

	public void gotoPatientEditPage() throws InterruptedException
	{
		clickButton( locators.EditBtn);
		Thread.sleep(1000);
	}

	public void gotoPatientShowPage() throws InterruptedException
	{
		clickButton( locators.ShowBtn);
		Thread.sleep(1000);
	}

	public void AddnewPatient(String patientname, String height, String number, String secondnumber,String insurance, String address, 
			String policy) throws InterruptedException 
	{
		clickButton( locators.AddBtn);
		locators.patientnamefield.sendKeys(patientname);
		clickButton( locators.patientgender);
		locators.patientgender.sendKeys(Keys.ARROW_DOWN);
		locators.patientgender.sendKeys(Keys.ENTER);
		clickButton( locators.patientdob);
	
	//	LocalDate today = LocalDate.now();
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//	String formattedDate = today.format(formatter);
		String dob = "12/12/1990";
		locators.patientdob.sendKeys(dob);
		locators.patientheight.sendKeys(height);
		locators.patientnumber.sendKeys(number);
		locators.patientsecondarynuber.sendKeys(secondnumber);
		locators.patientinsurancenumberfield.sendKeys(insurance);
		locators.patientaddressfield.sendKeys(address);
		locators.policynumberfield.sendKeys(policy);
		locators.patientclinicdropdown.click();
		locators.patientclinicdropdown.sendKeys(Keys.ENTER);
		locators.patientclinicdropdown.sendKeys(Keys.ENTER);
		//Thread.sleep(1000);
		//clickButton( patientSaveBtn);
	}
	public void usercangotoDevicesPage() throws InterruptedException
	{
		clickButton(locators.patientDeviceBtn);
		Thread.sleep(1000);
	}

}
