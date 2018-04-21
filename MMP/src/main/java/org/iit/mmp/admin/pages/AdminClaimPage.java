package org.iit.mmp.admin.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.iit.mmp.util.TestBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AdminClaimPage {
	
	WebDriver driver;
	
	By searchTxtbox = By.id(TestBaseClass.prop.getProperty("admin_claim_page_stxtbox"));
	By sbutton=By.className("tfbutton");
	By gender = By.id("male");
	By worksts = By.id("worksts");
	By dos = By.id("dos");
	By grpNo = By.id("license");
	By pser = By.id("pser");
	By mcode = By.id("mcode");
	By npiCode = By.cssSelector("div>#pser");
	By icdCode = By.id("dia");
	By cptCode = By.id("proc");
	By balance = By.id("bal");
	By insName = By.xpath("//select[@id='insname' AND @name='insname']");
	By submitButton = By.xpath("//input[@value='Submit Claim']");
	By doc = By.id("doc");
	public AdminClaimPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public void searchByPatientName(String name)
	{
		driver.findElement(searchTxtbox).sendKeys(name);
		driver.findElement(sbutton).click();
	}
	//to search Patient by his SSN number 
	public void searchBySSN(String ssn)
	{
		driver.findElement(searchTxtbox).sendKeys(ssn);
		driver.findElement(sbutton).click();
	}
	
	public Boolean clickPatientUsingFName(String patientName)
	{
		driver.findElement(By.partialLinkText(patientName)).click();
		return true;
	}
	 
	public String submitClaim() throws InterruptedException
	{	
		try {
		driver.findElement(gender).click();
		driver.findElement(worksts).click();
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		driver.findElement(dos).sendKeys( sdf.format(dt));
		
		driver.findElement(grpNo).sendKeys("123456");
		driver.findElement(pser).sendKeys("US");
		new Select(driver.findElement(doc)).selectByVisibleText("Beth");
		new Select(driver.findElement(mcode)).selectByVisibleText("100");
		//new Select(driver.findElement(npiCode)).selectByVisibleText("100");
		new Select(driver.findElement(icdCode)).selectByVisibleText("100");
		Thread.sleep(3000);
		new Select(driver.findElement(cptCode)).selectByVisibleText("102");		
		driver.findElement(balance).sendKeys("10000");
		Thread.sleep(10000);
		new Select(driver.findElement(insName)).selectByIndex(3);	
		driver.findElement(By.id("ch")).click();
		driver.findElement(submitButton).submit();
		 
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in submitting the claim" + e.getMessage());
			

		}return driver.switchTo().alert().getText();
	}
}
