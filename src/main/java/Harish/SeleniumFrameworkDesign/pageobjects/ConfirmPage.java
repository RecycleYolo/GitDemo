package Harish.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class ConfirmPage extends  AbstractComponents{
	WebDriver driver;
	public ConfirmPage (WebDriver driver){
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);  
	}

	@FindBy(css=".hero-primary") 
	WebElement confirmMessage;
	
	
	public String confirmSuccessMessage() {
		
		String thankyou = confirmMessage.getText();
		return thankyou;
	}
	
}
