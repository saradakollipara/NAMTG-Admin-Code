package org.iit.mmp.admin.pages;

import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
/**
 * 
 * This class useful to approve the patient which is registered
 * @author qa
 *
 */
public class AdminUsersPage {
	WebDriver driver;


	By userLink             =   By.xpath("//div[1]/div/div[1]/div/ul/li[3]/a/span");
	By selectStatus         =   By.id("sapproval");
	By pendingStatusSelect	=   By.id("search");
	By submitButton         =   By.cssSelector("input[value = 'Submit']");


	public 	AdminUsersPage(WebDriver driver) {

		this. driver = driver;
	}
	public void clickPatientNameLink(String patName){

		driver.findElement(By.linkText(patName)).click();
	}

	public void selectStatus(String text)	{

		WebElement status =	driver.findElement(selectStatus);
		Select selectOption = new Select(status);
		selectOption.selectByVisibleText(text);

	}
	public void clickSubmitButton(){

		try{
			driver.findElement(submitButton).click();
		}
		catch(Exception e)
		{
			System.out.println("unable to click on submit button " + e.getMessage());
		}
	}

	public String acceptAlert(){
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();
		return message;
	}

	public String getApprovedAdminModule(String patName,String pendingStatus,String approvedStatus){


		new Select(driver.findElement(pendingStatusSelect)).selectByVisibleText(pendingStatus);
	
		clickPatientNameLink(patName);
		
		selectStatus(approvedStatus);
		clickSubmitButton();
		return acceptAlert();

	}

	public String getApprovedAdminModule(HashMap<String, String> hMap, String pendingStatus, String approvedStatus) {
		String value = hMap.get("fName");
		return null;
	}


}
