//Code edited on 4/21/2018

package org.iit.mmp.admin.tests;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.iit.mmp.admin.helper.AdminLoginPage;
import org.iit.mmp.admin.helper.AdminNavigationPage;
import org.iit.mmp.admin.pages.AddPrescriptionPage;
import org.iit.mmp.admin.pages.AdminClaimPage;
import org.iit.mmp.admin.pages.AdminPatientsPage;
import org.iit.mmp.admin.pages.AdminUsersPage;
import org.iit.mmp.admin.pages.CreateFeePage;
import org.iit.mmp.admin.pages.CreateVisitPage;
import org.iit.mmp.admin.pages.ReportsPage;
import org.iit.mmp.patient.pages.RegisterPatientPage;
import org.iit.mmp.util.HomePage;
import org.iit.mmp.util.TestBaseClass;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminTests  extends TestBaseClass{

	Logger logger = Logger.getLogger("AdminTests.class");
	HashMap<String,String> hMap;
	Random rnd = new Random(); 

	@Parameters({"adminUsername","adminPassword","adminUrl"})
	@BeforeMethod(description="Validate Admin Login to the Application")
	public void setUp(String adminUsername,String adminPassword,String adminUrl)
	{
		driver.get(adminUrl);
		logger.info("Browser Opened Successfully");	
		AdminLoginPage  adminLogin = new AdminLoginPage(driver);
		AdminNavigationPage adminNav = new AdminNavigationPage(driver);

		adminLogin.loginToAdmin(adminUsername, adminPassword);
		//Boolean status = adminNav.validateAdminPage();
		Assert.assertTrue(adminNav.validateAdminPage());
		//Assert.assertEquals(status, true, "message");
		//Assert.assertEquals(status, true, "Login to admin failed");
		logger.info("Admin Login Successful");	

	}


	/* DesiredCapabilities capabilities=DesiredCapabilities.chrome(); 
	  LoggingPreferences logginPreferences=new LoggingPreferences();
	  logginPreferences.enable(LogType.BROWSER, Level.ALL);
	  capabilities.setCapability(CapabilityType.LOGGING_PREFS, logginPreferences);		  
	 }
	public void extractJSLogsInfo()
	{
		LogEntries logEntries=driver.manage().logs().get(LogType.BROWSER);
		for(LogEntry entry:logEntries) {
			System.out.println(new Date(entry.getTimestamp())+" "+entry.getLevel()+" "+entry.getMessage());
		}
	//}
	 */	
	public void searchPatient(String ssn,String patFName) {
		AdminNavigationPage adminNav = new AdminNavigationPage(driver);
		AdminClaimPage adminClaim = new AdminClaimPage(driver);

		adminNav.clickPatientsLink();
		adminClaim.searchBySSN(ssn);
		logger.info("Search By SSN :: " + ssn);
		adminClaim.clickPatientUsingFName(patFName);


	}

	@Parameters({"ssn","patFName"})//,"doctorName","appointmentDate","appointmentTime","symptoms"})
	@Test(description="Schedule an appointment with Doctor", priority=1)
	public void validateSubmitClaimTest(String ssn,String patFName) throws InterruptedException//,String doctorName,String apptDate,String appointmentTime,String symptoms) throws InterruptedException
	{
		SoftAssert sa = new SoftAssert();
		try {
			
			AdminClaimPage adminClaim = new AdminClaimPage(driver);
			//AdminPatientsPage adminPatients = new AdminPatientsPage(driver);
			AdminNavigationPage adminNav = new AdminNavigationPage(driver);
			
			adminNav.clickClaimCenterLink();
			
			logger.info("Clicking on Claim Center Tab");
			adminClaim.searchBySSN(ssn);
			
			sa.assertTrue(adminClaim.clickPatientUsingFName(patFName),"Error in finding Patien Name");
			logger.info("Clicking the Patient FirtName :: " + patFName);

			String actual = adminClaim.submitClaim();

			sa.assertTrue(actual.contains("submitted"),"Error in submitting the claim");
			sa.assertAll();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in submitting the claim" + e.getMessage());
			Assert.fail("Unable to Submit the Claim");

		}
		sa.assertAll();

		//adminPatients.scheduleAppointment(doctorName,TestBaseClass.prop.getProperty("appointmentDate"),appointmentTime,symptoms);
		//logger.info("Scheduled appointment for Patient FirtName :: " + patFName);
	}

	/*@Parameters({"adminUsername","adminPassword","adminUrl","url"})
	@Test(priority=3,enabled=false)
	public void validateSubmitClaim(String adminUsername,String adminPassword,String adminUrl,String url) throws InterruptedException
	{
		SoftAssert sa ;
		try{
			sa = new SoftAssert();

			HomePage hPage = new HomePage(driver);
			//RegisterPatientPage regPage = new RegisterPatientPage(driver);
			AdminLoginPage  adminLogin = new AdminLoginPage(driver);			
			AdminNavigationPage adminNav = new AdminNavigationPage(driver);

			//AdminUsersPage  userPage =new AdminUsersPage(driver);
			AdminClaimPage adminClaim = new AdminClaimPage(driver);

			//			Open MMP Url
			//			driver.get(url);
			//			
			//			Register the Patient
			//			hPage.navigateToPatientRegPage();
			//			String username = "test"+rnd.nextInt(90);
			//			String password = "test"+rnd.nextInt(90);
			//			Long  emailID = Calendar.getInstance().getTimeInMillis()/100000+ rnd.nextInt(90);
			//			String email = "test"+emailID+"@gmail.com";
			//			
			//			hMap = regPage.register(email,username,password);
			//			sa.assertTrue(hMap.get("message").contains("Thank you for registering with MMP."),"Error in registering the Patient");
			//			
			//Fetch SSN
			//			String ssn = hMap.get("ssn");
			//			String fName = hMap.get("firstName");
			String ssn ="960184043";
			String fName ="testFNFI";

			   Navigate to Admin Url
					1. login to Admin moule
					2. Select Approve 
					3. SSN Verify the table contains the SSN
					4. Navigate to ClaimCenter. Enter SSN.
					5. Click on Patient NAme.
					6. Submit The claim.	

			driver.navigate().to(adminUrl);

			//login to admin portal
			adminLogin.loginToAdmin(adminUsername, adminPassword);

			//Accept the Patient and select Approved status
		//	adminNav.clickUserLink();
			//String  result =userPage.getApprovedAdminModule(fName,"Pending","Accepted");
			//System.out.println("Result :: " + result);
			//sa.assertTrue(result.contains("updated"),"Error in login for accepted patient");

			//Submit the Claim
			adminNav.clickClaimCenterLink();
			adminClaim.searchBySSN(ssn);
			adminClaim.clickPatientUsingFName(fName);

			String actual = adminClaim.submitClaim();

			sa.assertTrue(actual.contains("submitted"),"Error in submitting the claim");
			sa.assertAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error in submitting the claim" + e.getMessage());
			Assert.fail("Unable to Submit the Claim");

		}
		//extractJSLogsInfo();

	}*/

	/*@AfterMethod
	 public void tearDown()
	 {
	  driver.quit();
	 }*/
	@Parameters({"ssn","patFName","doj","expTime","doctorName","symptoms"})
	@Test(priority=2,description="Create Patient Visit")
	public void createPatientvisitTest(String ssn,String patFName,String doj, String expTime,String doctorName, String  symptoms) throws InterruptedException
	{ 
		searchPatient(ssn,patFName);		
		CreateVisitPage visit=new CreateVisitPage(driver);
		Thread.sleep(2000);
		visit.clickCreateVisit();
		visit.schedulevisit(doj, expTime,doctorName, symptoms);
	}


	@Parameters({"prescriptionname", "presdescription","ssn","patFName"})
	@Test(priority=3,description="Add Prescription to the patient")
	public void addPresdescriptionTest(String prescriptionname,String presdescription,String ssn,String patFName) throws InterruptedException {
		
		searchPatient(ssn,patFName);	
		AddPrescriptionPage pres=new AddPrescriptionPage(driver);
		String actual=pres.addPrescriptionMethod(prescriptionname, presdescription);
		Assert.assertEquals("Prescription has been Added.", actual);
	}
	
	
	@Parameters({"ssn","patFName"})
	@Test(priority=4)
	public void createFeeMethodTest(String ssn,String patFName) throws InterruptedException
	{
		searchPatient(ssn,patFName);	
		CreateFeePage fee=new CreateFeePage(driver);
		String actual=fee.createFeeMethod();
		Assert.assertEquals("Fee Successfully Entered. ",actual);
	}
	
	
	@Parameters({"reportName","reportDesc","ssn","patFName","reportFile"})
	@Test(priority=5)
	public void reportPageMethodTest(String reportName,String reportDesc,String ssn,String patFName,String reportFile) throws InterruptedException
	{
		searchPatient(ssn,patFName);	
		
		ReportsPage report=new ReportsPage(driver);
		report.reportTabClick();
		report.reportPageMethod(reportName,reportDesc,reportFile);
	}


}
