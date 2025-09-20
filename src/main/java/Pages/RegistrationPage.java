package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents Registration Page
 */
public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='firstname']")
    private WebElement EL_firstname;
    
    @FindBy(xpath = "//input[@id='middlename']")
    private WebElement EL_middlename;
    
    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement EL_lastname;
    
    @FindBy(xpath = "//input[@id='email_address']")
    private WebElement EL_email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement EL_passwordField;


    @FindBy(xpath = "//input[@id='confirmation']")
    private WebElement EL_confirmPassword;
    
    @FindBy(xpath = "//button[@title='Register']")
    private WebElement registerButton;
    
 
    @FindBy(xpath = " //div[@id='advice-validate-email-email_address']")
    private WebElement EL_errorMessage;
    
    @FindBy(xpath = " //span[normalize-space()='Thank you for registering with Tealium Ecommerce.']")
    private WebElement EL_welcomeMessage;
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void register(String firstname, String middlename , String lastname , String email , String passwordField , String confirmPassword  ) {
        wait.until(ExpectedConditions.visibilityOf(EL_firstname)).sendKeys(firstname);
        wait.until(ExpectedConditions.visibilityOf(EL_middlename)).sendKeys(middlename);
        wait.until(ExpectedConditions.visibilityOf(EL_lastname)).sendKeys(lastname);
        wait.until(ExpectedConditions.visibilityOf(EL_email)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(EL_passwordField)).sendKeys(passwordField);
        wait.until(ExpectedConditions.visibilityOf(EL_confirmPassword)).sendKeys(passwordField);
        
        registerButton.click();
    }
  public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(EL_errorMessage));
        return EL_errorMessage.getText();
    }

    public String getWelcomeMessage ()
{
	return wait.until(ExpectedConditions.visibilityOf(EL_welcomeMessage)).getText();
}
    public boolean isRegistrationSuccessful() {
    	
        return driver.getCurrentUrl().contains("customer");
    }
}