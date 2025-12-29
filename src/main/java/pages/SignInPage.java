package pages;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import base.BaseClass;

public class SignInPage extends BaseClass {

	private static final Logger log = LogManager.getLogger(SignInPage.class);

	public SignInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[@data-nav-role='signin']")
	WebElement signElement;
	@FindBy(xpath = "//div[@id='claim-collection-container']/h1")
	WebElement textsigninElement;

	public void ClickOnSignIn() {
		log.info("CLICK ON SIGN IN BUTTON");
		ElementClickable(signElement);
	}

	@FindBy(id = "ap_email_login")
	WebElement emailElement;
	@FindBy(id = "ap_password")
	WebElement password;
	@FindBy(id = "continue")
	WebElement continuebtnElement;

	@FindBy(id = "signInSubmit")
	WebElement signbtnElement;
	@FindBy(id = "nav-link-accountList")
	WebElement signOutHoverElement;
	@FindBy(xpath = "//span[.='Sign Out']")
	WebElement signOutBtnElement;

	@FindBy(xpath = "(//h4[@class='a-alert-heading'])[1]")
	WebElement errormsgElement;

	public void FillCredentials(String email, String pass) {

		log.info("Entering email");
		ElementClickable(emailElement);
		emailElement.clear();
		emailElement.sendKeys(email);

		log.info("Clicking Continue button");
		ElementClickable(continuebtnElement);

		log.info("Entering password");
		ElementClickable(password);
		password.clear();
		password.sendKeys(pass);

		log.info("Clicking Sign In button");
		ElementClickable(signbtnElement);
	}

	public boolean isLoginSuccessful() {
		try {
			log.info("Checking if user is logged in");
			Actions action = new Actions(driver);
			action.moveToElement(signOutHoverElement).perform();
			boolean btndisply=signOutBtnElement.isDisplayed();
			signOutBtnElement.click();
			return btndisply;
		} catch (Exception e) {
			log.warn("Login successful element not found");
			return false;
		}
	}

	public boolean isLoginErrorDisplayed() {
		try {
			log.info("Checking login error message");
			log.error("Login error message displayed: {}", errormsgElement.getText());
			return errormsgElement.isDisplayed();
		} catch (Exception e) {
			log.warn("Login error message not found");
			return false;
		}
	}

}
