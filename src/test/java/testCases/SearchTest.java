package testCases;

import java.util.List;
import org.openqa.selenium.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.SearchPage;
import utils.Wait;

public class SearchTest extends BaseClass{
	static Logger log = LogManager.getLogger(SearchTest.class);

	@Test(dataProvider= "searchProduct")
	public void searchItem(String pincode, String product, boolean isFirst) throws InterruptedException {
		log.info("Starting test for product: " + product + " with pincode: " + pincode);
		SearchPage page = new SearchPage(driver);
		navigate("url");
		page.SarchProduct(pincode, product, isFirst);
		log.info("Searched for product: " + product);
		By resultItemLocator = By.cssSelector("li.ais-InfiniteHits-item.jm-col-4.jm-mt-base");

		try {
			Wait.waitForVisibility(driver, resultItemLocator);
		}
		catch(TimeoutException e) {
			log.warn("No search results container found for: " + product);
			Assert.fail("No results found for: " + product);
		}

		List<WebElement> results = driver.findElements(resultItemLocator);
		// System.out.println("Total products found: " + results.size());
		log.info("Total products found: " + results.size());
		boolean atLeastOneMatch = false;
		for (WebElement item : results) {
			String title = item.getText().toLowerCase();
			log.debug("Product title: " + title);
			if (title.contains(product.toLowerCase())) {
				log.info("Matching product found: " + title);
				atLeastOneMatch = true;
				break;
			}
		}
		Assert.assertTrue(atLeastOneMatch, "No search result contains the word '"+product+"'");
	}


	@DataProvider
	public Object[][] searchProduct() {
		return new Object[][] {
			{"221001", "Rice", true},     //valid test 
		    {"221001","Milk", false},      //valid test 
			{"221001","sdkjfvn", false},   //invalid test
			{"221001","", false}           //invalid test
		};

	}

}
