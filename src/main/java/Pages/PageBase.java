package Pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	public WebDriver driver ;

	public  PageBase(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	//SearchBar
	@FindBy(xpath="//button[@type=\"button\"]")
	WebElement SearchBtn ;
	@FindBy(xpath=" //input[@id='txt_search_query']")
	WebElement SearchBox ;
	
	//##################################################################################################
	
	public void waitUntilWebElemntsVisiable(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(80));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void SetTextAndPressEnter(WebElement Element ,String value)
	{
		waitUntilWebElemntsVisiable(Element);
		Element.clear();
		Element.sendKeys(value);
		Element.sendKeys(Keys.ENTER);
	}
	public void ClickOnBtn(WebElement elemnet)
	{
		waitUntilWebElemntsVisiable(elemnet);
		elemnet.click();
	}
	
	public void ScrollDownAndClickOnTheElement(WebElement Element)
	{
		waitUntilWebElemntsVisiable(Element);
		Actions builder = new Actions(driver);
		builder.moveToElement(Element).build().perform();
		ClickOnBtn(Element);
	}
}
