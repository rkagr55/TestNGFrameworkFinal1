package main;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriverWait wait;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	  public static final String USERNAME = "oauth-rkagr55-da508";
	  public static final String ACCESS_KEY = "24b6b221-2ba2-4171-9b83-48e6c4e2e488";
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	@BeforeMethod
	@Parameters("browser")
	public WebDriver beforeMethod(String browser) throws MalformedURLException {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			   DesiredCapabilities caps = DesiredCapabilities.chrome();
			    caps.setCapability("platform", "Windows 10");
			    caps.setCapability("version", "latest");
			tlDriver.set(new RemoteWebDriver(new URL(URL), caps));
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wait = new WebDriverWait(getDriver(), 120); // 2 mins
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	@AfterMethod
	public void afterMethod() {
		getDriver().close();
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		FileInputStream f = new FileInputStream("TestData.xlsx");
		wb = new XSSFWorkbook(f);
		sh = wb.getSheet("Sheet1");		
	}
}

