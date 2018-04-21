package org.iit.mmp.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateVisitPage {
	WebDriver driver;
	
	public CreateVisitPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickCreateVisit() {
		//clicking on create visit button
		driver.findElement(By.xpath("//input[@value='Create Visit']")).click();
		
	}


	public void schedulevisit(String doj, String expTime, String doctorName,String  symptoms) throws InterruptedException  {

		driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button")).click();
		//to change frame:

			driver.switchTo().frame("myframe"); 
			 Thread.sleep(2000);  
			
			driver.findElement(By.id("datepicker")).click(); 
			
		//String doj;
		String dt,month,year;
		String []date = doj.split("/");//delimeter /
		
		month=date[0];
		dt = date[1];
		year = date[2];
		
		String calYear,calMonth;
		calYear = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
		System.out.println(calYear);
		System.out.println(year);
		while(!calYear.equals(year))
		{
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
			calYear= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span[2]"))
					.getText();
			System.out.println("While Loop printing year::"+ calYear);
		}
		 
		calMonth= driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
		System.out.println(calMonth);
		System.out.println(month);
		while(!calMonth.equals(month))
		{
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
			calMonth=driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
			System.out.println("While Month printing month::"+ calMonth);
		}
		
		List<WebElement> tdList = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"));
		System.out.println(tdList);
		
		for(int i=0;i<tdList.size();i++)
		{
			
			WebElement e = tdList.get(i);
			if(e.getText().equals(dt))
			{
				System.out.println("Date matching::"+  e.getText());
				e.click();
				break;
			}
		}
		
		 WebElement k = driver.findElement(By.id("time"));
		    Select sel = new Select(k);
		    //sel.selectByIndex(1);
	       // String expTime="11Am";
		    List<WebElement> optionList = sel.getOptions();
		    System.out.println("List size::::::" + optionList.size());

		    for (int i = 0; i < optionList.size(); i++) {
		        if (optionList.get(i).getText().equalsIgnoreCase(expTime)) {
		            optionList.get(i).click();
		            System.out.println("Time Selected");
		            break;
		        }
		    }	
		    Thread.sleep(5000);
		    driver.findElement(By.id("ChangeHeatName")).click();
		    
		    driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div/form/textarea")).clear();
		    driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div/form/textarea")).sendKeys(symptoms);
			//driver.switchTo().defaultContent();
			//driver.findElement(By.id("sym")).sendKeys(symptoms);
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
			System.out.println("Appointment Scheduled");
	}
}
