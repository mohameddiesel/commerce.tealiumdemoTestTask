package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.AllureListener;
import utils.ConfigReader;
import utils.DriverManager;

/**
 * Base class for all test classes
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	 driver = DriverManager.getDriver();   //  initialize driver
         driver.manage().window().maximize();
        driver.get(ConfigReader.getConfigProperty("url"));
        AllureListener.setDriver(driver); // Set driver for Allure reporting
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}