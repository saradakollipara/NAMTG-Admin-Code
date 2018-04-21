package org.iit.mmp.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
/**
 * To navigate to Patient Login/Register Section
 * @author qa
 *
 */
public class HomePage{

	Logger logger = Logger.getLogger("HomePage.class");
	By patientButton = By.xpath(TestBaseClass.prop.getProperty("patientButton"));
	By patientLoginButton = By.cssSelector(TestBaseClass.prop.getProperty("patientLoginButton"));
	By registerButton = By.xpath(TestBaseClass.prop.getProperty("registerButton"));
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		logger.info("Setting the Driver Instance");
		this.driver= driver;
	}
	public void navigateToPatLoginPage() throws InterruptedException {

		try{
			driver.findElement(patientButton).click();
			logger.info("patient Button Xpath"+ TestBaseClass.prop.getProperty("patientButton"));
			logger.info("Clicking on the patient Button");
			Thread.sleep(5000);
			driver.findElement(patientLoginButton).click();
			logger.info("Clicking on the patient Login Button");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void navigateToPatientRegPage() throws InterruptedException 
	{
		try{
			driver.findElement(patientButton).click();
			logger.info("patient Button Xpath"+ TestBaseClass.prop.getProperty("patientButton"));
			logger.info("Clicking on the patient Button");
			Thread.sleep(5000);
			driver.findElement(registerButton).click();
			logger.info("Clicking on the patient Register Button");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
