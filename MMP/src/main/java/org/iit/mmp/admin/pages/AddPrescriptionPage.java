package org.iit.mmp.admin.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddPrescriptionPage {
WebDriver driver;
	
	public AddPrescriptionPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String addPrescriptionMethod(String prescriptionname,String presdescription) throws InterruptedException
	{
	//click on add prescription tab
	driver.findElement(By.xpath("//input[@value='Add Precription']")).click();
	
	WebElement appt=driver.findElement(By.id("app_date"));
	 Select apptdrop=new Select(appt);
	apptdrop.selectByIndex(2);
	 
	//click on prescription name
	driver.findElement(By.id("exampleInputcardnumber1")).clear();
	driver.findElement(By.id("exampleInputcardnumber1")).sendKeys(prescriptionname);
	
	//click on prescription description
	driver.findElement(By.xpath("//textarea[@name='p_desc']")).clear();
	driver.findElement(By.xpath("//textarea[@name='p_desc']")).sendKeys(presdescription);
	
	driver.findElement(By.xpath("//input[@value='submit']")).click();
	
	 // Switching to Alert        
    Alert alert = driver.switchTo().alert();		
    		
    // Capturing alert message.    
    String alertMessage= driver.switchTo().alert().getText();		
    		
    // Displaying alert message		
    System.out.println("***********alert message came after the message tab*********:::"+alertMessage);	
    Thread.sleep(5000);
    		
    // Accepting alert		
    alert.accept();	
    return alertMessage;
}
}
