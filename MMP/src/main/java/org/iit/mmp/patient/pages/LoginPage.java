package org.iit.mmp.patient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {


	WebDriver driver;



	By patientLogin    = By.linkText("Patient Login");
	By login           = By.cssSelector("div>a[href='portal/login.php']");
	By userName        = By.id("username");
	By password        = By.id("password");
	By signIn          = By.name("submit");



	public LoginPage(WebDriver driver){
		this.driver = driver;
	}

	public void clickPatientLogin(){
		driver.findElement(patientLogin).click();
	}
	public void clickLogin(){

		driver.findElement(login).click();

	}
	public void enterUserName(String urName){
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(urName);
	}
	public void enterPassword(String passWd){
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(passWd);
	}
	public void clickSignIn(){
		driver.findElement(signIn).click();
	}

	public void verifyLogin(String urName,String passWd) throws InterruptedException{

		clickPatientLogin();
		Thread.sleep(2000);
		clickLogin();
		enterUserName(urName);
		enterPassword(passWd);
		clickSignIn();
	}

}
