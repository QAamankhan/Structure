package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

    private int retryCount = 0;
    private int maxRetryCount = 2; // number of retries

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying test " + result.getName() + " for " + retryCount + " time(s).");
            return true; // retry test
        }
        return false; // stop retrying
    }
}
