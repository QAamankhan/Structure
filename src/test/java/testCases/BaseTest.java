package testCases;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import utilities.ExcelReader;
import utilities.ReadConfig;

public class BaseTest {

	public static WebDriver driver;
	ReadConfig rc;
	
	@BeforeSuite(alwaysRun = true)
	public void OpenBrowser() {
		rc = new ReadConfig();
		
		String browser = rc.GetBrowser();
		String url = rc.GetUrl();

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			driver.navigate().to(url);
			break;

		case "firefox":
			driver = new FirefoxDriver();
			driver.navigate().to(url);
			break;

		case "Edge":
			driver = new EdgeDriver();
			driver.navigate().to(url);

			break;

		default:
			System.out.println("please select valid browser name");

		}

		try {
			driver.manage().window().maximize();
			
			WebElement continuebtn=driver.findElement(By.xpath("//button[.='Continue shopping']"));
			if (continuebtn.isDisplayed()) {
				continuebtn.click();
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	

	
	@AfterSuite(alwaysRun = true)
	public void TearDown() {
		driver.close();
	}
	
	
	public static String ScreenShots(String testname, String status) {

	    String baseDir = System.getProperty("user.dir") + "/Reports/screenshots/" + status;

	    File directory = new File(baseDir);
	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    String destPath = baseDir + "/" + testname + ".png";

	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File(destPath));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // 🔥 RETURN RELATIVE PATH FOR EXTENT
	    return "screenshots/" + status + "/" + testname + ".png";
	}

	
	@DataProvider(name = "ExcelData")
	public Object[][] data() throws Exception {

	    ExcelReader exr = new ExcelReader();
	    return exr.Data();
	}

	
}
