package tests;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.LoginPage;
import pages.TransferPage;

public class TestTransferPage extends TestBase {

	LoginPage login;
	CSVReader reader;
	TransferPage transfer;

	@BeforeMethod
	public void userLogin() throws CsvValidationException, IOException {
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();
		String email = csvCell[0];
		String password = csvCell[1];

		login = new LoginPage(driver);
		login.Userlogin(email, password);
	}




	@Test
	public void validatetheUsercanRejectOBT() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();

		String cat = csvCell[22];
		String brand = csvCell[23];
		String model =csvCell[24];
		String clinic = csvCell[25];
		String code = csvCell[26];
		String docmail = csvCell[27];
		String docpass = csvCell[28];
		transfer = new TransferPage(driver);
		transfer.gotoDevicesPage();
		int transferNumberBefore = transfer.GetLockedquantity();
		System.out.println("locked transfer number before: " + transferNumberBefore);
		transfer.gotoTransferPage();
		transfer.createOutboundTransfer(cat, brand, model, clinic);
		WebElement AddBtn = driver.findElement(By.xpath("//a[text()='Add Transfers']"));
		assertTrue(AddBtn.isDisplayed(), "Redirected to index page successfully");
		transfer.PublishTransfer();
		transfer.gotoDevicesPage();
		int transferNumberAfter = transfer.GetLockedquantity();
		System.out.println("locked transfer number after: " + transferNumberAfter);


