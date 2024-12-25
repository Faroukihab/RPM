package ProjectLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Locators {
    @FindBy(id="email")
    public   WebElement UserTxtBox ;

    @FindBy(id="clinic_code")
    public   WebElement UserTxtcode ;

    @FindBy(id="password")
    public   WebElement Password ;

    @FindBy(css = "button.btn.btn-primary.w-100")
    public  WebElement SigninBtn ;

    @FindBy(xpath = "//img[@alt='avatar']")
    public  WebElement avatar ;

    @FindBy(xpath = "//span[text()='Log Out']")
    public WebElement logoutBtn ;

    	@FindBy(xpath = "//span[text()='Clinics Management']")
        public WebElement clinicmanageBtn;

	@FindBy(css = "a[data-test_id='create_button']")
    public   WebElement AddBtn;

	@FindBy(css = "a[data-test_id='show_button']")
    public    WebElement ShowBtn;

	@FindBy(css = "a[data-test_id='edit_button']")
    public WebElement EditBtn;

	@FindBy(id = "name")
    public WebElement AssistantName;

	@FindBy(id = "email")
    public  WebElement Assistantmail;

	@FindBy(id = "contact")
    public  WebElement Assistantcontact;

	@FindBy(id = "clinic-select-ts-control")
    public   WebElement AssistantClinic;

	@FindBy(css = "button[data-test_id='submit_button']")
    public   WebElement SaveBtn;


	@FindBy(xpath = "//a[text()='Assistants']")
    public WebElement AssistantBtn;

    @FindBy(id = "t-text")
  public  WebElement brandNameTxtfield;

    @FindBy(xpath =  "//a[text()='Brands']")
    public WebElement brandsBtn;

    @FindBy(xpath = "//span[text()='Devices Management']")
   public  WebElement devicesBtn;

    @FindBy(xpath="//a[text()='Categories']")
  public  WebElement categoryBtn ;

    @FindBy(css="svg.feather.feather-chevrons-left")
  public   WebElement menuBtn ;

    @FindBy(id = "t-text")
  public   WebElement categorynameTxtfield ;


    @FindBy(xpath = "//a[contains(text(),'Clinics')]")
public   WebElement clinicBtn;

    @FindBy(id = "name")
  public  WebElement clinicNameField;

    @FindBy(xpath = "//a[contains(text(),'Doctors')]")
  public  WebElement DoctorBtn;


    @FindBy(id = "name")
  public  WebElement DoctornameField;

    @FindBy(id = "email")
  public   WebElement DoctormailField;

    @FindBy(id = "contact")
  public  WebElement DoctorcontactField;

    @FindBy(id = "npi")
  public   WebElement DoctorPracteniorID;


    @FindBy(id = "clinic-select-ts-control")
  public  WebElement Clinicdropdown;

    @FindBy(xpath ="//a[contains(text(),'Models')]")
  public  WebElement modelsBtn ;

    @FindBy(id="t-text")
  public  WebElement modelNameTxtfield ;

    @FindBy(css ="#category-select-ts-control")
  public  WebElement categoryDropdownList ;

    @FindBy(css ="input#brand-select-ts-control")
    public WebElement brandDropdownList ;

    @FindBy(xpath = "//a[text()=Patients']")
  public   WebElement patientBtn;



    @FindBy(xpath = "//a[text()='Devices']")
  public   WebElement patientDeviceBtn;

    @FindBy(id = "name")
  public   WebElement patientnamefield;

    @FindBy(id = "gender")
  public   WebElement patientgender;

    @FindBy(id = "dob")
  public   WebElement patientdob;

    @FindBy(id = "height")
  public   WebElement patientheight;

    @FindBy(id = "phone_number")
  public   WebElement patientnumber;

    @FindBy(id = "secondary_number")
  public   WebElement patientsecondarynuber;

    @FindBy(id = "insurance_name")
  public   WebElement patientinsurancenumberfield;

    @FindBy(id = "address")
  public   WebElement patientaddressfield;

    @FindBy(id = "policy_number")
  public   WebElement policynumberfield;

    @FindBy(id = "clinic-select-ts-control")
  public   WebElement patientclinicdropdown;

    @FindBy(xpath="//a[contains(text(),'Devices')]")
   public WebElement serialBtn ;



    @FindBy(id ="category-select-ts-control")
  public   WebElement categoryField ;

    @FindBy(id ="brand-select-ts-control")
  public   WebElement brandField ;

    @FindBy(id= "model-select-ts-control")
  public   WebElement modelField ;

    @FindBy(id= "device-serial")
  public   WebElement serialnumberField ;

    @FindBy(xpath = "//span[contains(text(), 'Stocks')]")
  public   WebElement stockBtn;

    @FindBy(xpath = "//a[text()='Transfers']")
  public   WebElement transferBtn;

    @FindBy(id = "clinic-select-ts-control")
  public   WebElement clinicdropdown;

    @FindBy(id = "transfer_date")
  public   WebElement transferdate;

    @FindBy(xpath = "//label[@for='manual-input']")
  public   WebElement choosmanualoption;

    @FindBy(id = "category-select-ts-control")
  public   WebElement categorydropdown;

    @FindBy(id = "brand-select-ts-control")
  public   WebElement branddropdown;

    @FindBy(id = "model-select-ts-control")
  public   WebElement modeldropdown;

    @FindBy(id = "serial-number-select-ts-control")
  public   WebElement serialfield;

    @FindBy(id = "add-device")
  public  WebElement addBtn;

    @FindBy(xpath = "//button[text()='Publish']")
  public   WebElement publishBtn;

    @FindBy(xpath = "//span[text()='Reports']")
  public   WebElement reportsBtn;

    @FindBy(xpath = "//a[text()='Device Monitoring']")
  public  WebElement devicemonitorgBtn;

    @FindBy(id = "is_from_company-select")
 public   WebElement Transferdropdown;
}
