package org.iit.mmp.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReportsPage {
	WebDriver driver;
 public ReportsPage(WebDriver driver)
 {
	 this.driver=driver;
 }
 public void reportTabClick()
 {
	//click on reports tab
	 driver.findElement(By.xpath("//input[@value='Reports']"));
 }
 public void reportPageMethod(String reportName,String reportDesc,String reportFile) throws InterruptedException
  {
	//click on reports tab
	// driver.findElement(By.xpath("//input[@value='Reports']")).click();
		 
	//select Appointment dropdown
	// WebElement selectAppt=driver.findElement(By.xpath("//select[@id='app_date' AND @name='app_date']"));
	 WebElement selectAppt=driver.findElement(By.id("app_date"));
	 Select selectApptdrop=new Select(selectAppt);
	 selectApptdrop.selectByIndex(1);
	 
	 //ReportName Textbox
	 driver.findElement(By.xpath("//input[@id='exampleInputcardnumber1']")).clear();
	 driver.findElement(By.xpath("//input[@id='exampleInputcardnumber1']")).sendKeys(reportName);
	 
	 
	// By upload = By.id("file");
	 WebElement file=driver.findElement(By.id("file"));
		file.sendKeys(reportFile);	 	 
	
	// WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
	Thread.sleep(2000);
     //report description
	 driver.findElement(By.xpath("//textarea[@name='report_desc']")).clear();
	 driver.findElement(By.xpath("//textarea[@name='report_desc']")).sendKeys(reportDesc);
	 
	 //submit button
	 driver.findElement(By.xpath("//input[@value='submit']")).click();
 }
}
