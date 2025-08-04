package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

import baseClass.BaseClass;

public class ExtentTestNGITestListener extends BaseClass implements ITestListener {



	 @Override
	    public void onTestStart(ITestResult result) {
	        test = extent.createTest(result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "✅ Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.log(Status.FAIL, "❌ Test Failed");
	        test.log(Status.FAIL, result.getThrowable());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.log(Status.SKIP, "⚠️ Test Skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        if (extent != null) {
	            extent.flush();  // Flush report only once
	        }
	    }
}
