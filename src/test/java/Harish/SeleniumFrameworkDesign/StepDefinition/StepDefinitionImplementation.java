package Harish.SeleniumFrameworkDesign.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Harish.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Harish.SeleniumFrameworkDesign.pageobjects.CartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ConfirmPage;
import Harish.SeleniumFrameworkDesign.pageobjects.FinalCartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.LandingPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest {
	
	//cucumber feature file integration with step definition
	
	//catch the object
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public FinalCartPage finalCartPage;
	public ConfirmPage confirmPage;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException  {
		
		landingPage =launchApplication();
	}
	
	
	// regular expressionm - (.+) - represents any value
	//and whenever there is a regular expression - start with ^ and end with $
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		productCatalogue=landingPage.loginActions(username,password);
	}
	
	@When("^I add (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		
		List<WebElement> items= productCatalogue.getItemList();
		productCatalogue.addProductToCart(productName);
		
	}
	
	//here we can use and or when
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		
		cartPage =productCatalogue.goToCart();
		List<WebElement> cartItems= cartPage.getCartItemList();
		Boolean match = cartPage.itemNameCheck(productName);
		Assert.assertTrue(match);
		finalCartPage=cartPage.checkOut();
		finalCartPage.selectCountry();
		confirmPage = finalCartPage.submitPaymentDetails();
		
	}
	
	@Then("{string} confirmation message is displayed")
	public void confirmation_message_is_displayed(String string) {
		
		String thankyou  =confirmPage.confirmSuccessMessage();
		Assert.assertTrue(thankyou.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
