package pages;

import ProjectLocators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class serialPage extends pageBase {
	Locators locators;

	public serialPage(WebDriver driver) {
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
		
	}

	public static class FakeDataGenerator {

		// Method to generate a random number with a specified number of digits
		public static String generateRandomNumber(int length) {
			Random random = new Random();
			StringBuilder number = new StringBuilder();

			// Ensure the first digit is not zero
			number.append(random.nextInt(9) + 1);

			for (int i = 1; i < length; i++) {
				number.append(random.nextInt(10));
			}

			return number.toString();
		}
	}
	public class FakenumbersGenerator {

		// Method to generate a unique number based on the current timestamp
		public  String generateUniqueNumber() {
			return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}
	}

	public void enterSerialNumber() throws InterruptedException {
		// Call the fake data generator method here
		String fakeNumber = null;
		try {
			fakeNumber = FakeDataGenerator.generateRandomNumber(8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  // Generates an 8-digit number
		locators.serialnumberField.click();
		locators.serialnumberField.sendKeys(fakeNumber);
		clickButton(locators.addBtn);
		Thread.sleep(2000);
		scrollToBottom();
		Thread.sleep(1000);
		clickButton(locators.SaveBtn);
	}
	public void gotoSerialPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.devicesBtn);
		clickButton(locators.serialBtn);
	}

	public void CreatnewDevice() throws InterruptedException
	{
		clickButton(locators.AddBtn);
		clickButton(locators.categoryField);
		locators.categoryField.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		clickButton(locators.brandField);
		Thread.sleep(1000);
		locators.brandField.sendKeys(Keys.ARROW_DOWN);
		locators.brandField.sendKeys(Keys.ARROW_DOWN);
		locators.brandField.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		locators.modelField.sendKeys(Keys.ARROW_DOWN);
		//   modelField.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		clickButton(locators.modelField);
		locators.modelField.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}
}
