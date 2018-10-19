package regression;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBaseSetup;
import pages.HomePage;


public class DegreeProgramManagerPageTest extends TestBaseSetup {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("***running the degree program manager page tests***");
		driver=getDriver();
		homePage = new HomePage(driver);
	}
		
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod: search for degree program");
		homePage.goToHomePage();
		degreeProgramManagerPage = homePage.clickDegreeProgramManagerLink();
		degreeProgramManagerPage.enterProgramName("Master of Business Administration");
		degreeProgramManagerPage.selectFirstItemFromProgramDropDownList();
		degreeProgramManagerPage.verifyDegreeProgramName("Master of Business Administration");
		degreeProgramManagerPage.deleteAllCategories();
		degreeProgramManagerPage.clickSaveButton();
	}
	
	@Test
	public void addCategoryTest() {
		System.out.println("add category test...");
		degreeProgramManagerPage.clickAddNewCategoryButton();
		degreeProgramManagerPage.enterCategoryName("math");
		degreeProgramManagerPage.clickModalOkButton();
		degreeProgramManagerPage.verifyCategoryAdded("math");
	}
	
	@Test
	public void editCategoryTest() {
		System.out.println("edit category test...");
		addCategoryTest();
		degreeProgramManagerPage.clickCategoryEditButton("math");
		degreeProgramManagerPage.enterCategoryName("history");
		degreeProgramManagerPage.clickModalOkButton();
		degreeProgramManagerPage.verifyCategoryAdded("history");
	}
	
	@Test
	public void addElectivePoolTest() {
		System.out.println("add elective pool test...");
		addCategoryTest();
		degreeProgramManagerPage.clickAddNewElectivePoolButton();
		degreeProgramManagerPage.verifyAddNewElectivePoolModal();;
		degreeProgramManagerPage.enterElectivePoolName("calculas");
		degreeProgramManagerPage.clickModalOkButton();
		degreeProgramManagerPage.verifyElectivePoolAdded("math", "calculas");
	}
	
	@Test
	public void addCourseTest() {
		System.out.println("add elective pool course test...");
		addElectivePoolTest();
		degreeProgramManagerPage.clickSearchCourseTab();
		degreeProgramManagerPage.enterCourseCode("ED553P");
		degreeProgramManagerPage.dragAndDropCourseToElectivePool("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolCourseAdded("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
	}
	
	@Test
	public void addCourseGroupTest() {
		System.out.println("add elective pool course Group test...");
		addElectivePoolTest();
		degreeProgramManagerPage.clickSearchGroupTab();
		degreeProgramManagerPage.enterGroupName("Core Mathematics Requirement - Version B!");
		degreeProgramManagerPage.dragAndDropGroupToElectivePool("Core Mathematics Requirement - Version B!", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolGroupAdded("Core Mathematics Requirement - Version B!", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
	}
	
	@Test
	public void courseSequenceListTest() {
		System.out.println("course sequence test...");
		addElectivePoolTest();
		degreeProgramManagerPage.enterCourseCode("ED553P");
		degreeProgramManagerPage.dragAndDropCourseToElectivePool("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolCourseAdded("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.enterCourseCode("CE460");
		degreeProgramManagerPage.dragAndDropCourseToElectivePool("Special Education Law", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolCourseAdded("Special Education Law", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
		degreeProgramManagerPage.clickSequenceButton();
		degreeProgramManagerPage.verifySequencePage();
		degreeProgramManagerPage.verifyCoursesNotDisplayedSequencePage();
		degreeProgramManagerPage.clickCoursesButton();
		degreeProgramManagerPage.verifyDegreeProgramManagerPage();
		degreeProgramManagerPage.clickSearchCourseTab();
		degreeProgramManagerPage.checkCourseSequenceButton("Special Education Law", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
		degreeProgramManagerPage.clickSequenceButton();
		degreeProgramManagerPage.verifySequencePage();
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("Special Education Law");
		degreeProgramManagerPage.verifyCourseNotAddedToSequenceList("History and Philosophy of Education (P)");
		degreeProgramManagerPage.clickCoursesButton();
		degreeProgramManagerPage.verifyDegreeProgramManagerPage();
		degreeProgramManagerPage.checkCourseSequenceButton("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
		degreeProgramManagerPage.clickSequenceButton();
		degreeProgramManagerPage.verifySequencePage();
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("Special Education Law");
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("History and Philosophy of Education (P)");
		degreeProgramManagerPage.clickCoursesButton();
		degreeProgramManagerPage.verifyDegreeProgramManagerPage();
		degreeProgramManagerPage.uncheckCourseSequenceButton("Special Education Law", "calculas", "math");
		degreeProgramManagerPage.uncheckCourseSequenceButton("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.clickSaveButton();
		degreeProgramManagerPage.clickSequenceButton();
		degreeProgramManagerPage.verifySequencePage();
		degreeProgramManagerPage.verifyCourseNotAddedToSequenceList("Special Education Law");
		degreeProgramManagerPage.verifyCourseNotAddedToSequenceList("History and Philosophy of Education");
		degreeProgramManagerPage.clickCoursesButton();
		degreeProgramManagerPage.verifyDegreeProgramManagerPage();
		degreeProgramManagerPage.checkCourseSequenceButton("Special Education Law", "calculas", "math");
		degreeProgramManagerPage.checkCourseSequenceButton("History and Philosophy of Education (P)", "calculas", "math");
		degreeProgramManagerPage.enterCourseCode("LS490");
		degreeProgramManagerPage.dragAndDropCourseToCategory("Legal Philosophy", "math");
		degreeProgramManagerPage.verifyCategoryCourseAdded("Legal Philosophy", "math");
		degreeProgramManagerPage.clickSaveButton();
		degreeProgramManagerPage.clickSequenceButton();
		degreeProgramManagerPage.verifySequencePage();
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("Special Education Law");
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("History and Philosophy of Education (P)");
		degreeProgramManagerPage.verifyCourseAddedToSequenceList("Legal Philosophy");
	}
	
	@Test
	public void addModularizedCourseTest() {
		System.out.println("add modularized course test...");
		addElectivePoolTest();
		degreeProgramManagerPage.clickSearchCourseTab();
		degreeProgramManagerPage.enterCourseCode("AB217");
		degreeProgramManagerPage.dragAndDropCourseToElectivePool("Finance", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolCourseAdded("Finance", "calculas", "math");
		degreeProgramManagerPage.verifyElectivePoolCourseModularizeButtonUnder("Finance", "calculas", "math");
		degreeProgramManagerPage.clickElectivePoolCourseModularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.verifyModularizeModal();
		degreeProgramManagerPage.clickModularizeModalCancelButton();
		degreeProgramManagerPage.verifyElectivePoolCourseModularizeButtonUnder("Finance", "calculas", "math");
		degreeProgramManagerPage.clickElectivePoolCourseModularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.verifyModularizeModal();
		degreeProgramManagerPage.clickModularizeModalOkButton();
		degreeProgramManagerPage.verifyElectivePoolCourseUnmodularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.clickElectivePoolCourseUnmodularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.clickAlertPopUpCancelButton();
		degreeProgramManagerPage.verifyElectivePoolCourseUnmodularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.clickElectivePoolCourseUnmodularizeButton("Finance", "calculas", "math");
		degreeProgramManagerPage.clickAlertPopUpAcceptButton();
		degreeProgramManagerPage.verifyElectivePoolCourseModularizeButtonUnder("Finance", "calculas", "math");
		degreeProgramManagerPage.enterCourseCode("AB217");
		degreeProgramManagerPage.dragAndDropCourseToCategory("Finance", "math");
		degreeProgramManagerPage.verifyCategoryCourseAdded("Finance","math");
		degreeProgramManagerPage.verifyCategoryCourseModularizeButton("Finance","math");
		degreeProgramManagerPage.clickCategoryCourseModularizeButton("Finance","math");
		degreeProgramManagerPage.verifyModularizeModal();
		degreeProgramManagerPage.clickModularizeModalCancelButton();
		degreeProgramManagerPage.verifyCategoryCourseModularizeButton("Finance","math");
		degreeProgramManagerPage.clickCategoryCourseModularizeButton("Finance","math");
		degreeProgramManagerPage.verifyModularizeModal();
		degreeProgramManagerPage.clickModularizeModalOkButton();
		degreeProgramManagerPage.verifyCategoryCourseUnmodularizeButton("Finance", "math");
		degreeProgramManagerPage.clickCategoryCourseUnmodularizeButton("Finance", "math");
		degreeProgramManagerPage.clickAlertPopUpCancelButton();
		degreeProgramManagerPage.verifyCategoryCourseUnmodularizeButton("Finance", "math");
		degreeProgramManagerPage.clickCategoryCourseUnmodularizeButton("Finance", "math");
		degreeProgramManagerPage.clickAlertPopUpAcceptButton();
		degreeProgramManagerPage.verifyCategoryCourseModularizeButton("Finance","math");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod: deleting categories");
		degreeProgramManagerPage.deleteAllCategories();
		degreeProgramManagerPage.clickSaveButton();
	}
}
