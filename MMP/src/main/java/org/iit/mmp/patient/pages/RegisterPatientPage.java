package org.iit.mmp.patient.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RegisterPatientPage {

	WebDriver driver;
	public RegisterPatientPage(WebDriver driver)
	{
		this.driver= driver;
	}
	public HashMap<String, String> register(String emailID, String userName, String password)
	{	
		HashMap<String,String> hp = null;
		try{
			hp = new HashMap<String,String>();
			
			Thread.sleep(5000);
			
			WebElement licenseTxt= driver.findElement(By.id("license"));
			Random rnd = new Random();
			Integer  n =0;
			
			
			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys(emailID);
			hp.put("email", email.getAttribute("value"));
 	
			WebElement passwordTxt	=driver.findElement(By.id("password"));
			String passwordVal = (char)(65 + rnd.nextInt(26))+password+rnd.nextInt(90);
			passwordTxt.sendKeys(passwordVal);
			hp.put("password", passwordTxt.getAttribute("value"));

			WebElement uname= driver.findElement(By.id("username"));
			uname.sendKeys(userName);
			hp.put("uname", uname.getAttribute("value"));

			WebElement cPassword=driver.findElement(By.id("confirmpassword"));
			cPassword.sendKeys(passwordVal);
			hp.put("cPassword", cPassword.getAttribute("value"));

			new Select(driver.findElement(By.id("security"))).selectByVisibleText("what is your pet name");

			WebElement answerTxt=	driver.findElement(By.id("answer"));
			answerTxt.sendKeys(userName);
			hp.put("answer", answerTxt.getAttribute("value"));


			WebElement fName= driver.findElement(By.id("firstname"));
			fName.sendKeys("testFN"+(char)(65 + rnd.nextInt(26))+(char)(65 + rnd.nextInt(26)));
			hp.put("firstName", fName.getAttribute("value"));

			WebElement lName= driver.findElement(By.id("lastname"));
			lName.sendKeys("testLN");
			hp.put("lastname", lName.getAttribute("value"));

			WebElement datePicker= driver.findElement(By.id("datepicker"));
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			datePicker.sendKeys(sdf.format(new Date()));
			hp.put("datePicker", datePicker.getAttribute("value"));

			//9Characters starting with number value 1
			WebElement ssnTxt= driver.findElement(By.id("ssn"));
			n = 100000000 + rnd.nextInt(900000000);
			ssnTxt.sendKeys(n.toString());
			hp.put("ssn", ssnTxt.getAttribute("value"));


			WebElement age= driver.findElement(By.id("age"));
			age.sendKeys(rnd.nextInt(90)+"");
			hp.put("age", age.getAttribute("value"));

			WebElement height= driver.findElement(By.id("height"));
			height.sendKeys("200");
			hp.put("height", height.getAttribute("value"));

			WebElement weight= driver.findElement(By.id("weight"));
			weight.sendKeys("60");
			hp.put("weight", weight.getAttribute("value"));

			WebElement stateTxt= driver.findElement(By.id("state"));
			stateTxt.sendKeys("New York");
			hp.put("state", stateTxt.getAttribute("value"));

			WebElement city= driver.findElement(By.id("city"));
			city.sendKeys("seattle");
			hp.put("city", city.getAttribute("value"));

			WebElement addressTxt= driver.findElement(By.id("address"));
			addressTxt.sendKeys("9 street");
			hp.put("city", addressTxt.getAttribute("value"));


			WebElement zipcode= driver.findElement(By.id("zipcode"));
			zipcode.sendKeys(10000 + rnd.nextInt(90000)+"");
			hp.put("zipcode", zipcode.getAttribute("value"));

			Thread.sleep(5000);
			
		 
			
			driver.findElement(By.xpath(".//*[@id='container_body']/p/input")).sendKeys(Keys.ENTER);
		
			n = 1000000 + rnd.nextInt(9000000);
			String st = "1"+n;
			System.out.println("license value Entered" + n);
			licenseTxt.sendKeys(st.trim());
			hp.put("license", licenseTxt.getAttribute("value"));
			licenseTxt.click();
			ssnTxt.click();
			
			driver.findElement(By.xpath(".//*[@id='container_body']/p/input")).sendKeys(Keys.ENTER);
			
			hp.put("message",driver.switchTo().alert().getText());
			 
			driver.switchTo().alert().accept();
			
			return hp;
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in registerPatient logic::"+ e.getMessage());
		}
		return hp;

	}
}
