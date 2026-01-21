package Harish.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Harish.SeleniumFrameworkDesign.pageobjects.CartPage;
import Harish.SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponents {
	//Abstract class to reusable code
	//use this as parent class
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);  
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']") 
	WebElement cartButton;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;
	

	public void waitElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

}
	
	public void waitForWebElementToAppear(WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}
	public void waitElementToDisappear() throws InterruptedException {
		
		Thread.sleep(2000);
		
		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		
//		wait.until(ExpectedConditions.invisibilityOf(animationSpinner));
	
}
	//this is header menu component so 
	public CartPage goToCart() {
		
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrder() {
		
		orderButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	}
	
	
