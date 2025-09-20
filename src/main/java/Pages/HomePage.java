package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Represents Home Page of Tealium Ecommerce Demo
 */
public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Accessories")
    private WebElement accessoriesLink;
    
    @FindBy(xpath = "//span[@class='label'][normalize-space()='Account']")
    private WebElement AccountLink;
    
    @FindBy(xpath = "//a[normalize-space()='Log In']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement registerLink;
    
    @FindBy(xpath = "//input[@id='privacy_pref_optin']")
    private WebElement CookieIn;
    
    @FindBy(xpath = "//div[@id='consent_prompt_submit']")
    private WebElement submitBtnForCookie;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    public void AcceptCookie() {
    	wait.until(ExpectedConditions.elementToBeClickable(CookieIn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(submitBtnForCookie)).click();
    }

    public void navigateToLoginPage() {
    	wait.until(ExpectedConditions.elementToBeClickable(AccountLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void navigateToRegistrationPage() {
    	wait.until(ExpectedConditions.elementToBeClickable(AccountLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void hoverAccessories() {
        // Use Actions if needed, for simplicity we click
        wait.until(ExpectedConditions.visibilityOf(accessoriesLink)).click();
    }

    public boolean isAtHomePage() {
        return driver.getTitle().contains("Tealium");
    }
}