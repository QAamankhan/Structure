package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.ProductResultPage;

public class ProductResultPageValidation extends BaseTest{

	
	private static final Logger log = LogManager.getLogger(ProductResultPage.class);
	
	ProductResultPage pr;
	
	@BeforeMethod(alwaysRun = true)
	public void Object() {
		pr=new ProductResultPage(driver);
	}
	
	
	@Test(groups = {"productResult"})
	public void TC01_ChoseHpLaptop() {
		pr.chooseBrand("hp");
	}

	
	@Test(groups = {"productResult"})
	public void TC02_CheckAllProductIsHp() {
		boolean result=pr.allProductIsHP();
		Assert.assertTrue(result, "Not all Products are HP brand");
	}
	
	@Test(groups = {"productResult"})
	public void TC03_Addproducts() {
		pr.addProductInCart(3);
	}
}
