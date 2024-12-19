package pages;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage  extends pageBase {
    Locators locators;
	public CategoryPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);

	}



	

	


	public void gotocategoryPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.devicesBtn);
		clickButton(locators.categoryBtn);
		
	}
	public void createnewCategoty(String categotryName)
	{
		clickButton(locators.AddBtn);
		setTextElementText(locators.categorynameTxtfield ,categotryName );
		clickButton(locators.SaveBtn);
	}
	public void gotoeditpage()
	{
		clickButton(locators.EditBtn);
     //   driver.navigate().back();
	}
	public void gotoshowpage()
	{
		clickButton(locators.ShowBtn);
		// driver.navigate().back();
	}
}

