package pages;

import java.security.Key;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;

public class HomePage extends BaseClass {

	private static final Logger log = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[contains(text(),'EN')]")
	private WebElement langlogoElement;

	@FindBy(id = "icp-language-heading")
	private WebElement langsettingElement;

	public void ClickOnLogoChangeLangauge() {
		log.info("CLICK ON LANGAUGE LOGO");
		ElementVisible(langlogoElement);
		ElementClickable(langlogoElement);
	}

	public boolean NavigateLangaugePage() {
		if (langsettingElement.isDisplayed()) {
			log.info("Successfully navigate langauge page");
			return true;
		} else {
			log.info("Something Worng user is not able to navigate langauge page ");
			return false;
		}
	}

	@FindBy(xpath = "//div[@class='a-row a-spacing-mini']//child::span/span")
	private List<WebElement> listOfLangElement;
	@FindBy(xpath = "//span[@id='icp-save-button']")
	private WebElement langchangebtnElement;
	@FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
	private WebElement hindilangElement;

	public void selectLanguage(String lang) {
		log.info("User wants to change language to: " + lang);
		boolean isLangFound = false;

		for (WebElement language : listOfLangElement) {
			if (language.getText().equalsIgnoreCase(lang.toUpperCase())) {
				ElementClickable(language);
				isLangFound = true;
				break;
			}
		}

		if (isLangFound) {
			ElementClickable(langchangebtnElement);
			log.info("Language changed successfully to: " + lang);
		} else {
			log.error("Language not found: " + lang);
		}
	}

	public boolean isLanguageChanged() throws Exception {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.textToBePresentInElement(
	                hindilangElement, "नमस्ते"));

	        log.info("Language changed successfully");
	        return true;

	    } catch (Exception e) {
	        log.error("Language change not applied" +e);
	        return false;
	    }
	}

	
	@FindBy(id="twotabsearchtextbox") private WebElement searchtextboxElement; 
	public void SearchProduct(String product) {
		ElementClickable(searchtextboxElement);
		log.info("Searching Product");
		searchtextboxElement.sendKeys(product);
		searchtextboxElement.sendKeys(Keys.ENTER);
	}
	
	public boolean IsNavigateProductPage() {
		String title=driver.getTitle();
		
		System.out.println(title);
		if(title.toLowerCase().contains("laptop")) {
			log.info("Product result page open ");
			return true;
		}else {
			log.info("Something is wrong Product result page is not open");
			return false;
		}
		
	}
	
	
}
