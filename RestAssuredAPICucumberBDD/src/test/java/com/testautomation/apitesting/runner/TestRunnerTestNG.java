package com.testautomation.apitesting.runner;

import org.testng.annotations.*;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;


@CucumberOptions(features = "./features",
	glue = {"com.testautomation.apitesting.stepdefs"},
	tags = "@testNG",  plugin = {"pretty", "html:target/site/cucumber-pretty", 
		    "json:target/cucumber.json",
            "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true)

public class TestRunnerTestNG {
	
	private TestNGCucumberRunner testNGCucumberRunner;
	
	 @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {    	
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }

	    @Test(dataProvider = "features")    
	    public void feature(PickleWrapper eventwrapper,FeatureWrapper cucumberFeature) throws Throwable {
	    	
	    	testNGCucumberRunner.runScenario(eventwrapper.getPickle());
	    }
	    
	    @DataProvider
	    public Object[][] features() {
	       // return testNGCucumberRunner.provideFeatures();    	
	    	 return testNGCucumberRunner.provideScenarios();
	    }
	    
	    @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {    	
	        testNGCucumberRunner.finish();        
	    }	

}
