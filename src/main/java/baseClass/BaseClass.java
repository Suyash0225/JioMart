package baseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtendReportClass;

public class BaseClass {
	protected static WebDriver driver;
	Properties properties;
	public static ExtentReports extent;
   public static ExtentTest test;

	@BeforeSuite
	public void extednreport() {
		
		 extent = ExtendReportClass.setupReport(); 
	        System.out.println("Extent Report initialized from manager.");
		
		properties= new Properties();
		String path = "C:\\Users\\Suyash\\eclipse-workspace\\JioMart\\src\\main\\resources\\Configure.properties";
		try {
			FileInputStream file = new FileInputStream(path);
			try {
				properties.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}





	@BeforeMethod
	public void setup(Method method) {
		// test = extent.createTest(method.getName());
		String browser= properties.getProperty("browser");
		String url = properties.getProperty("url");
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--incognito");
			driver = new ChromeDriver(option);
		} else {
			throw new RuntimeException("Only Chrome supported in this simple setup");
		}

		driver.manage().window().maximize();
		driver.get(url);

	}

	public String navigate(String key) {
		String url = properties.getProperty(key);
		if(url==null) {
			throw new RuntimeException("Missing key"+ key+"in globale properties");
		}
		driver.get(url);
		return url;

	}

	@AfterMethod
	public void closeBrowser() {
		System.out.println(" Browser running");
	        if (driver != null) {
	            driver.quit();
	            System.out.println(" Browser quit successfully");
	        }
	  
	}
	
	
	
	
	   @AfterSuite
	    public void tearDownReport() {
	        if (extent != null) {
	            extent.flush();
	            System.out.println("Extent report flushed and saved.");
	        }
	    }


}
