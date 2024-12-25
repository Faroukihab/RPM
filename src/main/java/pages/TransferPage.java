package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ProjectLocators.Locators;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferPage extends pageBase {
      Locators locators;
	public TransferPage(WebDriver driver)
	{
		super(driver);
		locators = new Locators();
		PageFactory.initElements(driver, locators);
	}


	public void gotoTransferPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.stockBtn);
		clickButton(locators.transferBtn);
	}

	public void createOutboundTransfer(String category, String brand, String model, String clinic) throws InterruptedException
	{
		clickButton(locators.AddBtn);
		driver.navigate().refresh();
		clickButton(locators.choosmanualoption);
		clickButton(locators.categorydropdown);
		locators.categorydropdown.sendKeys(category);
		locators.categorydropdown.sendKeys(Keys.ENTER);

		clickButton(locators.branddropdown);
		locators.branddropdown.sendKeys(brand);
		locators.branddropdown.sendKeys(Keys.ENTER);

		Thread.sleep(2000);
		clickButton(locators.modeldropdown);
		Thread.sleep(1000);
		locators.modeldropdown.sendKeys(model);
		locators.modeldropdown.sendKeys(Keys.ENTER);

		clickButton(locators.clinicdropdown);
		locators.clinicdropdown.sendKeys(clinic);
		locators.clinicdropdown.sendKeys(Keys.ENTER);

		clickButton(locators.transferdate );
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = today.format(formatter);
		locators.transferdate.sendKeys(formattedDate);


//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		locators.serialfield = wait.until(ExpectedConditions.elementToBeClickable(By.id("serial-number-select-ts-control")));
        Thread.sleep(3000);
		clickButton(locators.serialfield);
		Thread.sleep(1000);
//		locators.serialfield.sendKeys(Keys.ARROW_DOWN);
		locators.serialfield.sendKeys(Keys.ENTER);

		scrollToBottom();
		Thread.sleep(1000);
		clickButton(locators.addBtn);
		Thread.sleep(1000);
		clickButton(locators.SaveBtn);
		Thread.sleep(1000);

	}

	public void createInboundTransfer(String category, String brand, String model, String clinic) throws InterruptedException {
		clickButton(locators.AddBtn);
		clickButton(locators.choosmanualoption);
		clickButton(locators.categorydropdown);
		locators.categorydropdown.sendKeys(category);
		locators.categorydropdown.sendKeys(Keys.ENTER);

		clickButton(locators.branddropdown);
		locators.branddropdown.sendKeys(brand);
		locators.branddropdown.sendKeys(Keys.ENTER);

		Thread.sleep(1000);
		clickButton(locators.modeldropdown);
		Thread.sleep(1000);
		locators.modeldropdown.sendKeys(model);
		locators.modeldropdown.sendKeys(Keys.ENTER);

		clickButton(locators.clinicdropdown);
		locators.clinicdropdown.sendKeys(clinic);
		locators.clinicdropdown.sendKeys(Keys.ENTER);
		locators.Transferdropdown.click();
		locators.Transferdropdown.sendKeys(Keys.ARROW_UP);
		locators.Transferdropdown.sendKeys(Keys.ENTER);
		clickButton(locators.transferdate);
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = today.format(formatter);
		locators.transferdate.sendKeys(formattedDate);

		Thread.sleep(3000);
		clickButton(locators.serialfield);
		Thread.sleep(1000);
		locators.serialfield.sendKeys(Keys.ENTER);

		// Add explicit wait to ensure the serial field is filled before clicking the add button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(locators.serialfield, "value"));

		scrollToBottom();
		Thread.sleep(1000);
		clickButton(locators.addBtn);
		Thread.sleep(1000);
		clickButton(locators.SaveBtn);
		Thread.sleep(1000);
	}
	public void PublishTransfer() throws InterruptedException
	{

		clickButton(locators.publishBtn);
		WebElement newstatus = driver.findElement(By.xpath("//span[contains(@class, 'badge') and contains(@class, 'badge-warning')]"));
		Assert.assertNotNull(newstatus);

	}
	

	
	public int GetLockedquantity()
	{
		
		WebElement lockedValue = driver.findElement(By.xpath("//th[text()='Locked']/following::tr[1]/td[count(//th[text()='Locked']/preceding-sibling::th) + 1]"));
		String transferNumberText = lockedValue.getText();
       return Integer.parseInt(transferNumberText);
	}
	public void gotoDevicesPage()
	{
		WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
		Actions actions = new Actions(driver);
		actions.moveToElement(menuList).perform();
		clickButton(locators.reportsBtn);
		clickButton(locators.devicemonitorgBtn);
	}
	public int GetClinicquantity()
	{
		
		WebElement clinicValue = driver.findElement(By.xpath("//th[text()='Clinic stock']/following::tr[1]/td[count(//th[text()='Clinic stock']/preceding-sibling::th) + 1]"));
     	String transferclinicNumberText = clinicValue.getText();
       return Integer.parseInt(transferclinicNumberText);
	}
	
	public int GetonTransferquantity()
	{
		WebElement ontransValue = driver.findElement(By.xpath("//th[text()='On transfer']/following::tr[1]/td[count(//th[text()='On transfer']/preceding-sibling::th) + 1]"));
		String Ontrans = ontransValue.getText();
       return Integer.parseInt(Ontrans);
	}
}
