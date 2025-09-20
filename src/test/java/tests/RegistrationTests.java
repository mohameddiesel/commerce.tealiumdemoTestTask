package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.RegistrationPage;
import io.qameta.allure.*;
import utils.ConfigReader;

/**
 * Test cases for User Registration
 */
@Epic("User Management")
@Feature("User Registration")
public class RegistrationTests extends BaseTest {

    @Test(description = "TC_REG_001: Register with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test user registration with valid data")
    @Story("User Registration")
    public void testValidRegistration() {
        Allure.step("Navigate to registration page");
        HomePage homePage = new HomePage(driver);
        homePage.AcceptCookie();
        homePage.navigateToRegistrationPage();
     
        

        RegistrationPage regPage = new RegistrationPage(driver);
        Allure.step("Fill registration form with valid data");
        regPage.register( 
            ConfigReader.getTestDataProperty("valid.firstname"),
            ConfigReader.getTestDataProperty("valid.middlename"),
            ConfigReader.getTestDataProperty("valid.lastname"),
            ConfigReader.getTestDataProperty("valid.email"),
            ConfigReader.getTestDataProperty("valid.passwordField"), 
            ConfigReader.getTestDataProperty("valid.passwordField")
        );

        Assert.assertTrue(regPage.isRegistrationSuccessful(), "User should be redirected to dashboard after valid registration.");
        Assert.assertEquals(regPage.getWelcomeMessage(), "Thank you for registering with Tealium Ecommerce.");
    }

    @Test(description = "TC_REG_002: Register with invalid email")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test user registration with invalid email")
    @Story("User Registration")
    public void testInvalidEmailRegistration() {
        Allure.step("Navigate to registration page");
        HomePage homePage = new HomePage(driver);
        homePage.AcceptCookie();
        homePage.navigateToRegistrationPage();
  

        RegistrationPage regPage = new RegistrationPage(driver);
        Allure.step("Fill registration form with invalid email");
        regPage.register(
            ConfigReader.getTestDataProperty("invalid.firstname"),
            ConfigReader.getTestDataProperty("invalid.middlename"),
            ConfigReader.getTestDataProperty("invalid.lastname"),
            ConfigReader.getTestDataProperty("invalid.email"),
            ConfigReader.getTestDataProperty("invalid.passwordField"), 
            ConfigReader.getTestDataProperty("invalid.passwordField")
        );

        String errorMsg = regPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Please enter a valid email address. For example johndoe@domain.com"), 
            "Error message should contain valid email format instruction");
    }
}