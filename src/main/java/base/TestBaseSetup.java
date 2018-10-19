package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.CampusManagerPage;
import pages.CourseModulePage;
import pages.CreditEvaluationPage;
import pages.DegreeProgramManagerPage;
import pages.EquivalencyAdminPage;
import pages.HomePage;
import pages.SignInPage;

public class TestBaseSetup {
	
	private static WebDriver driver;
	static String driverPath = "c:\\chromedriver.exe";
	private static String applicationURL;
	protected HomePage homePage;
	protected DegreeProgramManagerPage degreeProgramManagerPage;
	protected CreditEvaluationPage creditEvaluationPage;
	protected CourseModulePage courseModulePage;
	protected CampusManagerPage campusManagerPage;
	protected EquivalencyAdminPage equivalencyAdminPage;

		
	public static WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initChromeDriver(appURL);
		}
	}
	
	private static WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", driverPath);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		setApplicationURL(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "browserType", "appURL"})
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);
			verifySigningIn();
		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	public static String getApplicationURL() {
		return applicationURL;
	}

	public static void setApplicationURL(String applicationURL) {
		TestBaseSetup.applicationURL = applicationURL;
	}
	
	public void verifySigningIn() {
		System.out.println("Login test...");
		homePage = new HomePage(getDriver());
		Assert.assertTrue(homePage.verifyPageTitle());
		Assert.assertTrue(homePage.verifyPage());
		SignInPage signInPage = homePage.getSignInPage();
		signInPage.verifyLoginPage();
		signInPage.login("svc_selenium_np@kaplaninc.com", "rQxob)jargV4");
		homePage.selectBrowserWindows("");
		homePage.goToHomePage();
		Assert.assertTrue(homePage.verifyPageTitle());
		Assert.assertTrue(homePage.verifyPage());
	}

}
