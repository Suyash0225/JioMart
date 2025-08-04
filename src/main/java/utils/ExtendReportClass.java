package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportClass  {
	
	public static ExtentReports setupReport() {
		String path = System.getProperty("user.dir")+ "/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("JioMart");
		reporter.config().setDocumentTitle("Test Report");
		ExtentReports extent =  new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA Engineer", "Suyash");
		return extent;
	}


}
