package regression;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBaseSetup;
import pages.HomePage;

public class EquivalencyAdminPageTest extends TestBaseSetup {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("***running the equivalency admin page tests***");
		driver=getDriver();
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod: navigate to equivalency admin page");
		homePage.goToHomePage();
		equivalencyAdminPage = homePage.clickEequivalencyAdminLink();
		equivalencyAdminPage.verifyPage();
		equivalencyAdminPage.verifyItemsPerPageDropDownList("10");
		equivalencyAdminPage.verifyItemsPerPageDropDownList("20");
		equivalencyAdminPage.verifyItemsPerPageDropDownList("40");
		equivalencyAdminPage.verifyItemsPerPageDropDownList("80");
	}
	
	@Test
	public void filterEequivalencyListTest() {
		System.out.println("filter equivalency test...");
		equivalencyAdminPage.enterInstitutionName("Portland State University");
		equivalencyAdminPage.selectInstitution("Portland State University");
		equivalencyAdminPage.enterPriorLearningCourseCode("HST350U");
		equivalencyAdminPage.selectPriorLearningCourse("HST350U");
		equivalencyAdminPage.verifyFilteredEquivelancyListByPriorLearningCourse("HST350U");
		equivalencyAdminPage.clickResetAllButton();
		equivalencyAdminPage.enterInstitutionName("Portland State University");
		equivalencyAdminPage.selectInstitution("Portland State University");
		equivalencyAdminPage.enterKaplanCourseCode("SSTC5.0");
		equivalencyAdminPage.selectKaplanCourse("SSTC5.0");
		equivalencyAdminPage.verifyFilteredEquivelancyListByKaplanCourse("SSTC5.0");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod: reseting equivelance page");
		equivalencyAdminPage.clickResetAllButton();

	}

}
