package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.BaseClass;


public class LoginPage extends BaseClass {

	private WebDriver driver;
	
	@FindBy(id="input-email") private WebElement loginPageEmailID;
	@FindBy(id="input-password") private WebElement loginPagePassword;
	@FindBy(xpath="//input[@type='submit']") private WebElement loginPageSubmitButton;
	@FindBy(linkText = "Forgotten Password")private WebElement forgetPasswordLink;
	@FindBy(xpath="//div[@class = 'alert alert-success alert-dismissible']") private WebElement resetPasswordSuccessMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String launchBrowser() throws InterruptedException {
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		Thread.sleep(10000);
		return driver.getTitle();
	}

	public boolean isforgetPasswordLinkDisplayed() {
		return driver.findElement(By.linkText("Forgotten Password")).isDisplayed();
	}

	public String Login(String un, String pw) {
		loginPageEmailID.sendKeys(un);
		loginPagePassword.sendKeys(pw);
		loginPageSubmitButton.click();
		return driver.getTitle();
	}

	public String navigateToResetPasswordPage() {
		forgetPasswordLink.click();
		return driver.getTitle();
	}

	public String getSuccessMessageResetPassword() {
		String message = resetPasswordSuccessMessage.getText();
		System.out.println(message);
		return message;
	}

}
