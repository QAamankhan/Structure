package pages;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BaseClass;
import io.opentelemetry.sdk.metrics.internal.concurrent.AdderUtil;

public class ProductResultPage extends BaseClass {

	private static final Logger log = LogManager.getLogger(ProductResultPage.class);

	public ProductResultPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[@id='a-autoid-0-announce']")
	private WebElement featuredElement;
	@FindBy(xpath = "//li[.='Best Sellers']")
	private WebElement bestsellerElement;
	@FindBy(xpath = "//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base']")
	private List<WebElement> brandsElements;

	public void chooseBrand(String brandName) {

		ElementClickable(featuredElement);

		ElementVisible(bestsellerElement);
		ElementClickable(bestsellerElement);

		log.info("Selecting brand: " + brandName);

		boolean isBrandFound = false;

		for (WebElement brand : brandsElements) {
			String name = brand.getText().trim();

			if (name.equalsIgnoreCase(brandName)) {
				brand.click();
				log.info("Brand selected successfully: " + brandName);
				isBrandFound = true;
				break;
			}
		}

		if (!isBrandFound) {
			log.warn("Brand not found: " + brandName);
		}

	}

	@FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']//h2/span")
	private List<WebElement> displaytextElements;

	public boolean allProductIsHP() {

		for (WebElement product : displaytextElements) {
			String productName = product.getText().toLowerCase();
			if (!productName.contains("hp")) {
				log.info("This is NOT HP brand: " + productName);
				return false;
			}
		}
		log.info("All products are HP brand");
		return true;
	}

	@FindBy(xpath = "//button[@name='submit.addToCart']")
	private List<WebElement> addtocartElements;

	public void addProductInCart(int count) {

	    int productsToAdd = Math.min(count, addtocartElements.size());

	    for (int i = 0; i < productsToAdd; i++) {
	        log.info("Adding product " + (i + 1));
	        addtocartElements.get(i).click();
	    }
	}
}
