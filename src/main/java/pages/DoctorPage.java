package pages;

import java.util.UUID;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DoctorPage extends pageBase{
	Locators locators;
	public DoctorPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
	}


	public void AddNewDoctor(String doctorName,String doctormail, String PractionerID, String doctornumber )
	{
		clickButton(locators.AddBtn);
		locators.DoctornameField.sendKeys(doctorName);
		String uniqueMail = doctormail.split("@")[0] + UUID.randomUUID().toString().substring(0, 8) + "@" + doctormail.split("@")[1];
	    locators.DoctormailField.sendKeys(uniqueMail);
		locators.DoctorcontactField.sendKeys(doctornumber);
		locators.DoctorPracteniorID.sendKeys(PractionerID);
		locators.Clinicdropdown.click();
		locators.Clinicdropdown.sendKeys(Keys.ENTER);
		clickButton(locators.SaveBtn);
	}

	public void gotoDoctorEditPage()
	{
		clickButton(locators.EditBtn);
		
	}
	
	public void gotoDoctorVeiwPage()
	{
		clickButton(locators.ShowBtn);
	}
	
	public void gotoDoctorPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.clinicmanageBtn);
		clickButton( locators.DoctorBtn);
	}
}