package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ProjectLocators.Locators;
public class BrandsPage extends pageBase {


    Locators locators;
    // Constructor initializes elements
    public BrandsPage(WebDriver driver) {
        super(driver); // Call the parent constructor to initialize the driver
        locators = new Locators();
        PageFactory.initElements(driver, locators);
    }

    public void gotoBrandPage() {
        WebElement menuList = driver.findElement(By.cssSelector("ul.list-unstyled.menu-categories#accordionExample"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuList).perform();
        clickButton(locators.devicesBtn);
        clickButton(locators.brandsBtn);
    }

    public void CreateNewBrand(String brandName) {
        clickButton(locators.AddBtn);
        setTextElementText(locators.brandNameTxtfield, brandName);
        clickButton(locators.SaveBtn);
    }

    public void gotoshowpage()
    {
        locators.ShowBtn.click();

    }

    public void gotoeditpage() {

        locators.EditBtn.click();
    }
}