		if (transferNumberAfter == transferNumberBefore + 1) {
			System.out.println("Validation passed: Locked count incremented by 1.");
		} else {
			System.out.println("Validation failed: Locked count did not increment correctly.");
		}
		login = new LoginPage(driver);
		login.logout();
		login.loginwithDoctorAccount(code,docmail, docpass);
		Thread.sleep(1000);
		transfer.gotoDevicesPage();
		Thread.sleep(2000);
		int numberofclinicstockbefore= transfer.GetClinicquantity();
		System.out.println("clinic stock number before rejection: " + numberofclinicstockbefore);
		transfer.gotoTransferPage();
		WebElement showButton = driver.findElement(By.xpath("//a[contains(@href, '/transfers/') and text()='Show']"));
		showButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000)");

		WebElement rejectButton = driver.findElement(By.xpath("//button[text()='Reject']"));
		rejectButton.click();
		Thread.sleep(1000);
		WebElement StatusRejected = driver.findElement(By.xpath("//span[contains(@class, 'badge-dark') and normalize-space(text())='Rejected']"));
		Assert.assertNotNull(StatusRejected);
		transfer.gotoDevicesPage();
		int  numberofclinicstockafter =  transfer.GetClinicquantity();
		System.out.println("clinic stock number after rejection: " + numberofclinicstockafter);

		if ( numberofclinicstockafter ==  numberofclinicstockbefore ) {
			System.out.println("Validation passed: Clinic stock quantity doesn't affected by rejection.");
		} else {
			System.out.println("Validation failed:  Clinic stock quantity affected");
		}
	}
	@Test
	public void validateUsercanAccepttheOBT() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();

		String cat = csvCell[22];
		String brand = csvCell[23];
		String model =csvCell[24];
		String clinic = csvCell[25];
		String code = csvCell[26];
		String docmail = csvCell[27];
		String docpass = csvCell[28];
		transfer = new TransferPage(driver);
		transfer.gotoDevicesPage();
		int transferNumberBefore = transfer.GetLockedquantity();
		System.out.println("locked transfer number before: " + transferNumberBefore);
		transfer.gotoTransferPage();
		transfer.createOutboundTransfer(cat, brand, model, clinic);
		WebElement AddBtn = driver.findElement(By.xpath("//a[text()='Add Transfers']"));
		assertTrue(AddBtn.isDisplayed(), "Redirected to index page successfully");
		transfer.PublishTransfer();
		transfer.gotoDevicesPage();
		int transferNumberAfter = transfer.GetLockedquantity();
		System.out.println("locked transfer number after: " + transferNumberAfter);

		// Validate that the transfer count has increased by 1
		if (transferNumberAfter == transferNumberBefore + 1) {
			System.out.println("Validation passed: Transfer count incremented by 1.");
		} else {
			System.out.println("Validation failed: Transfer count did not increment correctly.");
		}
		login = new LoginPage(driver);
		login.logout();
		login.loginwithDoctorAccount(code,docmail, docpass);
		transfer.gotoDevicesPage();
		int numberofclinicstockbefore= transfer.GetClinicquantity();
		System.out.println("clinic stock number before Accept Transfer: " + numberofclinicstockbefore);
		transfer.gotoTransferPage();
		WebElement showButton = driver.findElement(By.xpath("//a[contains(@href, '/transfers/') and text()='Show']"));
		showButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000)");
		WebElement acceptButton = driver.findElement(By.xpath("//button[text()='Accept']"));
		acceptButton.click();
		Thread.sleep(1000);
		WebElement StatusApproved = driver.findElement(By.xpath("//span[contains(@class, 'badge') and contains(@class, 'badge-success') and normalize-space()='Approved']"));
		Assert.assertNotNull(StatusApproved);
		transfer.gotoDevicesPage();
		int  numberofclinicstockafter =  transfer.GetClinicquantity();
		System.out.println("clinic stock number after Accept Transfer: " + numberofclinicstockafter);
		// Validate that the transfer count has increased by 1
		if ( numberofclinicstockafter ==  numberofclinicstockbefore + 1) {
			System.out.println("Validation passed: Clinic count incremented by 1.");
		} else {
			System.out.println("Validation failed: Transfer count did not increment correctly.");
		}
	}



	@Test
	public void validateUsercanEditOBT() throws InterruptedException, CsvValidationException, IOException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();

		String cat = csvCell[22];
		String brand = csvCell[23];
		String model =csvCell[24];
		String clinic = csvCell[25];
		String code = csvCell[26];
		String docmail = csvCell[27];
		String docpass = csvCell[28];
		transfer = new TransferPage(driver);
		transfer.gotoDevicesPage();
		int transferNumberBefore = transfer.GetLockedquantity();
		System.out.println("locked transfer number before: " + transferNumberBefore);
		transfer.gotoTransferPage();
		transfer.createOutboundTransfer(cat, brand, model, clinic);
		WebElement AddBtn = driver.findElement(By.xpath("//a[text()='Add Transfers']"));
		assertTrue(AddBtn.isDisplayed(), "Redirected to index page successfully");
		transfer.PublishTransfer();
		transfer.gotoDevicesPage();
		int transferNumberAfter = transfer.GetLockedquantity();
		System.out.println("locked transfer number after: " + transferNumberAfter);


		if (transferNumberAfter == transferNumberBefore + 1) {
			System.out.println("Validation passed: Locked count incremented by 1.");
		} else {
			System.out.println("Validation failed: Locked count did not increment correctly.");
		}
		login = new LoginPage(driver);
		login.logout();
		login.loginwithDoctorAccount(code,docmail, docpass);
         Thread.sleep(1000);
		transfer.gotoDevicesPage();
		Thread.sleep(2000);
		int numberofclinicstockbefore= transfer.GetClinicquantity();
		System.out.println("clinic stock number before Edit: " + numberofclinicstockbefore);
		transfer.gotoTransferPage();
		WebElement showButton = driver.findElement(By.xpath("//a[contains(@href, '/transfers/') and text()='Show']"));
		showButton.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 1000)");

		WebElement editButton = driver.findElement(By.xpath("//button[@type='button' and contains(@class, 'btn-warning') and @data-bs-target='#requestEditModal']"));
		editButton.click();
		Thread.sleep(1000);
		WebElement editText = driver.findElement(By.id("edit_comment"));
		editText.sendKeys("not needed right now");
		WebElement submitteBtn = driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'btn-warning')]"));
		submitteBtn.click();
		WebElement StatusRequestEdit = driver.findElement(By.xpath("//span[contains(@class, 'badge-))info') and normalize-space(text())='Request edit']"));
		Assert.assertNotNull(StatusRequestEdit);
		
		WebElement pendingBtn = driver.findElement(By.xpath("//button[text()='Publish']"));
		Assert.assertNotNull(pendingBtn);
		
		transfer.gotoDevicesPage();
		int  numberofclinicstockafter =  transfer.GetClinicquantity();
		System.out.println("clinic stock number after Request Edit: " + numberofclinicstockafter);

		if ( numberofclinicstockafter ==  numberofclinicstockbefore ) {
			System.out.println("Validation passed: Clinic stock quantity doesn't affected by R.E.");
		} else {
			System.out.println("Validation failed:  Clinic stock quantity affected");
		}

	}
	
	@Test
	public void validateUsercanAcceptIBT() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell = reader.readNext();

		String cat = csvCell[22];
		String brand = csvCell[23];
		String model =csvCell[24];
		String clinic = csvCell[25];
		String code = csvCell[26];
		transfer = new TransferPage(driver);
		transfer.gotoDevicesPage();
        int ontransbefore = transfer.GetonTransferquantity();
        System.out.println("On transfer number before: " + ontransbefore);
		transfer.gotoTransferPage();
		transfer.createInboundTransfer(cat, brand, model, clinic);
     	WebElement Statusdraft = driver.findElement(By.xpath("//span[contains(text(), 'Draft')]"));
		Assert.assertNotNull(Statusdraft);
		transfer.gotoDevicesPage();
		int ontransferafter = transfer.GetonTransferquantity();
		 System.out.println("On transfer number after: " + ontransferafter);
		
		 if(ontransferafter == ontransbefore+1 )
		 {
			 System.out.println("Validation passed: the quantity of Ontransfer increased after IBT");
		 }
		 else {
			 System.out.println("Validation failed: the quantity of Ontransfer is wrong");
		 }
		
	}
}