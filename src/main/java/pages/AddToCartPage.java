package pages;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;
import utils.Wait;



public class AddToCartPage {
	Actions action;
	WebDriver driver;
	static Logger log = LogManager.getLogger(AddToCartPage.class);
	
	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div.plp-card-cart div.product-card-cta") WebElement add;
	@FindBy(id= "btn_minicart") WebElement cart;
	@FindBy(id="minicart_proceed") WebElement processtoCart;
	
	
	public void clickAdd() {
	    Wait.waitForVisibility(driver, add);
	    add.click();
	    Wait.waitForVisibility(driver, cart);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].dispatchEvent(new MouseEvent('mouseover', { bubbles: true }))", cart);
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("minicart_container")));

	
	    wait.until(ExpectedConditions.visibilityOf(processtoCart));
	    processtoCart.click();
	}
	
	
	public String getItemName(String expectedName) {
		String dynamicXpath = "//div[contains(@class,'product-name') and normalize-space(text())='" + expectedName + "']";
		Wait.waitForUrlContains(driver,"/checkout/cart");
		WebElement product = Wait.waitForVisibilityOfXpath(driver, dynamicXpath);
		String actualName = product.getText().trim();
		return actualName;
	}
	
	public String getItemPrice(String expectedPrice) {
		 String dynamicXpath = "//span[starts-with(@id,'row_itmdiscprice_')]";
		WebElement price = Wait.waitForVisibilityOfXpath(driver, dynamicXpath);
		String actualPrice = price.getText().trim();
		return actualPrice;
	}
	
	
	
	
}
