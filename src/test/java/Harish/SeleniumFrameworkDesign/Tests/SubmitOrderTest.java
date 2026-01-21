package Harish.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Harish.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Harish.SeleniumFrameworkDesign.pageobjects.CartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ConfirmPage;
import Harish.SeleniumFrameworkDesign.pageobjects.FinalCartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.LandingPage;
import Harish.SeleniumFrameworkDesign.pageobjects.OrderPage;
import Harish.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String itemName = "ZARA COAT 3";
	
//		String browserPath = "C:\\Users\\hari1\\OneDrive\\Documents\\seleniumss\\webdrivers\\chromedriver-win64\\chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", browserPath);
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		
//		
	
	//thread count in xml = max number of method (browsers) alloweed to run parallely n a test
		@Test(dataProvider = "getData", groups = {"purchase"})
		public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		//using hashmap to get the data from data provider	
			
		//use this instead of above code
		
		//the below step is done i base test class - using before method
		//LandingPage landingPage=launchApplication();
	
		//landing page is declared as global variable in base test
		ProductCatalogue productCatalogue=landingPage.loginActions(input.get("email"),input.get("password") );
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		List<WebElement> items= productCatalogue.getItemList();
		productCatalogue.addProductToCart(input.get("productName"));
		
		//in gotocart method = we initialised cart page object so no need to initialise
		CartPage cartPage =productCatalogue.goToCart();
		List<WebElement> cartItems= cartPage.getCartItemList();
		Boolean match = cartPage.itemNameCheck(input.get("productName"));
		
		//validation should not be in pom pages. it should only be in test cases
		Assert.assertTrue(match);
		FinalCartPage finalCartPage=cartPage.checkOut();
		finalCartPage.selectCountry();
		ConfirmPage confirmPage =   finalCartPage.submitPaymentDetails();
		String thankyou  =confirmPage.confirmSuccessMessage();
		
		
		
		//.ng-animating - ask the developer
		//and driver.find element - to counter performance issue
		//Use anyMatch to check if the list contains the name that matches and store as boolean
		
		Assert.assertTrue(thankyou.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
		//this will only run after executing above method
		@Test(dependsOnMethods= {"submitOrder"})
		public void orderHistoryTest() {
		
			ProductCatalogue productCatalogue=landingPage.loginActions("har1234@gmail.com","Hari@123" );
			OrderPage orderPage = productCatalogue.goToOrder();
	
			Boolean match =orderPage.verifyOrderDisplay(itemName);
			Assert.assertTrue(match);
		}
		
		public String getScreenshot(String testCaseName) throws IOException {
			//screenshot in selenium
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source =ts.getScreenshotAs(OutputType.FILE);
			
			String sShotStoragePath = "C:\\Eclipse works\\SeleniumFrameworkDesign\\screenShots"+ testCaseName + ".png";
			File file = new File(sShotStoragePath);
			//(source,destination)
			FileUtils.copyFile(source, file);
			return sShotStoragePath;
			
		}
		
		
		//when there is multiple data
		@DataProvider
		public Object[][] getData() throws IOException {
			
		
			
			// Now use json to import data and convert it to hashmap without manually writing hashmap
			List <HashMap<String,String>> data =jsonToHashMap("C:\\Eclipse works\\SeleniumFrameworkDesign\\src\\test\\java\\Harish\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
			
			
		}
		//old method for data provider
//		@DataProvider
//		public Object[][] getData() {
//			
//			return new Object[][] {{"har1234@gmail.com","Hari@123","ZARA COAT 3"},{"vaishni@gmail.com","Vaish@123","ADIDAS ORIGINAL"}};
		
		
		//Create hashmap to make the data provider send multiple data with out creating multiple parameter in test case method (ignore above code)
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "har1234@gmail.com");
//		map1.put("password", "Hari@123");
//		map1.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("email", "vaishni@gmail.com");
//		map2.put("password", "Vaish@123");
//		map2.put("productName", "ADIDAS ORIGINAL");
//		return new Object[][] {{map1},{map2}};
//		}

}
