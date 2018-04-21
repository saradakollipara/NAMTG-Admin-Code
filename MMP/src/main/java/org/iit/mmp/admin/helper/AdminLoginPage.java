package org.iit.mmp.admin.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminLoginPage {


	WebDriver driver;


	By adminLogin      =  By.linkText("Office Login");
	By loginLink       =  By.xpath("//h4[contains(text(),'doctor')]/parent::div/descendant::a[text()='Login']");
	By userName        =  By.xpath("//input[@id='username']");
	By password        =  By.id("password");
	By submitButton    =  By.name("admin");


	public AdminLoginPage(WebDriver driver){

		this.driver = driver;
	}

	public void officeAdminLogin(){

		driver.findElement(adminLogin).click();
	}

	public void clickLoginLink(){

		driver.findElement(loginLink).click();
	}

	public void enterUserName(String urName){

		try{
			WebDriverWait wait = new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(userName));
			driver.findElement(userName).clear();
			driver.findElement(userName).sendKeys(urName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in entering username" + e.getMessage());
		}
	}
	public void enterPassword(String psWord){
		try{
			driver.findElement(password).clear();
			driver.findElement(password).sendKeys(psWord);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in entering password" + e.getMessage());
		}
	}
	public void clickSubmitButton(){

		driver.findElement(submitButton).click();
	}
	public void validateAdminLogin() {
		
	}

	public void goToAdminLogin( ) {

		officeAdminLogin();
		clickLoginLink();
	}
	
	//To login as Admin and verify Admin home page after login	
		
	public void loginToAdmin(String urName,String psWord)
	{
		enterUserName(urName);
		enterPassword(psWord);
		clickSubmitButton();	
		
	}

}
