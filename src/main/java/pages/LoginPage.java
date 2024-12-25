package pages;

import ProjectLocators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class LoginPage extends pageBase {

	Locators locators;
	public LoginPage(WebDriver driver) {
		super(driver);
	     locators = new Locators();
		 PageFactory.initElements(driver, locators);

	}


	
	public void Userlogin(String email, String password)
	{
		setTextElementText(locators.UserTxtBox, email);
		setTextElementText(locators.Password, password);
		clickButton(locators.SigninBtn);
	}
	
	public void logout()
	{
		clickButton(locators.avatar);
		clickButton(locators.logoutBtn);
	}
	
	public void loginwithDoctorAccount(String code,String docmail, String docPass)
	{
		setTextElementText(locators.UserTxtcode, code);
		setTextElementText(locators.UserTxtBox, docmail);
		setTextElementText(locators.Password, docPass);
		clickButton(locators.SigninBtn);
	}
}
