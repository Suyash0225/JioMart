package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pages.AddToCartPage;
import pages.SearchPage;

public class AddToCartTest extends BaseClass{
    @Test
	public void addtoCart() throws InterruptedException {
		SearchPage search = new SearchPage(driver);
		search.SarchProduct("221001", "Rice", true);
		AddToCartPage cart = new AddToCartPage(driver);
		cart.clickAdd();
		String expectedName = "Good Life Biryani Basmati Rice 1 kg";
		String actualName = cart.getItemName(expectedName);
		Assert.assertEquals(actualName, expectedName, "Product name mismatch!");
		String expectedPrice = "₹105.00";
		String actualPrice= cart.getItemPrice(expectedPrice);
		Assert.assertEquals(actualPrice, expectedPrice, "Price not match");
	}
	
	
}
