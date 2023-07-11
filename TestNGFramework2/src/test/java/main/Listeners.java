package main;

import java.util.Iterator;
import org.testng.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Listeners extends BaseClass implements ITestListener {

	ExtentReports extent = ExtentReporterNG.extentReportGenerator();
	ExtentTest test;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {


		if (result.getMethod().getRetryAnalyzer() != null) {
			RetryAnalyzer retryAnalyzer = (RetryAnalyzer) result.getMethod().getRetryAnalyzer();

			if (retryAnalyzer.isRetryAvailable()) { 
			} else {
				extentTest.get().fail(result.getThrowable());
				String path = utilities.captureSnaps.getScreenshotPath(result.getMethod().getMethodName(), getDriver());
				extentTest.get().addScreenCaptureFromPath(path);
			}
	
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
		extentTest.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {

		Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
		while (skippedTestCases.hasNext()) {

			ITestResult skippedTestCase = skippedTestCases.next();
			ITestNGMethod method = skippedTestCase.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				System.out.println("failed test case remove as dup:" + skippedTestCase.getTestClass().toString());
				skippedTestCases.remove();
			} else {

				if (context.getPassedTests().getResults(method).size() > 0) {
					System.out.println(
							"failed test case remove as pass retry:" + skippedTestCase.getTestClass().toString());
					skippedTestCases.remove();
				}
			}
		}
		
		

		extent.flush();
	}

	public static synchronized ExtentTest getExtentTest() {
		return extentTest.get();
	}
}