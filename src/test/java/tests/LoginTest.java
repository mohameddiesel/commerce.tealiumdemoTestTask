package tests;

import Pages.HomePage;
import Pages.LoginPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

/**
 * Test class for Login functionality - Valid and Invalid cases
 */
@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginTest extends BaseTest {

    /**
     * TC003: Validate that a user can log in with valid credentials.
     * Expected: Redirected to account dashboard (URL contains 'customer')
     */
    @Test(description = "TC003: User Login with Valid Credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test valid user login functionality")
    @Story("User Login")
    public void testValidLogin() {
        Allure.step("Navigate to login page");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        //Go To login Page then login
        homePage.AcceptCookie();
        homePage.navigateToLoginPage();
    
   
        Allure.step("Enter valid credentials and login");
        loginPage.login(
            ConfigReader.getTestDataProperty("valid.email"),
            ConfigReader.getTestDataProperty("valid.passwordField")
        );
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed: User was not redirected to customer dashboard.");
        
        Assert.assertEquals(loginPage.getWelcomeMessage(), "WELCOME, MOHAMED HELAL KHAKIL!");
    }

    /**
     * TC004: Validate that the application shows appropriate error message for invalid login.
     * Expected: Error message displayed"
     */
    @Test(description = "TC004: User Login with Invalid Credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test invalid user login functionality")
    @Story("User Login")
    public void testInvalidLogin() {
        Allure.step("Navigate to login page");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.AcceptCookie();
        homePage.navigateToLoginPage();
    
        Allure.step("Enter invalid credentials and attempt login");
        loginPage.login(
            ConfigReader.getTestDataProperty("invalid.email"),
            ConfigReader.getTestDataProperty("invalid.passwordField")
        );
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "Invalid login or password.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}