package Pages;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents Login Page
 */
public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement EL_email;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement EL_password;

    @FindBy(xpath = "//button[@id='send2']")
    private WebElement loginButton;

    @FindBy(xpath = "//li[@class='error-msg']//ul//li")
    private WebElement errorMessage;

  
    @FindBy(xpath = "//p[@class='welcome-msg']")
    private WebElement welcomeMessage;
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(EL_email)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(EL_password)).sendKeys(password);
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
      
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("customer");
    }
    public String getWelcomeMessage() {
        return     wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).getText();
    }
}