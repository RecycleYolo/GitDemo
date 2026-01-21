package Harish.SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Harish.SeleniumFrameworkDesign.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
//		String browserPath = "C:\\Users\\hari1\\OneDrive\\Documents\\seleniumss\\webdrivers\\chromedriver-win64\\chromedriver.exe";
//		System.setProperty("webdriver.chrome.driver", browserPath);
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		
//		
		
		//use this instead of above code
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		driver.findElement(By.id("userEmail")).sendKeys("har1234@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Hari@123");
		
		driver.findElement(By.id("login")).click();
		
		LandingPage landingPage = new LandingPage(driver);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
		List<WebElement> items = driver.findElements(By.cssSelector("div.mb-3"));
		
		WebElement ourItem = items.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		ourItem.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
		
		
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='toast-container']")));
		
		//.ng-animating - ask the developer
		//and driver.find element - to counter performance issues
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart")));
		
		List<WebElement> cartItem = driver.findElements(By.cssSelector(".cart h3"));
		
		//Use anyMatch to check if the list contains the name that matches and store as boolean
		
		Boolean Match = cartItem.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(Match);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		
		String thankyou = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(thankyou.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
