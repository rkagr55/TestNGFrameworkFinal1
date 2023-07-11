package tests;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.BaseClass;
import main.Listeners;
import pages.LoginPage;
import pages.MyAccountPage;
import utilities.ExcelUtils;
import utilities.captureSnaps;

public class MyAccountTest extends BaseClass {

	@Test(description="myAccountsectionCount")
	public void TCID3() throws InterruptedException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		title = objPage1.Login(ExcelUtils.getData("TCID1", "EmailID"), ExcelUtils.getData("TCID1", "Password"));
		Assert.assertTrue(title.equalsIgnoreCase("My Account"));
		Listeners.getExtentTest().log(Status.PASS, "User is logged in with valid credentials",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		MyAccountPage objMyAccountPage = new MyAccountPage(getDriver());
		int count = objMyAccountPage.getMyAccountSectionCount();
		Assert.assertTrue(count == Integer.parseInt(ExcelUtils.getData(methodName, "ItemsCount")));
		Listeners.getExtentTest().log(Status.PASS, "My account section having expected count of items under it",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}

	@Test(description="accountsSectionsList")
	public void TCID4() throws InterruptedException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Thread.sleep(5000);
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		title = objPage1.Login(ExcelUtils.getData("TCID1", "EmailID"), ExcelUtils.getData("TCID1", "Password"));
		Assert.assertTrue(title.equalsIgnoreCase("My Account"));
		Listeners.getExtentTest().log(Status.PASS, "User is logged in with valid credentials",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		MyAccountPage objMyAccountPage = new MyAccountPage(getDriver());
		List<String> myAaccountSectionList = objMyAccountPage.getAccountsSectionsList();
		//List<String> expectedList = new ArrayList<String>();
		//Collections.addAll(expectedList, "Edit your account information", "Change your password", "Modify your address book entries", "Modify your wish list");
		
		
		List<String> expectedList = Arrays.asList(ExcelUtils.getData(methodName, "ItemsList").split(","));
		System.out.println(expectedList);
				
		Assert.assertEquals(myAaccountSectionList, expectedList);
		Listeners.getExtentTest().log(Status.PASS, "My section is having expected items list under it",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath(methodName, getDriver())).build());
	}

}