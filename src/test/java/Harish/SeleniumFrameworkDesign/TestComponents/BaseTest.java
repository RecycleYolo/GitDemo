package Harish.SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Harish.SeleniumFrameworkDesign.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	//setting up common automation
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		Properties prop = new Properties();
		
		//system.getproperty to generate path dynamically
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Harish\\SeleniumFrameworkDesign\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		//for browser to be specified by maven --> command prompt
		//this means if it is specified as not null - it should obey the command prompt command - 
		// terniary operator - if the condition is true first argument will be executed and if not gets the next argument
		String browserName =	System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		
		//String browserName = prop.getProperty("browser");
		
		
		// properties class - can use properties files which we create in resources
		if(browserName.contains("chrome")) {
		//to run the test in headless mode - use chrome option
		
		ChromeOptions options = new ChromeOptions();	
			
			
		WebDriverManager.chromedriver().setup();
		
		//check if the command prompt argument has word headless
		if(browserName.contains("headless")) {
		
		//run in headless mode
		options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		
		// to set the window full screen in headless mode
		driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		else if(browserName.contains("firefox")){
			String browserPath = "C:\\Users\\hari1\\OneDrive\\Documents\\seleniumss\\webdrivers\\geckodriver-v0.36.0-win-aarch64\\geckodriver.exe";
			System.setProperty("webdriver.chrome.driver", browserPath);
			driver = new FirefoxDriver();
			
		}
		
        else if(browserName.contains("edge")){
        	String browserPath = "C:\\Users\\hari1\\OneDrive\\Documents\\seleniumss\\webdrivers\\edgedriver_win64\\msedgedriver.exe";
			System.setProperty("webdriver.chrome.driver", browserPath);
			driver = new EdgeDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}
	
	//get hashmap from file
       public List<HashMap<String, String>> jsonToHashMap(String filePath) throws IOException {
		
		String jsonContent =FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to hashmap - jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List <HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference <List <HashMap<String,String>>>() {
		});
		
		return data;
	}
       
   	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		//screenshot in selenium
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		
		String sShotStoragePath = "C:\\Eclipse works\\SeleniumFrameworkDesign\\screenShots"+ testCaseName + ".png";
		File file = new File(sShotStoragePath);
		//(source,destination)
		FileUtils.copyFile(source, file);
		return sShotStoragePath;
   	}
	
	//always run is used to run this method even if this does not fall under group
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver = initializeDriver();
		
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}
	
	
}
