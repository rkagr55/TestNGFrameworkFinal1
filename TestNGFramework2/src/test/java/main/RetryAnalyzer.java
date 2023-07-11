package main;

import java.util.concurrent.atomic.AtomicInteger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static int MAX_RETRY_COUNT = 1;

	AtomicInteger count = new AtomicInteger(MAX_RETRY_COUNT);

	public boolean isRetryAvailable() {
	    return (count.intValue() > 0);
	}

	@Override
	public boolean retry(ITestResult result) {
	    boolean retry = false;
	    if (isRetryAvailable()) {
	        System.out.println("Going to retry test case: " + result.getMethod() + ", " + (MAX_RETRY_COUNT - count.intValue() + 1) + " out of " + MAX_RETRY_COUNT);
	        retry = true;
	        count.decrementAndGet();
	    }
	    return retry;
	}
}
