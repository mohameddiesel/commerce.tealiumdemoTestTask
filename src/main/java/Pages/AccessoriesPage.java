package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;


/**
 * Represents Accessories > Shoes Page
 */
public class AccessoriesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;  

    @FindBy(xpath = "//a[@class='level0 has-children'][normalize-space()='Accessories']")
    private WebElement EL_Accessories;

    @FindBy(xpath = "//a[normalize-space()='Shoes']")
    private WebElement EL_shoesSection;


    public AccessoriesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver); 
        PageFactory.initElements(driver, this);
    }

    public void GoToShoesSection() {
        
        wait.until(ExpectedConditions.visibilityOf(EL_Accessories));
        actions.moveToElement(EL_Accessories).pause(Duration.ofSeconds(1)).perform(); // hover on EL_Accessories
        wait.until(ExpectedConditions.elementToBeClickable(EL_shoesSection)).click(); // click on Shoes section
    }
    
    public String getShoesURL() {
        // wait until URL contains "shoes"
        wait.until(ExpectedConditions.urlContains("shoes"));
        return driver.getCurrentUrl();
    }


}
