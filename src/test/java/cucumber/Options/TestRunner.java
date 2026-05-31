package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinitions"},
	    plugin={"json:target/jsonReports/cucumber-report.json",
	    		"html:target/cucumber-html-reports/cucumber-reports.html"}
	    //tags= "@DeletePlace"
	    
	)
public class TestRunner {

}
