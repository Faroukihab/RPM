package pages;

import java.time.Duration;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class modelsPage extends pageBase{
	Locators locators;
	public modelsPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);

	}





	public void gotomodelpage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.devicesBtn);
		clickButton(locators.modelsBtn);
	}
	public void creatnewmodel(String modelName, String categotyName, String brandName ) throws InterruptedException
	{
		clickButton(locators.AddBtn);
		setTextElementText(locators.modelNameTxtfield, modelName);

		// Wait for the category dropdown to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(locators.categoryDropdownList));

		clickButton(locators.categoryDropdownList);
		locators.categoryDropdownList.sendKeys(Keys.ENTER);

		// Wait for the brand dropdown to be visible
	//	locators.brandDropdownList.clear();
		wait.until(ExpectedConditions.visibilityOf(locators.brandDropdownList));
		//locators.brandDropdownList.sendKeys(Keys.ENTER);
		clickButton(locators.brandDropdownList);
		locators.brandDropdownList.sendKeys(Keys.ARROW_DOWN);
		locators.brandDropdownList.sendKeys(Keys.ENTER);
		locators.brandDropdownList.sendKeys(Keys.ENTER);

		// Step 2: Click the 'Save' button
//		wait.until(ExpectedConditions.elementToBeClickable(locators.SaveBtn));
//		clickButton(locators.SaveBtn);

	 
	 
	}	
	public void selectbrand(String brandName) {
		WebElement optionList = driver.findElement(By.name("brand_id"));
		optionList.click();
		switch (brandName) {
		case "1":
			optionList.sendKeys(Keys.ARROW_DOWN);
			optionList.sendKeys(Keys.ENTER);
		}
	}
	public void gotoshowpage()
	{
		clickButton(locators.ShowBtn);
	}

	public void gotoeditpage()
	{
		clickButton(locators.EditBtn);
	}
}
