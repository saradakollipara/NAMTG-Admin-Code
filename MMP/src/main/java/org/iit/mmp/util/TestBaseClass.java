package org.iit.mmp.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseClass {

	public static Properties prop;
	public WebDriver driver;
	public String url;

	@SuppressWarnings("deprecation")
	@Parameters({"browserType","url"})	
	@BeforeClass(alwaysRun=true)
	public void setup(String browser,String url) throws Exception{
		if (browser.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//src//test//resources//geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", false);
			driver = new FirefoxDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else if (browser.equals("CH")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//test//resources//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else if(browser.equals("ED"))
		{
			System.setProperty("webdriver.edge.driver", ("user.dir")+"browserdrivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else {
			throw new Exception("Browser is not correct");
		}
		this.url=url;
		driver.get(url);

		if(prop==null)
		{
			//Properties is a class in java
			prop = new Properties();

			//loading a property file
			prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/test/resources/MMP.properties")));
		}
	 
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/resources/log.properties");
	}




	@AfterClass
	public void closeApp()
	{
		//    driver.close();
	}






}









