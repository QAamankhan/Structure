package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;

public class HomePageValidation extends BaseTest{

	HomePage hp;
	private static final Logger log = LogManager.getLogger(HomePage.class);

	
	@BeforeMethod(alwaysRun = true)
	public void Object() {
		hp=new HomePage(driver);
	}
	
	
	@Test(groups = {"homepage"})
	public void TC01_ClcikOnLangaugeLogo() {
		hp.ClickOnLogoChangeLangauge();
		boolean result=hp.NavigateLangaugePage();
		
		Assert.assertEquals(result, true);
	}
	
	@Test(groups = {"homepage"})
	public void TC02_ClickOnLangaugebtn() throws Exception {
		hp.selectLanguage("HI");
		boolean result=hp.isLanguageChanged();
		Assert.assertEquals(result, true);
	}
	
	@Test(groups = {"homepage","productResult"})
	public void TC03_SerachProduct() {
		hp.SearchProduct("laptop");
		boolean result =hp.IsNavigateProductPage();
		Assert.assertEquals(result, true);
		
	}
}
