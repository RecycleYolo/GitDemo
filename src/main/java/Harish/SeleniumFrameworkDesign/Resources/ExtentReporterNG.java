package Harish.SeleniumFrameworkDesign.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	// use static - so you can use the class name like methods
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter( path);
		

		
		reporter.config().setReportName("Web Automation Results");
		
		reporter.config().setDocumentTitle("Test Results");
		

		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Harish");
		return extent;
	}

}
