package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

	static ExtentReports extent;

	public static ExtentReports extentReportGenerator() {

		ExtentSparkReporter reporter = new ExtentSparkReporter("ExtentReports/extentreport_"
				+ new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss_SSS").format(new Date()) + ".html"); 
		reporter.config().setDocumentTitle("DocumentTitle_01");
		reporter.config().setReportName("ReportName_01");
		reporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("OS", "OS_Windows 10");
		extent.setSystemInfo("Environment", "Env_QA");
		return extent;

	}

}
