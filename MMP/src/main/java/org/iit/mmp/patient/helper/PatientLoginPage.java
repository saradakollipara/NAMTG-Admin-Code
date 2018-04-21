package org.iit.mmp.patient.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientLoginPage {


	WebDriver driver;
	public PatientLoginPage(WebDriver driver)
	{
		this.driver= driver;
	}
	public boolean login(String uname,String pword )
	{
		//driver.navigate().to(url+GlobalVariables.SYMPTOMSURL );
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pword);
		WebDriverWait wait  = new WebDriverWait(driver,30);
		WebElement submitWE= wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
		submitWE.sendKeys(Keys.ENTER);
		String actual = driver.findElement(By.tagName("h3")).getText();
		return actual.contains(uname);
	}
}
