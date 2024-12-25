package pages;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ClinicPage extends pageBase{
	Locators locators;
	public ClinicPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
	}

	public void gotoClinicPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.clinicmanageBtn);
		clickButton(locators.clinicBtn);
	}
	public void gotoClinicEditPage()
	{
		clickButton(locators.EditBtn);
	}
	public void gotoClinicShowPage()
	{
		clickButton(locators.ShowBtn);
	}
	public void CreateClinic(String ClinicName )
	{
		clickButton(locators.AddBtn);
		locators.clinicNameField.sendKeys(ClinicName);
		clickButton(locators.SaveBtn);
	}
}
