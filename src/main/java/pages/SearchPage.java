package pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import utils.Wait;

public class SearchPage {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="btn_location_close_icon") WebElement enableLocation;
	@FindBy(id="rel_pincode") WebElement givePincode;
	@FindBy(css= "input[class*='aa-Input']") WebElement searchKey;

	public void searchKeyIcon(String item) {
		Wait.waitForVisibility(driver, searchKey);
		searchKey.sendKeys(item);
		searchKey.sendKeys(Keys.ENTER);
	}

	public void givePincode(String pincode) {
		Wait.waitForVisibility(driver, givePincode);
		givePincode.sendKeys(pincode);
		givePincode.sendKeys(Keys.ENTER);
	}
	public void SarchProduct(String pincode, String item, boolean isFirst) {
		  if (isFirst) {
		        enableLocation.click();
		        givePincode(pincode);
		    }
		searchKeyIcon(item);


	}
}
