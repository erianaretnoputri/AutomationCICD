package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//  	cucumber -> TestNG, JUnit
@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinition",
monochrome=true, tags = "Regression", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
