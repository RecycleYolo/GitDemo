package Harish.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Harish.SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	
	//Constructor is the first method initialised when anything touches your class
	public LandingPage(WebDriver driver){
		
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);  //this driver is used to initialize all the elements below
	}
	
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	
	//Page factory - instead of writing like in above mentioned line. use this.
	
	@FindBy(id="userEmail") 
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	String error =errorMessage.getText();
	return error;
}
	
	public ProductCatalogue loginActions(String email, String pass ) {
		
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		loginButton.click();
		
		//to minimize the use of creating objects in testcase. we can create the object of next page in previous pages last action step
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	
	
}
