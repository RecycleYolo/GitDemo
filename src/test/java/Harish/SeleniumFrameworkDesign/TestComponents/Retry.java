package Harish.SeleniumFrameworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//This is to double check failed tests (flacky tests) - rerun the failed test one more time

public class Retry implements IRetryAnalyzer {
	
	int count =0;
	
	//how many times i need to rerun
	int maxtry = 1;

	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxtry) {
			
			count++;
			return true;
			
		}
		
		
		return false;
	}

}
