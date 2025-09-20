package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ShoesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div.page-title h1")
    private WebElement shoesHeader;

    @FindBy(xpath = "//select[@title='Sort By']")
    private WebElement sortDropdown;

    @FindBy(xpath = "(//a[@title='Set Descending Direction'][normalize-space()='Set Descending Direction'])")
    private WebElement sortDirection;

    @FindBy(xpath = "//a[normalize-space()='Dorian Perforated Oxford']")
    private WebElement dorianShoes;

    // 🔹 Use locator instead of @FindBy list (to avoid stale elements)
    private By priceLocator = By.cssSelector("span.regular-price span.price");

    public ShoesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return wait.until(ExpectedConditions.visibilityOf(shoesHeader)).getText();
    }

    public void sortByPriceLowToHigh() {
        // Step 1: Select "Price" in the dropdown
        new Select(sortDropdown).selectByVisibleText("Price");

        // Step 2: Wait for the list to refresh
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceLocator));

        // Step 3: If not sorted ascending, click the sort direction toggle
        if (!isSortedAscending()) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(sortDirection)).click();
            } catch (Exception e) {
                // Fallback: Use JavaScript click if normal click fails
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sortDirection);
            }
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceLocator));
        }
    }

    public List<Double> getAllPrices() {
        return driver.findElements(priceLocator).stream()
                .map(p -> Double.parseDouble(
                        p.getText().replace("$", "").replace(",", "").trim()
                ))
                .collect(Collectors.toList());
    }

    public boolean isSortedAscending() {
        List<Double> priceValues = getAllPrices();

        System.out.println("Prices on page: " + priceValues);

        List<Double> sorted = priceValues.stream().sorted().collect(Collectors.toList());

        System.out.println("Expected ascending: " + sorted);

        return priceValues.equals(sorted);
    }

    public void openDorianShoes() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dorianShoes);
        wait.until(ExpectedConditions.elementToBeClickable(dorianShoes)).click();
    }
}
