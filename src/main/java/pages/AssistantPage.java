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

public class AssistantPage extends pageBase{
	Locators locators;
	public AssistantPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
		
	}



	public void gotoAssistantPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.clinicmanageBtn);
		clickButton( locators.AssistantBtn);
	}
	
	public void Addnewassistant(String assistantName, String assistantmail, String assistantcontact )
	{
		clickButton( locators.AddBtn);
		locators.AssistantName.sendKeys(assistantName);
		String uniqueMail = assistantmail.split("@")[0] + UUID.randomUUID().toString().substring(0, 8) + "@" + assistantmail.split("@")[1];
		locators.Assistantmail.sendKeys(uniqueMail);
		locators.Assistantcontact.sendKeys(assistantcontact);
	    clickButton( locators.AssistantClinic);
		locators.AssistantClinic.sendKeys(Keys.ENTER);
		clickButton( locators.SaveBtn);
		
		
	}
	
	public void gotoAssistantShowPage()
	{

		clickButton( locators.ShowBtn);
	}
	
	public void gotoAssistantEditPage()
	{

		clickButton( locators.EditBtn);
	}
}
