package Harish.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class FinalCartPage extends  AbstractComponents{
	
	WebDriver driver;
	
		public FinalCartPage (WebDriver driver){
			
			super(driver);
			this.driver=driver;
			
			PageFactory.initElements(driver, this);  
		}
		
		@FindBy(xpath="//input[@placeholder='Select Country']") 
		WebElement countryField;
		
		@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]") 
		WebElement india;
		
		@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']") 
		WebElement submitButton;
		
		@FindBy(css=".hero-primary") 
		WebElement confirmMessage;
		
		By countryDropDownByLocator = By.cssSelector(".ta-results");
		
		
		
		public void selectCountry() {
			
			countryField.sendKeys("ind");
			waitElementToAppear(countryDropDownByLocator);
			
			india.click();
			
		}		
	public ConfirmPage submitPaymentDetails() {	
		submitButton.click();
		
		ConfirmPage confirmPage = new ConfirmPage(driver);
		
	return confirmPage;
		
			
			
			
		}
		
		
}
		
		
		
		
		

	
		
		
		
		