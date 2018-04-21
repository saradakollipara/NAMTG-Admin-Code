package org.iit.mmp.patient.tests;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.iit.mmp.admin.helper.AdminLoginPage;
import org.iit.mmp.admin.pages.AdminUsersPage;
import org.iit.mmp.patient.helper.PatientLoginPage;
import org.iit.mmp.patient.pages.RegisterPatientPage;
import org.iit.mmp.util.HomePage;
import org.iit.mmp.util.TestBaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PatientTests extends TestBaseClass{

	//Fixing the scripts
	Logger logger = Logger.getLogger("PatientTests.class");
	HashMap<String,String> hMap;
	Random rnd = new Random();
	String username = "test"+rnd.nextInt(90);
	String password = "test"+rnd.nextInt(90);
 
 
	String email = "test"+rnd.nextInt(9000)+"@gmail.com";
 
	@BeforeMethod
	public void beforeMethod()
	{
		logger.info("##########Test Execution Started#########" );
	}
	@Test(priority = 1,enabled=false)
	public void verifyPatientLogin() throws InterruptedException
	{ 
		driver.get(url);
		HomePage hPage = new HomePage(driver);
		hPage.navigateToPatLoginPage();
		PatientLoginPage loginPage = new PatientLoginPage(driver);
		boolean result = loginPage.login("ria1","Ria12345");
		Assert.assertTrue(result);


	}
	@Test(priority = 2,enabled=false)
	public void registerPatient() throws InterruptedException
	{
		try{
			driver.get(url);
			HomePage hPage = new HomePage(driver);
			hPage.navigateToPatientRegPage();
			RegisterPatientPage regPage = new RegisterPatientPage(driver);
			username = "test"+rnd.nextInt(90);
			password = "test";
			email = "test"+rnd.nextInt(90)+"@gmail.com";
			hMap = regPage.register(email,username,password);
			
//			ArrayList<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
//			aList.add(hMap);
			
			Set<String> set= hMap.keySet(); 
			Iterator<String> it = set.iterator();
			while(it.hasNext())
			{
				String key = it.next();
				System.out.println("Key:::" + key + "value::: " + hMap.get(key));

			}
			Assert.assertTrue(hMap.get("message").contains("Thank you for registering with MMP."));

		}
		catch(Exception e)
		{
			Assert.fail("Registration Failed!!!!");
			System.out.println(e.getMessage());
		}
	}
	@Parameters({"adminUsername","adminPassword","adminUrl","url"})
	@Test(priority = 3)
	public void approvePatient(String adminUsername,String adminPassword,String adminUrl,String url) throws InterruptedException
	{
		try{
			driver.get(url);
			HomePage hPage = new HomePage(driver);
			hPage.navigateToPatientRegPage();
			RegisterPatientPage regPage = new RegisterPatientPage(driver);
			username = "test"+rnd.nextInt(90);
			password = "test"+rnd.nextInt(90);
			email = "test"+rnd.nextInt(90)+"@gmail.com";
			hMap = regPage.register(email,username,password);
			Set<String> set= hMap.keySet(); 
			Iterator<String> it = set.iterator();
			while(it.hasNext())
			{
				String key = it.next();
				System.out.println("Key:::" + key + "value::: " + hMap.get(key));
			}
			Assert.assertTrue(hMap.get("message").contains("Thank you for registering with MMP."));
			driver.navigate().to(adminUrl);
			AdminLoginPage  adminLogin = new AdminLoginPage(driver);
			adminLogin.loginToAdmin(adminUsername, adminPassword);
			AdminUsersPage  userPage =new AdminUsersPage(driver);
			String  result =userPage.getApprovedAdminModule(hMap.get("firstName"),"Pending","Accepted");
			System.out.println("Result :: " + result);
			driver.navigate().to(url);
			hPage = new HomePage(driver);
			hPage.navigateToPatLoginPage();
			PatientLoginPage loginPage = new PatientLoginPage(driver);
			boolean result1 = loginPage.login(username,password);
			Assert.assertTrue(result1);


		}
		catch(Exception e)
		{
			Assert.fail("Registration Failed!!!!");
			System.out.println(e.getMessage());
		}
	}

}
