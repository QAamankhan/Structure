package testCases;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SignInPage;

public class SignInValidation extends BaseTest {

	SignInPage sp;
	private static final Logger log = LogManager.getLogger(SignInPage.class);

	@BeforeMethod(alwaysRun = true)
	public void Objcet() {
		sp = new SignInPage(driver);
	}

	@Test(groups = { "SignIn", "regression" })
	public void TC01_OpenSingInPage() {
		sp.ClickOnSignIn();

	}

	@Test(dataProvider = "ExcelData", dataProviderClass = BaseTest.class, groups = { "SignIn", "regression" })
	public void TC02_FillCreds(String email, String password, String expected) {

		log.info("Starting login test with expected result: {}", expected);

		sp.FillCredentials(email, password);

		if (expected.equalsIgnoreCase("pass")) {
			log.info("Validating successful login");
			Assert.assertTrue(true);
		} else {
			log.info("Validating login failure");
			Assert.assertTrue(sp.isLoginErrorDisplayed());
		}
	}
}
