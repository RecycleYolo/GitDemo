package Harish.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
WebDriver driver;
	
	//Constructor is the first method initialised when naything touches your class
	public OrderPage(WebDriver driver){
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);  //this driver is used to initialize all the elements below
	}
	
	@FindBy(css="tr td:nth-child(3)") 
	List<WebElement> orderItems;
	
	
	 public Boolean verifyOrderDisplay(String itemName) {
	    	
	    	//Use anyMatch to check if the list contains the name that matches and store as boolean
	    	
	    	Boolean match =orderItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(itemName));
	    	return match;
	    	}
	    

}
