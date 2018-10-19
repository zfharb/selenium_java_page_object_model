package regression;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBaseSetup;
import pages.HomePage;

public class CampusManagerPageTest extends TestBaseSetup {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("***running the campus manager page tests***");
		driver=getDriver();
		homePage = new HomePage(driver);
	}
	
	@BeforeMethod
	public void goToCampusManagerPage() throws Exception {
		System.out.println("@BeforeMethod: navigate to campus manager page");
		homePage.goToHomePage();
		campusManagerPage = homePage.clickCampusManagerLink();
	}

	@Test
	public void editCreditLimitTest() throws Exception {
		System.out.println("campus manager credit limit test...");
		campusManagerPage.clickFirstCampusEditButton();
		campusManagerPage.enterUndergraduateCredit("8");
		campusManagerPage.clickModalOkButton();
		campusManagerPage.verifyUndergraduateCreditLimit("8");
		campusManagerPage.clickFirstCampusEditButton();
		campusManagerPage.enterUndergraduateCredit("9");
		campusManagerPage.clickModalOkButton();
		campusManagerPage.verifyUndergraduateCreditLimit("9");
		campusManagerPage.clickFirstCampusEditButton();
		campusManagerPage.enterGraduateCredit("11");
		campusManagerPage.clickModalOkButton();
		campusManagerPage.verifyGraduateCreditLimit("11");
		campusManagerPage.clickFirstCampusEditButton();
		campusManagerPage.enterGraduateCredit("10");
		campusManagerPage.clickModalOkButton();
		campusManagerPage.verifyGraduateCreditLimit("10");
		campusManagerPage.clickFirstCampusEditButton();
		campusManagerPage.clickModalCancelButton();
		campusManagerPage.verifyUndergraduateCreditLimit("9");
		campusManagerPage.verifyGraduateCreditLimit("10");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod:");
	}

}
