package org.iit.mmp.admin.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AdminPatientsPage {



	WebDriver driver;


	public AdminPatientsPage(WebDriver driver){

		this.driver = driver;
	}

	public void scheduleAppointment(String doctorName,String apptDate,String apptTime,String symptoms) throws InterruptedException
	{
		try {
			driver.findElement(By.xpath("//input[@value='Create Visit']")).click();  
			WebElement docName = driver.findElement(By.xpath("//h4[contains(text(),'"+doctorName+"')]/ancestor::ul/following-sibling::button"));
			docName.click();
		} 
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in entering username" + e.getMessage());
		}

		try {
			//to change frame:
			driver.switchTo().frame("myframe"); 
			Thread.sleep(2000);  

			driver.findElement(By.id("datepicker")).click(); 

			String day,month,year;
			String date[] = apptDate.split("/");//split date with delimeter 

			month=date[0];
			day = date[1];
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

			calMonth= driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			System.out.println(calMonth);
			System.out.println(month);
			while(!calMonth.equals(month))
			{
				driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
				calMonth=driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/div/span[1]")).getText();
				System.out.println("While Month printing month::"+ calMonth);
			}

			List<WebElement> tdList = driver.findElements(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr/td/"));
			System.out.println(tdList);

			for(int i=0;i<tdList.size();i++)
			{

				WebElement e = tdList.get(i);
				if(e.getText().equals(day))
				{
					System.out.println("Date matching::"+  e.getText());
					e.click();
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in entering username" + e.getMessage());
		}
		WebElement timeWE = driver.findElement(By.id("time"));
		Select sel = new Select(timeWE);
		List<WebElement> optionList = sel.getOptions();
		System.out.println("List size::::::" + optionList.size());

		for (int i = 0; i < optionList.size(); i++) {
			if (optionList.get(i).getText().equalsIgnoreCase(apptTime)) {
				optionList.get(i).click();
				System.out.println("Time Selected");
				break;
			}
		}	 
		driver.findElement(By.id("ChangeHeatName")).click();

		driver.switchTo().defaultContent();
		driver.findElement(By.name("sym")).clear();
		driver.findElement(By.name("sym")).sendKeys(symptoms);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		System.out.println("Appointment Scheduled");
		
	}





	/*driver.switchTo().frame("myframe");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		//WebElement monthWE = driver.findElement(By.className("ui-datepicker-year"));
		String dt,month,year;
		String []date1 = apptDate.split("/");
		dt = date1[0];
		month=date1[1];
		year = date1[2];

		String calYear,calMonth;

		calYear = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
		System.out.println(calYear);
		System.out.println(year);
		while(!calYear.equals(year))
		{
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			calYear = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]")).getText();
			System.out.println("While Loop printing year::"+ calYear);
		}


		calMonth= driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
		System.out.println(calMonth);
		System.out.println(month);
		while(!calMonth.equals(month))
		{
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			calMonth= driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText();
			System.out.println("While Month printing month::"+ calMonth);
		}

		List<WebElement> tdList = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td"));
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
		String dateSelected=driver.findElement(By.xpath("//input[@id='datepicker']")).getText();
		System.out.println("Date selected for appontment scheduling"+dateSelected);
	}	*/

}


