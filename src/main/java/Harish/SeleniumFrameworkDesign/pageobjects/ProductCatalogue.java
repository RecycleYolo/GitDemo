package Harish.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	
		public ProductCatalogue (WebDriver driver){
			
			super(driver);
			this.driver=driver;
			
			PageFactory.initElements(driver, this);  
		}
		

		@FindBy(css="div.mb-3") 
		List<WebElement> items;
		
		@FindBy(css=".ng-animating") 
		WebElement animationSpinner;
		
		@FindBy(css="button[routerlink='/dashboard/cart']") 
		WebElement cartButton;
		
		By itemByLocator = By.cssSelector("div.mb-3");
		By addToCartByLocator = By.cssSelector("i[class='fa fa-shopping-cart']");
		By toastByLocator = By.cssSelector("div[id='toast-container']");
		
		

		
		public List<WebElement> getItemList() {
			
			
			waitElementToAppear(itemByLocator);
			return items;		
			
		}
		
		
		public WebElement getItemByname(String itemName) {
			
			WebElement ourItem = items.stream().filter(item->item.findElement(By.cssSelector("b")).getText().equals(itemName)).findFirst().orElse(null);
			
			return ourItem; 
			
		}
		
		public void addProductToCart(String itemName) throws InterruptedException {
			
			WebElement item = getItemByname(itemName);
			item.findElement(addToCartByLocator).click();
			
			waitElementToAppear(toastByLocator);
			
			//.ng-animating - ask the developer
			//and driver.find element - to counter performance issues
			
			waitElementToDisappear();
			
			
			
			
		}
		
		
}


