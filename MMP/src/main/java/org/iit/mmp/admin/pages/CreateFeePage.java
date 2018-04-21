package org.iit.mmp.admin.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateFeePage {
	
	WebDriver driver;
	public CreateFeePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String createFeeMethod() throws InterruptedException
	{
		
		//click on create fee tab
				driver.findElement(By.xpath("//input[@value='Create Fee']")).click();
				
				//click on select Appointments dropdown
				WebElement appt=driver.findElement(By.id("app_date"));
				 Select apptdrop=new Select(appt);
				apptdrop.selectByIndex(2);
				
				//click on select service dropdown
				WebElement service=driver.findElement(By.id("service"));
				 Select servicetdrop=new Select(service);
				 servicetdrop.selectByIndex(2);
				 
				 Thread.sleep(2000);
				 
				 //fee automatically coming
				 WebElement feevalue=driver.findElement(By.xpath("//input[@class='form-control'][1]"));
				 System.out.println("fee value is::"+feevalue.getText());
				 
					//submit button
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
