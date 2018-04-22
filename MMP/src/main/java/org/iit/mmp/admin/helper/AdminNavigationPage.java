package org.iit.mmp.admin.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminNavigationPage {
	
	WebDriver driver;
	
	By userLink             =   By.xpath("//span[contains(text(),'Users')]");
	By claimCenterLink      =   By.xpath("//span[contains(text(),'Claim Center')]");
	By patientLink 			=   By.xpath("//span[contains(text(),'Patients')]");
	
	public AdminNavigationPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	// to Validate if the user is in Admin Page
	public boolean validateAdminPage() {
		String pageSource = driver.getPageSource();
		Boolean status = pageSource.contains("Admin Portal");
		System.out.println("*************  Admin Validated  *********** "); 
		return status;
		
	}
	public void clickUserLink()	{

		driver.findElement(userLink).click();
	}
	
	public void clickClaimCenterLink()	{

		driver.findElement(claimCenterLink).click();
	}
	//to click on 'Patients Tab' from Admin portal
	public void clickPatientsLink()	{

		driver.findElement(patientLink).click();
	}
	
}
