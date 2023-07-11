package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.BaseClass;
import main.Listeners;
import pages.LoginPage;
import utilities.ExcelUtils;
import utilities.captureSnaps;

public class LoginTest extends BaseClass {

	
	@Test(description="validLogin")
	public void TCID1() throws InterruptedException {
		
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();		
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		title = objPage1.Login(ExcelUtils.getData(methodName, "EmailID"), ExcelUtils.getData(methodName, "Password"));
		Assert.assertTrue(title.equalsIgnoreCase("My Account"));
		Listeners.getExtentTest().log(Status.PASS, "User is logged in with valid credentials",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}

	@Test(description="invalidLogin")
	public void TCID2() throws InterruptedException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath(methodName, getDriver())).build());
		title = objPage1.Login(ExcelUtils.getData(methodName, "EmailID"), ExcelUtils.getData(methodName, "Password"));
		
		Assert.assertTrue(title.equalsIgnoreCase("Account Loginn"));

		Listeners.getExtentTest().log(Status.PASS, "User is not logged in with invalid credentials", MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}	
}