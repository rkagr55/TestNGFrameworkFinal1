package utilities;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class captureSnaps {

	public static String getScreenshotPath(String TestCaseName, WebDriver driver) {
		File destinationPath = null;
		File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir") + "/Screenshots/screenshot_" + TestCaseName + "_"
				+ new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_SSSSSS").format(new Date()) + ".png";

		File file = new File(destPath);
		try {
			FileHandler.copy(sourcePath, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}
}