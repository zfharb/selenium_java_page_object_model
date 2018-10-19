package regression;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBaseSetup;
import pages.HomePage;

public class CourseModulePageTest extends TestBaseSetup {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("***running the course module page tests***");
		driver=getDriver();
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod: navigate to course module page");
		homePage.goToHomePage();
		courseModulePage = homePage.clickCourseModuleLink();
	}
	
	@Test
	public void addModuleTest() {
		System.out.println("add module test...");
		courseModulePage.deleteModule("Course Assessment for Marketing Management");
		courseModulePage.enterNameInCourseSearchBox("Course Assessment for Marketing Management");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("Course Assessment for Marketing Management");
		courseModulePage.dragAndDropModule("Course Assessment for Marketing Management");
		courseModulePage.verifyModuleDisplayedUnderModuleManager("Course Assessment for Marketing Management");
		courseModulePage.verifyModuleTotalCreditsNumber("-", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleRequiredCreditsNumber("6", "Course Assessment for Marketing Management");
		courseModulePage.clickModuleDeleteButton("Course Assessment for Marketing Management");
		courseModulePage.clickAlertPopUpCancelButton();
		courseModulePage.verifyModuleDisplayedUnderModuleManager("Course Assessment for Marketing Management");
	}
	
	@Test
	public void addModuleCourseTest() {
		System.out.println("add module course test...");
		addModuleTest();
		courseModulePage.enterNameInCourseSearchBox("611AB");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("Mathematics/Draftsmen");
		courseModulePage.dragAndDropCourseToModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyCourseDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleTotalCreditsNumber("3", "Course Assessment for Marketing Management");
		courseModulePage.clickModuleCourseDeleteButton("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.clickAlertPopUpCancelButton();
		courseModulePage.verifyCourseDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.clickModuleCourseDeleteButton("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.clickAlertPopUpAcceptButton();
		courseModulePage.verifyModuleTotalCreditsNumber("0", "Course Assessment for Marketing Management");
		courseModulePage.verifyCourseNotDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.enterNameInCourseSearchBox("611AB");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("Mathematics/Draftsmen");
		courseModulePage.dragAndDropCourseToModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyCourseDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleTotalCreditsNumber("3", "Course Assessment for Marketing Management");
		courseModulePage.enterNameInCourseSearchBox("611AB");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("Mathematics/Draftsmen");
		courseModulePage.dragAndDropCourseToModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.clickAlertPopUpAcceptButton();
		courseModulePage.verifyNoDuplicateCourseDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleTotalCreditsNumber("3", "Course Assessment for Marketing Management");
		courseModulePage.enterNameInCourseSearchBox("CR611");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("STENO THEORY PRACTICE I");
		courseModulePage.dragAndDropCourseToModule("STENO THEORY PRACTICE I", "Course Assessment for Marketing Management");
		courseModulePage.verifyCourseDisplayedUnderModule("STENO THEORY PRACTICE I", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleTotalCreditsNumber("6", "Course Assessment for Marketing Management");
		courseModulePage.verifyModuleDropCourseBoxNotDisplayed("Course Assessment for Marketing Management");
	}
   	
	@Test
	public void filterModuleTest() {
		System.out.println("filter module test...");
		courseModulePage.deleteModule("Course Assessment for Marketing Management");
		addModuleTest();
		courseModulePage.enterNameInFilterBox("Course Assessment for Marketing Management");
		courseModulePage.verifyFilteredModule("Course Assessment for Marketing Management");
		courseModulePage.enterNameInFilterBox(" ");
		courseModulePage.enterNameInCourseSearchBox("611AB");
		courseModulePage.verifyCourseDisplayedUnderCourseSearchBox("Mathematics/Draftsmen");
		courseModulePage.dragAndDropCourseToModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.verifyCourseDisplayedUnderModule("Mathematics/Draftsmen", "Course Assessment for Marketing Management");
		courseModulePage.enterNameInFilterBox("Mathematics/Draftsmen");
		courseModulePage.verifyFilteredModule("Course Assessment for Marketing Management");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod: deleting modules");
		courseModulePage.deleteModule("Course Assessment for Marketing Management");
	}

}
