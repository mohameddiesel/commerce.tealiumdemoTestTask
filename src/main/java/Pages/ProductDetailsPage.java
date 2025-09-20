package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@class='h1']")
    private WebElement productTitle;

    @FindBy(xpath = "//img[@alt='Black']")
    private WebElement colorOption;

    @FindBy(xpath = "//span[@class='swatch-label'][normalize-space()='10']")
    private WebElement sizeOption;

    @FindBy(xpath = "//button[@onclick='productAddToCartForm.submit(this)']//span//span[contains(text(),'Add to Cart')]")
    private WebElement addToCartButton;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
    }

    public void selectColor() {
        wait.until(ExpectedConditions.visibilityOf(colorOption)).click();
    }

    public void selectSize() {
        wait.until(ExpectedConditions.visibilityOf(sizeOption)).click();
    }

    public void addToCart() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
    }
}
