package regression;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBaseSetup;
import pages.HomePage;

public class CreditEvaluationPageTest extends TestBaseSetup {

	private WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.out.println("***running the credit evaluation page tests***");
		driver=getDriver();
		homePage = new HomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("@BeforeMethod: open student transcript");
		homePage.goToHomePage();
		creditEvaluationPage = homePage.clickCreditEvaluationPage();
		creditEvaluationPage.enterStudentId("32217354");
		creditEvaluationPage.clickGetStudentButton();
		creditEvaluationPage.verifyTanscriptDropDownList();
		creditEvaluationPage.selectFirstTranscriptFromDropDownList();
		creditEvaluationPage.waitForTranscriptToLoad();
		creditEvaluationPage.deleteAllCourses();
		creditEvaluationPage.clickSaveButton();
	}
	
	@Test
	public void changeInstitutionTest() {
		System.out.println("cet change institution test...");
		creditEvaluationPage.enterInstitutionName("Portland State University");
		creditEvaluationPage.selectFirstInstitution();
		creditEvaluationPage.clickSaveButton();
		creditEvaluationPage.clickStudentName();
		beforeMethod();
		creditEvaluationPage.verifyInstitutionNameInDropDownList("Portland State University");
		creditEvaluationPage.enterInstitutionName("Florida Atlantic University");
		creditEvaluationPage.selectFirstInstitution();
		creditEvaluationPage.clickSaveButton();
		creditEvaluationPage.clickStudentName();
		beforeMethod();
		creditEvaluationPage.verifyInstitutionNameInDropDownList("Florida Atlantic University-Boca Raton");
	}
	
	@Test
	public void addCollegeCourseTest() {
		System.out.println("cet add college course test...");
		creditEvaluationPage.selectTranscriptType("College");
		creditEvaluationPage.enterCollegeCourseCode("zzz101");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.enterCollegeCourseTitle("math101");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.selectCollegeCourseMonth("january");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.selectCollegeCourseYear("2014");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.enterCollegeCourseGrade("B");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.enterCollegeCourseCredit("3");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.selectCollegeCourseCreditType("Semester");
		creditEvaluationPage.verifyCollegeCourseSubmitButtonDisable();
		creditEvaluationPage.selectCollegeCourseLevel("All Levels");
		creditEvaluationPage.enterCollegeCourseNote("test");
		creditEvaluationPage.clickCollegeCourseSubmitButton();
		creditEvaluationPage.verifyCollegeCourseCode("zzz101");
		creditEvaluationPage.verifyCollegeCourseTitle("math101");
		creditEvaluationPage.verifyCollegeCourseMonth("january");
		creditEvaluationPage.verifyCollegeCourseYear("2014");
		creditEvaluationPage.verifyCollegeCourseGrade("B");
		creditEvaluationPage.verifyCollegeCourseCredits("3");
		creditEvaluationPage.verifyCollegeCourseCreditType("Semester");
		creditEvaluationPage.verifyCollegeCourseLevel("All Levels");
	}
	
	@Test
	public void autoCompleteCollegeCourseTest() {
		System.out.println("cet auto complete college course test...");
		addCollegeCourseTest();
		creditEvaluationPage.enterCollegeCourseCode("zzz");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCode("zzz101");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseTitle("math101");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCreditType("Semester");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseLevel("All Levels");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCredits("3");
		creditEvaluationPage.enterCollegeCourseCode("");
		creditEvaluationPage.enterCollegeCourseTitle("math");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCode("zzz101");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseTitle("math101");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCreditType("Semester");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseLevel("All Levels");
		creditEvaluationPage.verifyAutoCompleteListCollegeCourseCredits("3");
	}
	
	@Test
	public void editCollegeCourseTest() {
		System.out.println("cet edit college course test...");
		creditEvaluationPage.selectTranscriptType("College");
		creditEvaluationPage.enterCollegeCourseCode("z101");
		creditEvaluationPage.enterCollegeCourseTitle("math");
		creditEvaluationPage.selectCollegeCourseMonth("january");
		creditEvaluationPage.selectCollegeCourseYear("2014");
		creditEvaluationPage.enterCollegeCourseGrade("B");
		creditEvaluationPage.enterCollegeCourseCredit("3");
		creditEvaluationPage.selectCollegeCourseCreditType("Semester");
		creditEvaluationPage.selectCollegeCourseLevel("All Levels");
		creditEvaluationPage.enterCollegeCourseNote("test");
		creditEvaluationPage.clickCollegeCourseSubmitButton();
		creditEvaluationPage.verifyCollegeCourseCode("z101");
		creditEvaluationPage.verifyCollegeCourseTitle("math");
		creditEvaluationPage.verifyCollegeCourseMonth("january");
		creditEvaluationPage.verifyCollegeCourseYear("2014");
		creditEvaluationPage.verifyCollegeCourseGrade("B");
		creditEvaluationPage.verifyCollegeCourseCredits("3");
		creditEvaluationPage.verifyCollegeCourseCreditType("Semester");
		creditEvaluationPage.verifyCollegeCourseLevel("All Levels");
		creditEvaluationPage.verifyCollegeCourseNote("test");
		creditEvaluationPage.clickCollegeCourseEditButton();
		creditEvaluationPage.enterCollegeCourseCodeInEditBox("z102");
		creditEvaluationPage.enterCollegeCourseTitleInEditBox("sports");
		creditEvaluationPage.selectCollegeCourseMonthInEditDropDownList("april");
		creditEvaluationPage.selectCollegeCourseYearInEditDropDownList("2013");
		creditEvaluationPage.enterCollegeCourseGradeInEditBox("A");
		creditEvaluationPage.enterCollegeCourseCreditsInEditBox("4");
		creditEvaluationPage.selectCollegeCourseCreditTypeInEditDropDownList("Quarter");
		creditEvaluationPage.selectCollegeCourseLevelInEditDropDownList("100 Level");
		creditEvaluationPage.enterCollegeCourseNoteInEditBox("testing");
		creditEvaluationPage.clickCollegeCourseOkButton();
		creditEvaluationPage.verifyCollegeCourseCode("z102");
		creditEvaluationPage.verifyCollegeCourseTitle("sports");
		creditEvaluationPage.verifyCollegeCourseMonth("april");
		creditEvaluationPage.verifyCollegeCourseYear("2013");
		creditEvaluationPage.verifyCollegeCourseGrade("A");
		creditEvaluationPage.verifyCollegeCourseCredits("4");
		creditEvaluationPage.verifyCollegeCourseCreditType("Quarter");
		creditEvaluationPage.verifyCollegeCourseLevel("100 Level");
		creditEvaluationPage.verifyCollegeCourseNote("testing");
		creditEvaluationPage.clickCollegeCourseEditButton();
		creditEvaluationPage.clickCollegeCourseCancelButton();
		creditEvaluationPage.verifyCollegeCourseCode("z102");
		creditEvaluationPage.verifyCollegeCourseTitle("sports");
		creditEvaluationPage.verifyCollegeCourseMonth("april");
		creditEvaluationPage.verifyCollegeCourseYear("2013");
		creditEvaluationPage.verifyCollegeCourseGrade("A");
		creditEvaluationPage.verifyCollegeCourseCredits("4");
		creditEvaluationPage.verifyCollegeCourseCreditType("Quarter");
		creditEvaluationPage.verifyCollegeCourseLevel("100 Level");
		creditEvaluationPage.verifyCollegeCourseNote("testing");
	}

	@Test
	public void copyCollegeCourseTest() {
		System.out.println("cet copy college course test...");
		creditEvaluationPage.selectTranscriptType("College");
		creditEvaluationPage.enterCollegeCourseCode("z101");
		creditEvaluationPage.enterCollegeCourseTitle("math");
		creditEvaluationPage.selectCollegeCourseMonth("january");
		creditEvaluationPage.selectCollegeCourseYear("2014");
		creditEvaluationPage.enterCollegeCourseGrade("B");
		creditEvaluationPage.enterCollegeCourseCredit("3");
		creditEvaluationPage.selectCollegeCourseCreditType("Semester");
		creditEvaluationPage.selectCollegeCourseLevel("All Levels");
		creditEvaluationPage.enterCollegeCourseNote("test");
		creditEvaluationPage.clickCollegeCourseSubmitButton();
		creditEvaluationPage.clickCollegeCourseCopyButton();
		creditEvaluationPage.verifyCollegeCourseCodeInTextBox("z101");
		creditEvaluationPage.verifyCollegeCourseTitleInTextBox("math");
		creditEvaluationPage.verifyCollegeCourseMonthInDropDownList("january");
		creditEvaluationPage.verifyCollegeCourseYearInDropDownList("2014");
		creditEvaluationPage.verifyCollegeCourseGradeInTextBox("B");
		creditEvaluationPage.verifyCollegeCourseCreditsInTextBox("3");
		creditEvaluationPage.verifyCollegeCourseCreditTypeInDropDownList("Semester");
		creditEvaluationPage.verifyCollegeCourseLevelInDropDownList("All Levels");
		creditEvaluationPage.verifyCollegeCourseNoteInTextBox("test");
	}

	@Test
	public void addMillitaryCourseTest() {
		System.out.println("cet add military course test...");
		creditEvaluationPage.selectTranscriptType("Military");
		creditEvaluationPage.enterMilitaryRank("zzz");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryStatus("aaa");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryDateIssued("09/10/2001");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryCourseNumber("zzz101");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryCourseACEExhibitNumber("301b");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryCourseTitle("math101");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.selectMilitaryCourseMonth("january");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.selectMilitaryCourseYear("2014");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryCourseACECreditRecommendation("good");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.enterMilitaryCourseCredit("3");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.selectMilitaryCourseCreditType("Semester");
		creditEvaluationPage.verifyMilitaryCourseSubmitButtonDisable();
		creditEvaluationPage.selectMilitaryCourseLevel("Upper Undergrad Credit");
		creditEvaluationPage.selectMilitaryCourseSkillLevel("10");
		creditEvaluationPage.enterMilitaryCourseNote("test");
		creditEvaluationPage.clickMilitaryCourseSubmitButton();
		creditEvaluationPage.verifyMilitaryCourseNumber("zzz101");
		creditEvaluationPage.verifyMilitaryCourseACEExhibitNumber("301b");
		creditEvaluationPage.verifyMilitaryCourseTitle("math101");
		creditEvaluationPage.verifyMilitaryCourseMonth("january");
		creditEvaluationPage.verifyMilitaryCourseYear("2014");
		creditEvaluationPage.verifyMilitaryCourseACECreditRecommendation("good");
		creditEvaluationPage.verifyMilitaryCourseCredits("3");
		creditEvaluationPage.verifyMilitaryCourseCreditType("Semester");
		creditEvaluationPage.verifyMilitaryCourseLevel("Upper Undergrad Credit");
		creditEvaluationPage.verifyMilitaryCourseSkillLevel("10");
		creditEvaluationPage.verifyMilitaryCourseNote("test");
	}
	
	@Test
	public void autoCompleteMilitaryCourseTest() {
		System.out.println("cet auto complete military course test...");
		addMillitaryCourseTest();
		creditEvaluationPage.enterMilitaryCourseNumber("zzz");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseNumber("zzz101");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseAceExhibitNumber("301b");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseTitle("math101");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseAceCreditRecommendation("good");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseCredits("3");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseCreditType("Semester");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseLevel("U");
		creditEvaluationPage.enterMilitaryCourseNumber("");
		creditEvaluationPage.enterMilitaryCourseACEExhibitNumber("301");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseNumber("zzz101");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseAceExhibitNumber("301b");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseTitle("math101");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseAceCreditRecommendation("good");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseCredits("3");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseCreditType("Semester");
		creditEvaluationPage.verifyAutoCompleteListMilitaryCourseLevel("U");
	}
		
	@Test
	public void editMillitaryCourseTest() {
		System.out.println("cet edit military course test...");
		creditEvaluationPage.selectTranscriptType("Military");
		creditEvaluationPage.enterMilitaryRank("zzz");
		creditEvaluationPage.enterMilitaryStatus("aaa");
		creditEvaluationPage.enterMilitaryDateIssued("09/10/2001");
		creditEvaluationPage.enterMilitaryCourseNumber("z101");
		creditEvaluationPage.enterMilitaryCourseACEExhibitNumber("3");
		creditEvaluationPage.enterMilitaryCourseTitle("math");
		creditEvaluationPage.selectMilitaryCourseMonth("january");
		creditEvaluationPage.selectMilitaryCourseYear("2014");
		creditEvaluationPage.enterMilitaryCourseACECreditRecommendation("3");
		creditEvaluationPage.enterMilitaryCourseCredit("3");
		creditEvaluationPage.selectMilitaryCourseCreditType("Semester");
		creditEvaluationPage.selectMilitaryCourseLevel("Graduate Credit");
		creditEvaluationPage.selectMilitaryCourseSkillLevel("10");
		creditEvaluationPage.enterMilitaryCourseNote("test");
		creditEvaluationPage.clickMilitaryCourseSubmitButton();
		creditEvaluationPage.verifyMilitaryCourseNumber("z101");
		creditEvaluationPage.verifyMilitaryCourseACEExhibitNumber("3");
		creditEvaluationPage.verifyMilitaryCourseTitle("math");
		creditEvaluationPage.verifyMilitaryCourseMonth("january");
		creditEvaluationPage.verifyMilitaryCourseYear("2014");
		creditEvaluationPage.verifyMilitaryCourseACECreditRecommendation("3");
		creditEvaluationPage.verifyMilitaryCourseCredits("3");
		creditEvaluationPage.verifyMilitaryCourseCreditType("Semester");
		creditEvaluationPage.verifyMilitaryCourseLevel("Graduate Credit");
		creditEvaluationPage.verifyMilitaryCourseSkillLevel("10");
		creditEvaluationPage.verifyMilitaryCourseNote("test");
		creditEvaluationPage.clickMilitaryCourseEditButton();
		creditEvaluationPage.enterMilitaryCourseNumberInEditBox("z102");
		creditEvaluationPage.enterMilitaryCourseACEExhibitNumberInEditBox("4");
		creditEvaluationPage.enterMilitaryCourseTitleInEditBox("sports");
		creditEvaluationPage.selectMilitaryCourseMonthInEditDropDownList("april");
		creditEvaluationPage.selectMilitaryCourseYearInEditDropDownList("2013");
		creditEvaluationPage.enterMilitaryCourseACECreditRecommendationInEditBox("4");
		creditEvaluationPage.enterMilitaryCourseCreditsInEditBox("4");
		creditEvaluationPage.selectMilitaryCourseCreditTypeInEditDropDownList("Quarter");
		creditEvaluationPage.selectMilitaryCourseLevelInDropDownEditList("Vocational Credit");
		creditEvaluationPage.selectMilitaryCourseSkillLevelInDropDownEditList("20");
		creditEvaluationPage.enterMilitaryCourseNoteInEditBox("testing");
		creditEvaluationPage.clickMilitaryCourseOkButton();
		creditEvaluationPage.verifyMilitaryCourseNumber("z102");
		creditEvaluationPage.verifyMilitaryCourseACEExhibitNumber("4");
		creditEvaluationPage.verifyMilitaryCourseTitle("sports");
		creditEvaluationPage.verifyMilitaryCourseMonth("april");
		creditEvaluationPage.verifyMilitaryCourseYear("2013");
		creditEvaluationPage.verifyMilitaryCourseACECreditRecommendation("4");
		creditEvaluationPage.verifyMilitaryCourseCredits("4");
		creditEvaluationPage.verifyMilitaryCourseCreditType("Quarter");
		creditEvaluationPage.verifyMilitaryCourseLevel("Vocational Credit");
		creditEvaluationPage.verifyMilitaryCourseSkillLevel("20");
		creditEvaluationPage.verifyMilitaryCourseNote("testing");
		creditEvaluationPage.clickMilitaryCourseEditButton();
		creditEvaluationPage.clickMilitaryCourseCancelButton();
		creditEvaluationPage.verifyMilitaryCourseNumber("z102");
		creditEvaluationPage.verifyMilitaryCourseACEExhibitNumber("4");
		creditEvaluationPage.verifyMilitaryCourseTitle("sports");
		creditEvaluationPage.verifyMilitaryCourseMonth("april");
		creditEvaluationPage.verifyMilitaryCourseYear("2013");
		creditEvaluationPage.verifyMilitaryCourseACECreditRecommendation("4");
		creditEvaluationPage.verifyMilitaryCourseCredits("4");
		creditEvaluationPage.verifyMilitaryCourseCreditType("Quarter");
		creditEvaluationPage.verifyMilitaryCourseLevel("Vocational Credit");
		creditEvaluationPage.verifyMilitaryCourseSkillLevel("20");
		creditEvaluationPage.verifyMilitaryCourseNote("testing");
	}
	
	@Test
	public void copyMillitaryCourseTest() {
		System.out.println("cet copy military course test...");
		creditEvaluationPage.selectTranscriptType("Military");
		creditEvaluationPage.enterMilitaryRank("zzz");
		creditEvaluationPage.enterMilitaryStatus("aaa");
		creditEvaluationPage.enterMilitaryDateIssued("09/10/2001");
		creditEvaluationPage.enterMilitaryCourseNumber("z101");
		creditEvaluationPage.enterMilitaryCourseACEExhibitNumber("3");
		creditEvaluationPage.enterMilitaryCourseTitle("math");
		creditEvaluationPage.selectMilitaryCourseMonth("january");
		creditEvaluationPage.selectMilitaryCourseYear("2014");
		creditEvaluationPage.enterMilitaryCourseACECreditRecommendation("3");
		creditEvaluationPage.enterMilitaryCourseCredit("3");
		creditEvaluationPage.selectMilitaryCourseCreditType("Semester");
		creditEvaluationPage.selectMilitaryCourseLevel("Graduate Credit");
		creditEvaluationPage.selectMilitaryCourseSkillLevel("10");
		creditEvaluationPage.enterMilitaryCourseNote("test");
		creditEvaluationPage.clickMilitaryCourseSubmitButton();
		creditEvaluationPage.clickCollegeCourseCopyButton();
		creditEvaluationPage.verifyMilitaryCourseNumberInTextBox("z101");
		creditEvaluationPage.verifyMilitaryCourseACEExhibitNumberInTextBox("3");
		creditEvaluationPage.verifyMilitaryCourseTitleInTextBox("math");
		creditEvaluationPage.verifyMilitaryCourseMonthInDropDownList("january");
		creditEvaluationPage.verifyMilitaryCourseYearInDropDownList("2014");
		creditEvaluationPage.verifyMilitaryCourseACECreditRecommendationInTextBox("3");
		creditEvaluationPage.verifyMilitaryCourseCreditsInTextBox("3");
		creditEvaluationPage.verifyMilitaryCourseCreditTypeInDropDownList("Semester");
		creditEvaluationPage.verifyMilitaryCourseLevelInDropDownList("Graduate Credit");
		creditEvaluationPage.verifyMilitaryCourseSkillLevelInDropDownList("10");
		creditEvaluationPage.verifyMilitaryCourseNoteInTextBox("test");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod: deleteing all courses");
		creditEvaluationPage.deleteAllCourses();
		creditEvaluationPage.clickSaveButton();
	}
}
