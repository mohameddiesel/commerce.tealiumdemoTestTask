package tests;

import Pages.AccessoriesPage;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductDetailsPage;
import Pages.ShoesPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

@Epic("Ecommerce Shopping Flow")
@Feature("Add to Cart Functionality")
public class AddShoesToCartTest extends BaseTest {

    @Test(description = "Add shoes to cart test")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify user can add shoes to cart successfully")
    @Story("Shopping Cart")
    public void testAddShoesToCart() {
        // Step 1: Login
        Allure.step("Navigate to login page and login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.AcceptCookie();
        
        //Go To login Page then login
        homePage.navigateToLoginPage();
        loginPage.login(
            ConfigReader.getTestDataProperty("valid.email"),
            ConfigReader.getTestDataProperty("valid.passwordField")
        );
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "Login failed: User was not redirected to customer dashboard.");

        // Step 2: Navigate to Accessories > Shoes
        Allure.step("Navigate to Accessories > Shoes section");
        AccessoriesPage accessoriesPage = new AccessoriesPage(driver);
        accessoriesPage.GoToShoesSection();

        // Step 3: Validate Shoes Page
        Allure.step("Validate shoes page is loaded");
        String currentUrl = accessoriesPage.getShoesURL();
        Assert.assertTrue(currentUrl.contains("shoes"), "Did not redirect to Shoes page. URL: " + currentUrl);
     
        ShoesPage shoesPage = new ShoesPage(driver);
        Assert.assertEquals(shoesPage.getHeader(), "SHOES", "Shoes page not opened!");

        // Step 4: Sort by price
        Allure.step("Sort shoes by price (Low to High)");
        shoesPage.sortByPriceLowToHigh();
        Assert.assertTrue(shoesPage.isSortedAscending(), "Shoes are not sorted ascending!");

        // Step 5: View Dorian Shoes
        Allure.step("Open Dorian Perforated Oxford product");
        shoesPage.openDorianShoes();
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
        Assert.assertEquals(detailsPage.getProductTitle(), "DORIAN PERFORATED OXFORD", " Wrong product page!");

        // Step 6: Select Color & Size
        Allure.step("Select color and size for the product");
        detailsPage.selectColor(); // only black exist
        detailsPage.selectSize(); // choose size 10

        // Step 7: Add to Cart
        Allure.step("Add product to cart");
        detailsPage.addToCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getSuccessMessage().contains("Dorian Perforated Oxford was added to your shopping cart."),
                "Product not added to cart!");
    }
}