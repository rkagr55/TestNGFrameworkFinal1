package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class ResetPasswordPage {

	private WebDriver driver;
	@FindBy(id ="input-email")private WebElement resetPasswordPageEmailID;
	@FindBy(xpath ="//input[@type='submit']")private WebElement resetPasswordPageSubmitButton;

	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Test
	public String resetPassword(String EmailID) {
		resetPasswordPageEmailID.sendKeys(EmailID);
		resetPasswordPageSubmitButton.click();
		return driver.getTitle();

	}
}
