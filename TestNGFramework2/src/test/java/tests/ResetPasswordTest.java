package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.BaseClass;
import main.Listeners;
import pages.LoginPage;
import pages.ResetPasswordPage;
import utilities.captureSnaps;

public class ResetPasswordTest extends BaseClass {

	@Test(description="isForgetPasswordDisplayed")
	public void TCID5() throws InterruptedException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		boolean forgetPasswordLinkDisplayed = objPage1.isforgetPasswordLinkDisplayed();
		Assert.assertTrue(forgetPasswordLinkDisplayed);
		Listeners.getExtentTest().log(Status.PASS, "Forgotten Password link is displayed on login page",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}

	@Test(description="navigateTorRsetPassword")
	public void TCID6() throws InterruptedException{
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Login page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		title = objPage1.navigateToResetPasswordPage();
		Assert.assertTrue(title.equalsIgnoreCase("Forgot Your Password?"));
		Listeners.getExtentTest().log(Status.PASS, "Password reset page is displayed",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}

	@Test(description="doResetPassword")
	public void TCID7() throws InterruptedException {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName(); 
		LoginPage objPage1 = new LoginPage(getDriver());
		String title = objPage1.launchBrowser();
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "URL is launched");

		title = objPage1.navigateToResetPasswordPage();
		Assert.assertTrue(title.equalsIgnoreCase("Forgot Your Password?"));
		Listeners.getExtentTest().log(Status.PASS, "Password reset page is displayed");
		
		ResetPasswordPage objPage3 = new ResetPasswordPage(getDriver());
		title = objPage3.resetPassword("rkagr55@gmail.com");
		Assert.assertTrue(title.equalsIgnoreCase("Account Login"));
		Listeners.getExtentTest().log(Status.PASS, "Password reset done and user navigated back to login page",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
		
		String message = objPage1.getSuccessMessageResetPassword();
		Assert.assertTrue(message.equalsIgnoreCase("An email with a confirmation link has been sent your email address."));
		Listeners.getExtentTest().log(Status.PASS, "Password reset success message is displayed on login page",MediaEntityBuilder.createScreenCaptureFromPath(captureSnaps.getScreenshotPath( methodName, getDriver())).build());
	}

}
