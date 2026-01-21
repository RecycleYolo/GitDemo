package Harish.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Harish.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Harish.SeleniumFrameworkDesign.pageobjects.CartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ConfirmPage;
import Harish.SeleniumFrameworkDesign.pageobjects.FinalCartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.LandingPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {
	
	//including and seperating test cases is according to team
	// all negative testcase in here
	
	// only use retryanalyzer if the test fails in random occasion (if not sure)
	
		@Test(groups= {"ErrorHandling"},retryAnalyzer =Harish.SeleniumFrameworkDesign.TestComponents.Retry.class)
		public void loginErrorValidation() throws InterruptedException, IOException {

	
		//using wrong email id and password
		
		ProductCatalogue productCatalogue=landingPage.loginActions("hare234@gmail.com","Haeri@123" );
		
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	
	}

		@Test
		public void productErrorValidation() throws InterruptedException, IOException {
	
		String itemName = "ZARA COAT 3";
	
	
	
		ProductCatalogue productCatalogue=landingPage.loginActions("har1234@gmail.com","Hari@123" );
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		List<WebElement> items= productCatalogue.getItemList();
		
		productCatalogue.addProductToCart(itemName);
		
	
		CartPage cartPage =productCatalogue.goToCart();
		
		List<WebElement> cartItems= cartPage.getCartItemList();
		
		Boolean match = cartPage.itemNameCheck("ZARA COAT 33");
		

		Assert.assertFalse(match);
	
	}
		
}
