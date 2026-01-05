package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BaseClass;

public class CartPage extends BaseClass {

	private static final Logger log = LogManager.getLogger(CartPage.class);

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "nav-cart-count")
	private WebElement cartitemElement;

	public void IsCartHaveProduct() {
		ElementVisible(cartitemElement);
		cartitemElement.click();
		String itemcount = cartitemElement.getText();
		int item = Integer.parseInt(itemcount);
		if (item != 0) {
			log.info("Nuber of product in cart : " + item);
		} else {
			log.info("Cart is empty");
		}
	}

	public boolean deleteItemFromCart() {
		
		cartitemElement.click();
		try {
			String itemCountText = cartitemElement.getText();
			int itemCount = Integer.parseInt(itemCountText.replaceAll("\\D+", ""));
			log.info("Total items in cart: " + itemCount);
			while (itemCount > 0) {
				WebElement decrementBtn = driver.findElement(By.xpath("//span[@data-a-selector='decrement-icon']"));
				decrementBtn.click();
				itemCount--;

				log.info("Remaining items: " + itemCount);
				Thread.sleep(1000); // better: use WebDriverWait
			}

			return true;

		} catch (Exception e) {
			log.error("Failed to delete items from cart", e);
			return false;
		}

	}

}
