package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;

public class CartPageValidation extends BaseTest {
  
	
	private static final Logger log = LogManager.getLogger(CartPage.class);

	CartPage cp;
	
	@BeforeMethod(alwaysRun = true)
	public void Object() {
		cp= new CartPage(driver);
	}
	
	@Test(groups = {"cart","regression"},dependsOnMethods = "testCases.ProductResultPageValidation.TC03_Addproducts")
	public void TC01_CartIteamCount() {
		cp.IsCartHaveProduct();
	}
	
	@Test(groups = {"cart","regression"})
	public void TC02_deleItem() {
		boolean result=cp.deleteItemFromCart();
		Assert.assertTrue(result, "Item not present in cart");
	}
}
