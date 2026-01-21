package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



//giver path of feature file and step definition file
//monochrome - give the results in readable format
//plugin = type of report (key value pairs)
@CucumberOptions(features="src/test/java/Cucumber", glue="Harish.SeleniumFrameworkDesign.StepDefinition",tags= "@Regression", monochrome=true, plugin= {"html:target/cucumber.html"})

//Import AbstractTestNGCucumberTests = seamless integration with cucumber - test ng support
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
