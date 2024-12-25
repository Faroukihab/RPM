package tests;

import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import pages.serialPage;
import pages.LoginPage;

public class TestserialPage extends TestBase {

	LoginPage login;
	CSVReader reader;
	serialPage serial;

	@Test
	public void usercancreatanewDevice() throws CsvValidationException, IOException, InterruptedException
	{
		String csvFile = System.getProperty("user.dir") + "/src/test/java/data/data.csv";
		reader = new CSVReader(new FileReader(csvFile));
		String[] csvCell;
		while ((csvCell = reader.readNext()) != null) {
			String user = csvCell[0];
			String password = csvCell[1]; 
			login = new LoginPage(driver);
			serial = new serialPage(driver);
			login.Userlogin(user, password);
			serial.gotoSerialPage();
			serial.CreatnewDevice();
			serial.enterSerialNumber();
			
		}
	}
}