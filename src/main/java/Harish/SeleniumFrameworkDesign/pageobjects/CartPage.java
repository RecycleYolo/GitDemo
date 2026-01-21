package Harish.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class CartPage extends  AbstractComponents{
	
	WebDriver driver;
	
		public CartPage (WebDriver driver){
			
			super(driver);
			this.driver=driver;
			
			PageFactory.initElements(driver, this);  
		}
		
		
		@FindBy(css=".cart h3") 
		List<WebElement> cartItems;
		
		@FindBy(xpath="//button[text()='Checkout']") 
		WebElement checkOutButton;
		
		
		By cartItemsByLocator = By.cssSelector(".cart");
		
		
    public List<WebElement> getCartItemList() {
			
			
			waitElementToAppear(cartItemsByLocator);
			return cartItems;		
}
    
    public Boolean itemNameCheck(String itemName) {
    	
    	//Use anyMatch to check if the list contains the name that matches and store as boolean
    	
    	Boolean match =cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(itemName));
    	return match;
    	}
    
    
    public FinalCartPage checkOut() {
    	
    	checkOutButton.click();
    	FinalCartPage finalCartPage = new FinalCartPage(driver);
    	
    	return finalCartPage;
    	
    }
}