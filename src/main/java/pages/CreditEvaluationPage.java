package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import exceptions.ElementNotClickableException;

public class CreditEvaluationPage extends Page {
	private By studentName = By.xpath(".//a[contains(@ng-click, 'Student.hideSearch')]");
	private By getStudentButton = By.xpath(".//button[contains(text(), 'Get Student')]");
	private By transcriptDropDownListLabel = By.xpath(".//label[text() = 'School Transcript']");
	private By transcriptDropDownList = By.xpath(".//select[@ng-change = 'Student.selectedTranscript.selected(Student)']");
	private By collegeCourse = By.xpath(".//tr[@ng-repeat = 'course in coursesRet | reverse']");
	private By collegeAutoCompleteCourse = By.xpath(".//li[@ng-repeat = 'match in matches track by $index']");
	private By militaryCourse = By.xpath(".//div[@heading = 'Military Courses']/.//tr[@ng-repeat = 'course in coursesRet | reverse']");
	private By militaryAutoCompleteCourse = By.xpath(".//div[@heading = 'Military Courses']/.//li[@ng-repeat = 'match in matches track by $index']");
	private By institutionNameTextBox = By.xpath(".//label[contains(text(), 'Institution')]/following-sibling::input");
	private By transcriptTypeDropDownList = By.xpath(".//select[@ng-change = 'Student.selectedTranscript.transcriptTypeChanged()']");
	private By collegeCourseCodeTextBox = By.xpath(".//input[@placeholder = 'Course Codes']");
	private By collegeCourseTitleTextBox = By.xpath(".//input[@placeholder= 'Title']");
	private By collegeCourseMonthSelectList = By.xpath(".//select[@name= 'month']");
	private By collegeCourseYearSelectList = By.xpath(".//select[@name= 'year']");
	private By collegeCourseGradeTextBox = By.xpath(".//input[@placeholder= 'Grade']");
	private By collegeCourseCreditsTextBox = By.xpath(".//input[@placeholder= 'Credits']");
	private By collegeCourseCreditTypeSelectList = By.id("select");
	private By collegeCourseLevelSelectList = By.xpath(".//select[@name= 'level']");
	private By collegeCourseNoteBox = By.xpath(".//textarea[@placeholder= 'Notes']");
	private By collegeCourseSubmitButton = By.xpath(".//button[contains(@ng-click, 'addCourse')]");
	private By collegeCourseEditButton = By.xpath(".//button[@ng-click = 'editCourse(course)']");
	private By collegeCourseDeleteButton = By.xpath(".//td/div/button[@ng-click='deleteCourse(course)']");
	private By collegeCourseCopyButton = By.xpath(".//button[@ng-click = 'copyCourse(course)']");
	private By collegeCourseOkButton = By.xpath(".//button[@ng-click = 'saveCourse(course)']");
	private By collegeCourseCancelButton = By.xpath(".//button[contains(@ng-click, 'cancelCourse')]");
	private By saveButton = By.xpath(".//small/.//span[@ng-click = 'save()' and contains(text(), 'save')]");
	private By transcriptImage = By.id("pdf-canvas");
	private By militaryRankTextBox = By.id("militaryRank");
	private By militaryStatusTextBox = By.id("militaryStatus");
	private By militaryDateIssuedCalendarBox = By.id("militaryDateIssued");
	private By militaryCourseCodeTextBox = By.xpath(".//div[@heading = 'Military Courses']/.//input[@placeholder= 'Military Course No']");
	private By militaryExhibitNumberTextBox = By.xpath(".//div[@heading = 'Military Courses']/.//input[@placeholder= 'ACE Exhibit Number']");
	private By militaryCourseTitleTextBox = By.xpath(".//div[@heading = 'Military Courses']/.//input[@placeholder= 'Title']");
	private By militaryCourseMonthSelectList = By.xpath(".//div[@heading = 'Military Courses']/.//select[@name= 'month']");
	private By militaryCourseYearSelectList = By.xpath(".//div[@heading = 'Military Courses']/.//select[@name= 'year']");
	private By militaryACECreditRecommendationTextBox = By.xpath(".//div[@heading = 'Military Courses']/.//input[@placeholder= 'ACE credit recommendation']");
	private By militaryCourseCreditsTextBox = By.xpath(".//div[@heading = 'Military Courses']/.//input[@placeholder= 'Credits']");
	private By militaryCourseCreditTypeSelectList = By.xpath(".//div[@heading = 'Military Courses']/.//select[@id = 'select']");
	private By militaryCourseLevelDropDownList = By.xpath(".//div[@heading = 'Military Courses']/.//select[@ng-model= 'course.LevelID']");
	private By militaryCourseSkillLevelDropDownList = By.xpath(".//div[@heading = 'Military Courses']/.//select[@ng-model= 'course.SkillLevel']");
	private By militaryCourseNoteBox = By.xpath(".//div[@heading = 'Military Courses']/.//textarea[@placeholder= 'Notes']");
	private By militaryCourseSubmitButton = By.xpath(".//div[@heading = 'Military Courses']/.//button[contains(@ng-click, 'addCourse')]");
	private By militaryCourseEditButton = By.xpath(".//button[@ng-click = 'editCourse(course)']");
	private By militaryCourseDeleteButton = By.xpath(".//td/div/button[@ng-click='deleteCourse(course)']");
	private By militaryCourseCopyButton = By.xpath(".//button[@ng-click = 'copyCourse(course)']");
	private By militaryCourseOkButton = By.xpath(".//button[@ng-click = 'saveCourse(course)']");
	private By militaryCourseCancelButton = By.xpath(".//button[contains(@ng-click, 'cancelCourse')]");

	
	Map<String, String> transcriptTypeMap = new HashMap<String, String>();
	Map<String, String> monthMap = new HashMap<String, String>();
	Map<String, String> yearMap = new HashMap<String, String>();
	Map<String, String> creditTypeMap = new HashMap<String, String>();
	Map<String, String> levelMap = new HashMap<String, String>();
	Map<String, String> militaryLevelMap = new HashMap<String, String>();
	Map<String, String> militarySkillLevelMap = new HashMap<String, String>();

	public CreditEvaluationPage(WebDriver driver) {
		super(driver);
		createTranscriptTypeHashMap();
        createMonthHashMap();
        createYearHashMap();
        createCreditTypeHashMap();
        createCollegeLevelHashMap();
        createMilitaryLevelHashMap();
        createMilitarySkillLevelHashMap();
	}
	
	private void createTranscriptTypeHashMap() {
		transcriptTypeMap.put("College", "number:1");
		transcriptTypeMap.put("Corporate", "number:2");
		transcriptTypeMap.put("Military", "number:4");
		transcriptTypeMap.put("MOOC", "number:3");
		transcriptTypeMap.put("Other", "number:5");
	}
	
	private void createMonthHashMap() {
		monthMap.put("january", "number:1");
        monthMap.put("february", "number:2");
        monthMap.put("march", "number:3");
        monthMap.put("april", "number:4");
        monthMap.put("may", "number:5");
        monthMap.put("june", "number:6");
        monthMap.put("july", "number:7");
        monthMap.put("august", "number:8");
        monthMap.put("september", "number:9");
        monthMap.put("october", "number:10");
        monthMap.put("november", "number:11");
        monthMap.put("december", "number:12");
	}
		
	private void createYearHashMap() {
		yearMap.put("2016", "number:2016");
		yearMap.put("2015", "number:2015");
		yearMap.put("2014", "number:2014");
		yearMap.put("2013", "number:2013");
		yearMap.put("2012", "number:2012");
	}
	
	private void createCreditTypeHashMap() {
		creditTypeMap.put("Quarter", "number:1");
		creditTypeMap.put("Quarter Calculated", "number:4");
		creditTypeMap.put("Semester", "number:2");
		creditTypeMap.put("Trimester", "number:3");
	}
	
	private void createCollegeLevelHashMap() {
		levelMap.put("All Levels", "number:1");
		levelMap.put("100 Level", "number:3");
		levelMap.put("200 Level", "number:4");
		levelMap.put("300 Level", "number:5");
		levelMap.put("400 Level", "number:6");
		levelMap.put("500 Level", "number:7");
		levelMap.put("600 Level", "number:8");
		levelMap.put("700 Level", "number:9");
		levelMap.put("800 Level", "number:10");	
		levelMap.put("Graduate Credit", "string:G");	
		levelMap.put("Lower Undergrad Credit", "string:L");	
		levelMap.put("Upper Undergrad Credit", "string:U");	
		levelMap.put("Vocational Credit", "string:V");	
	}
	
	private void createMilitaryLevelHashMap() {
		militaryLevelMap.put("Graduate Credit", "string:G");
		militaryLevelMap.put("Lower Undergrad Credit", "string:L");
		militaryLevelMap.put("Upper Undergrad Credit", "string:U");
		militaryLevelMap.put("Vocational Credit", "string:V");
	}
	
	private void createMilitarySkillLevelHashMap() {
		militarySkillLevelMap.put("10", "number:10");
		militarySkillLevelMap.put("20", "number:20");
		militarySkillLevelMap.put("30", "number:30");
		militarySkillLevelMap.put("40", "number:40");
		militarySkillLevelMap.put("50", "number:50");
		militarySkillLevelMap.put("60", "number:60");
	}
	
	public void clickSaveButton() {
		sleep(2);
		waitForElement(saveButton);
		if (driver.findElements(saveButton).size() > 1) {
			clickElement(driver.findElements(saveButton).get(1));
		} else {
			clickElement(driver.findElement(saveButton));
		}
		sleep(5);
	}
	
	public void waitForTranscriptToLoad() {
		waitForElement(transcriptImage);
	}
	
	public void clickStudentName() {
		waitForElement(studentName);
		clickElement(driver.findElement(studentName));
	}
	
	public void enterStudentId(String studentId) {
		waitForElement(By.xpath("//*/input[contains(@placeholder, 'Enter a Student id')]"));
		driver.findElement(By.xpath("//*/input[contains(@placeholder, 'Enter a Student id')]")).clear();
		driver.findElement(By.xpath("//*/input[contains(@placeholder, 'Enter a Student id')]")).sendKeys(studentId);			
	}
	
	public void clickGetStudentButton() {
		waitForElement(getStudentButton);
		clickElement(driver.findElement(getStudentButton));
	}
	
	public void verifyTanscriptDropDownList() { 
		waitForElement(transcriptDropDownListLabel);
	}

	public void verifyInstitutionNameInDropDownList(String isntitutionName) {
		waitForElement(By.xpath(".//select[@ng-change = 'Student.selectedTranscript.selected(Student)']/"
								+".//option[text() = 'Official College Transcript - " + isntitutionName + "']"));
	}
	
	public void selectFirstTranscriptFromDropDownList() {
		driver.findElement(transcriptDropDownList).findElement(By.xpath(".//option[2]")).click();
	}
	
	public void deleteAllCourses() {
		if(driver.findElements(militaryCourse).size() > 1){
			while (driver.findElements(militaryCourse).size() > 1 ) {
				int coursesCount = driver.findElements(militaryCourse).size();
				driver.findElements(militaryCourse).get(1).findElement(militaryCourseDeleteButton).click();
				clickAlertPopUpAcceptButton();
				while (coursesCount == driver.findElements(militaryCourse).size()) {
				}
			}			
		} else if (driver.findElements(collegeCourse).size() > 1 && driver.findElements(militaryCourse).size() == 0) {
			while (driver.findElements(collegeCourse).size() > 1 ) {
				int coursesCount = driver.findElements(collegeCourse).size();
				driver.findElements(collegeCourse).get(1).findElement(collegeCourseDeleteButton).click();
				clickAlertPopUpAcceptButton();
				while (coursesCount == driver.findElements(collegeCourse).size()) {
				}
			}						
		}
	}
	
	public void enterInstitutionName(String isntitutionName) {
		driver.findElement(institutionNameTextBox).clear();
		driver.findElement(institutionNameTextBox).sendKeys(isntitutionName);
	}
	
	public void selectFirstInstitution() {
		waitForElement(By.xpath("//*/label[contains(text(), 'Institution')]/following-sibling::ul/li[contains(@id, '-option-0')]"));
		clickElement(driver.findElement(By.xpath("//*/label[contains(text(), 'Institution')]/following-sibling::ul/li[contains(@id, '-option-0')]")));
	}

	public void selectTranscriptType(String transcriptType) {
		waitForElement(transcriptTypeDropDownList);
		selectFromDropDownByValue(driver.findElement(transcriptTypeDropDownList), transcriptTypeMap.get(transcriptType));
	}
	
	public void enterCollegeCourseCode(String courseCode) {
		waitForElement(collegeCourseCodeTextBox);
		driver.findElement(collegeCourseCodeTextBox).clear();
		driver.findElement(collegeCourseCodeTextBox).sendKeys(courseCode);
	}
		
	
	public void enterCollegeCourseTitle(String title) {
		waitForElement(collegeCourseTitleTextBox);
		driver.findElement(collegeCourseTitleTextBox).clear();
		driver.findElement(collegeCourseTitleTextBox).sendKeys(title);
	}
	
	public void selectCollegeCourseMonth(String month) {
		selectFromDropDownByValue(driver.findElement(collegeCourseMonthSelectList), monthMap.get(month));
	}
	
	public void selectCollegeCourseYear(String year) {
		selectFromDropDownByValue(driver.findElement(collegeCourseYearSelectList), yearMap.get(year));
	}

	public void enterCollegeCourseGrade(String grade) {
		waitForElement(collegeCourseGradeTextBox);
		driver.findElement(collegeCourseGradeTextBox).clear();
		driver.findElement(collegeCourseGradeTextBox).sendKeys(grade);
	}

	public void enterCollegeCourseCredit(String credit) {
		waitForElement(collegeCourseCreditsTextBox);
		driver.findElement(collegeCourseCreditsTextBox).clear();
		driver.findElement(collegeCourseCreditsTextBox).sendKeys(credit);
	}

	public void selectCollegeCourseCreditType(String creditType) {
		selectFromDropDownByValue(driver.findElement(collegeCourseCreditTypeSelectList), creditTypeMap.get(creditType));
	}

	public void selectCollegeCourseLevel(String level) {
		selectFromDropDownByValue(driver.findElement(collegeCourseLevelSelectList), levelMap.get(level));
	}

	public void enterCollegeCourseNote(String text) {
		waitForElement(collegeCourseNoteBox);
		driver.findElement(collegeCourseNoteBox).clear();
		driver.findElement(collegeCourseNoteBox).sendKeys(text);
	}

	public void verifyCollegeCourseCode(String courseCode) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td/span"))
				.getText().equalsIgnoreCase(courseCode));
	}

	public void verifyCollegeCourseTitle(String courseTitle) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[2]/span"))
				.getText().equalsIgnoreCase(courseTitle));
	}

	public void verifyCollegeCourseMonth(String courseMonth) {
		waitForElement(collegeCourse);
		String[] courseDateParts = driver.findElements(collegeCourse)
								.get(1)
								.findElement(By.xpath(".//td[3]/span"))
								.getText().split("/");
		Assert.assertEquals(monthMap.get(courseMonth).split(":")[1] , courseDateParts[0]);
	}

	public void verifyCollegeCourseYear(String courseYear) {
		waitForElement(collegeCourse);
		String[] courseDateParts = driver.findElements(collegeCourse)
								.get(1)
								.findElement(By.xpath(".//td[3]/span"))
								.getText().split("/");
		Assert.assertEquals(courseYear, courseDateParts[1]);
	}

	public void verifyCollegeCourseGrade(String courseGrade) {
		waitForElement(collegeCourse);
		Assert.assertEquals(courseGrade, driver.findElements(collegeCourse).get(1).findElement(By.xpath(".//td[4]/span")).getText());
	}

	public void verifyCollegeCourseCredits(String courseCredits) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[5]/span"))
				.getText().equalsIgnoreCase(courseCredits));
	}

	public void verifyCollegeCourseCreditType(String courseCreditType) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//select[@name = 'calendarSystemID']/option[@selected = 'selected']"))
				.getText().equalsIgnoreCase(courseCreditType));
	}

	public void verifyCollegeCourseLevel(String courseLevel) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//select[@name = 'level']/option[@selected = 'selected']"))
				.getText().equalsIgnoreCase(courseLevel));
	}

	public void verifyCollegeCourseNote(String note) {
		waitForElement(collegeCourse);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[8]/span"))
				.getText().equalsIgnoreCase(note));
	}

	public void enterCollegeCourseCodeInEditBox(String courseCode) {
		waitForElement(collegeCourseOkButton);
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td/.//input[@name = 'code']")).clear();
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td/.//input[@name = 'code']")).sendKeys(courseCode);
	}

	public void enterCollegeCourseTitleInEditBox(String title) {
		waitForElement(collegeCourseOkButton);
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[2]/.//input[@name = 'title']")).clear();
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[2]/.//input[@name = 'title']")).sendKeys(title);
	}

	public void selectCollegeCourseMonthInEditDropDownList(String month) {
		waitForElement(collegeCourseOkButton);
		selectFromDropDownByValue(driver.findElements(collegeCourse)
				.get(1).findElement(By.xpath(".//td[3]/.//select[@name = 'month']")), monthMap.get(month));
	}

	public void selectCollegeCourseYearInEditDropDownList(String year) {
		waitForElement(collegeCourseOkButton);
		selectFromDropDownByValue(driver.findElements(collegeCourse)
				.get(1).findElement(By.xpath(".//td[3]/.//select[@name = 'year']")), yearMap.get(year));
	}

	public void enterCollegeCourseGradeInEditBox(String Grade) {
		waitForElement(collegeCourseOkButton);
		driver.findElements(collegeCourse).get(1).findElement(By.xpath(".//td[4]/.//input[@name = 'grade']")).clear();
		driver.findElements(collegeCourse).get(1).findElement(By.xpath(".//td[4]/.//input[@name = 'grade']")).sendKeys(Grade);;
	}

	public void enterCollegeCourseCreditsInEditBox(String credits) {
		waitForElement(collegeCourseOkButton);
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[5]/.//input[@name = 'credits']")).clear();
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[5]/.//input[@name = 'credits']")).sendKeys(credits);
	}

	public void selectCollegeCourseCreditTypeInEditDropDownList(String creditType) {
		waitForElement(collegeCourseOkButton);
		selectFromDropDownByValue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[6]/.//select[@name = 'calendarSystemID']")), creditTypeMap.get(creditType));
	}
		
	public void selectCollegeCourseLevelInEditDropDownList(String level) {
		waitForElement(collegeCourseOkButton);
		selectFromDropDownByValue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[7]/.//select[@name = 'level']")), levelMap.get(level));
	}
	
	public void enterCollegeCourseNoteInEditBox(String note) {
		waitForElement(collegeCourse);
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[8]/.//textarea")).clear();
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[8]/.//textarea")).sendKeys(note);;
	}
	
	public void clickCollegeCourseSubmitButton() {
		List<WebElement> courses = driver.findElements(collegeCourse);
		driver.findElement(collegeCourseSubmitButton).click();
		while (courses.size() == driver.findElements(collegeCourse).size()) {
		}
		sleep(2);
	}
	
	public void clickCollegeCourseEditButton() {
		waitForElement(collegeCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(collegeCourseEditButton));
		sleep(2);
	}
	
	public void clickCollegeCourseCopyButton() {
		waitForElement(collegeCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(collegeCourseCopyButton));
		sleep(2);
	}
	
	public void clickCollegeCourseOkButton() {
		waitForElement(collegeCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(collegeCourseOkButton));
		waitForElement(collegeCourseEditButton);
		sleep(2);
	}
	
	public void clickCollegeCourseCancelButton() {
		waitForElement(collegeCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(collegeCourseCancelButton));
		waitForElement(collegeCourseEditButton);
	}
	
	public void verifyMilitaryCourseSubmitButtonDisable() {
		boolean isSubmitButtonDisable = true;
		try	{
			clickElement(driver.findElement(militaryCourseSubmitButton));
		} catch (ElementNotClickableException e) {
			isSubmitButtonDisable = false;
		}	
		Assert.assertFalse(isSubmitButtonDisable);
	}
		
	public void verifyCollegeCourseSubmitButtonDisable() {
		boolean isSubmitButtonDisable = true;
		try	{
			clickElement(driver.findElement(collegeCourseSubmitButton));
		} catch (ElementNotClickableException e) {
			isSubmitButtonDisable = false;
		}	
		Assert.assertFalse(isSubmitButtonDisable);
	}
	
	public void verifyCollegeCourseCodeInTextBox(String courseCode) {
		Assert.assertTrue(driver.findElement(collegeCourseCodeTextBox)
						.getAttribute("value").equalsIgnoreCase(courseCode));
	}
		
	public void verifyCollegeCourseTitleInTextBox(String courseTitle) {
		Assert.assertTrue(driver.findElement(collegeCourseTitleTextBox)
				.getAttribute("value")
				.equalsIgnoreCase(courseTitle));
	}
		
	public void verifyCollegeCourseMonthInDropDownList(String courseMonth) {
		Assert.assertTrue(driver.findElement(collegeCourseMonthSelectList).getAttribute("value").equalsIgnoreCase(monthMap.get(courseMonth)));
	}
	
	public void verifyCollegeCourseYearInDropDownList(String courseYear) {
		Assert.assertTrue(driver.findElement(collegeCourseYearSelectList).getAttribute("value").equalsIgnoreCase(yearMap.get(courseYear)));
	}
		
	public void verifyCollegeCourseGradeInTextBox(String courseGrade) {
		Assert.assertTrue(driver.findElement(collegeCourseGradeTextBox)
						.getAttribute("value")
						.equalsIgnoreCase(courseGrade));
	}
		
	public void verifyCollegeCourseCreditsInTextBox(String courseCredit) {			
		Assert.assertTrue(driver.findElement(collegeCourseCreditsTextBox)
						.getAttribute("value").equalsIgnoreCase(courseCredit));
	}
		
	public void verifyCollegeCourseCreditTypeInDropDownList(String courseCreditType) {
		Assert.assertTrue(driver.findElement(collegeCourseCreditTypeSelectList)
						.getAttribute("value")
						.equalsIgnoreCase(creditTypeMap.get(courseCreditType)));
	}
			
	public void verifyCollegeCourseLevelInDropDownList(String courseLevel) {
		Assert.assertTrue(driver.findElement(collegeCourseLevelSelectList)
						.getAttribute("value")
						.equalsIgnoreCase(levelMap.get(courseLevel)));
	}
		
	public void verifyCollegeCourseNoteInTextBox(String note) {
		Assert.assertTrue(driver.findElement(collegeCourseNoteBox)
				.getAttribute("value").equalsIgnoreCase(note));
	}
	
	public void verifyAutoCompleteListCollegeCourseCode(String courseCode) {
		boolean courseCodeIsDisplayed = false;
		waitForElement(collegeAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(collegeAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding']"))
				.getText().equalsIgnoreCase(courseCode)) {
				courseCodeIsDisplayed = true;
			}
		}
		Assert.assertTrue(courseCodeIsDisplayed);
	}
	
	public void verifyAutoCompleteListCollegeCourseTitle(String coursetitle) {
		boolean coursetitleIsDisplayed = false;
		waitForElement(collegeAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(collegeAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-5 ng-binding']"))
				.getText().equalsIgnoreCase(coursetitle)) {
				coursetitleIsDisplayed = true;
			}
		}
		Assert.assertTrue(coursetitleIsDisplayed);
	}
	
	public void verifyAutoCompleteListCollegeCourseCredits(String credits) {
		boolean courseCreditsAreDisplayed = false;
		waitForElement(collegeAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(collegeAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//span[@class = 'badge ng-binding']"))
				.getText().equalsIgnoreCase(credits)) {
				courseCreditsAreDisplayed = true;
			}
		}
		Assert.assertTrue(courseCreditsAreDisplayed);
	}
	
	public void verifyAutoCompleteListCollegeCourseCreditType(String creditType) {
		boolean courseCreditTypeIsDisplayed = false;
		waitForElement(collegeAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(collegeAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 text-right ng-binding']"))
				.getText().equalsIgnoreCase(creditType)) {
				courseCreditTypeIsDisplayed = true;
			}
		}
		Assert.assertTrue(courseCreditTypeIsDisplayed);
	}
	
	public void verifyAutoCompleteListCollegeCourseLevel(String level) {
		boolean courseLevelIsDisplayed = false;
		waitForElement(collegeAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(collegeAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding'][2]"))
				.getText().equalsIgnoreCase(level)) {
				courseLevelIsDisplayed = true;
			}
		}
		Assert.assertTrue(courseLevelIsDisplayed);
	}
	
	public void enterMilitaryRank(String militaryRank) {
		waitForElement(militaryRankTextBox);
		driver.findElement(militaryRankTextBox).clear();
		driver.findElement(militaryRankTextBox).sendKeys(militaryRank);
	}
	
	public void enterMilitaryStatus(String militaryStatus) {
		waitForElement(militaryStatusTextBox);
		driver.findElement(militaryStatusTextBox).clear();
		driver.findElement(militaryStatusTextBox).sendKeys(militaryStatus);
	}
	
	public void enterMilitaryDateIssued(String dateIssued) {
		waitForElement(militaryDateIssuedCalendarBox);
		driver.findElement(militaryDateIssuedCalendarBox).sendKeys(dateIssued);
	}
	
	public void enterMilitaryCourseNumber(String courseCode) {
		waitForElement(militaryCourseCodeTextBox);
		driver.findElement(militaryCourseCodeTextBox).clear();
		driver.findElement(militaryCourseCodeTextBox).sendKeys(courseCode);
	}
	
	public void enterMilitaryCourseACEExhibitNumber(String exhibitNumber) {
		waitForElement(militaryExhibitNumberTextBox);
		driver.findElement(militaryExhibitNumberTextBox).clear();
		driver.findElement(militaryExhibitNumberTextBox).sendKeys(exhibitNumber);
	}

	public void enterMilitaryCourseTitle(String title) {
		waitForElement(militaryCourseTitleTextBox);
		driver.findElement(militaryCourseTitleTextBox).clear();
		driver.findElement(militaryCourseTitleTextBox).sendKeys(title);
	}
	
	public void selectMilitaryCourseMonth(String month) {
		selectFromDropDownByValue(driver.findElement(militaryCourseMonthSelectList), monthMap.get(month));
	}
	
	public void selectMilitaryCourseYear(String year) {
		selectFromDropDownByValue(driver.findElement(militaryCourseYearSelectList), yearMap.get(year));
	}

	public void enterMilitaryCourseACECreditRecommendation(String credit) {
		driver.findElement(militaryACECreditRecommendationTextBox).clear();
		driver.findElement(militaryACECreditRecommendationTextBox).sendKeys(credit);
	}

	public void enterMilitaryCourseCredit(String credit) {
		waitForElement(militaryCourseCreditsTextBox);
		driver.findElement(militaryCourseCreditsTextBox).clear();
		driver.findElement(militaryCourseCreditsTextBox).sendKeys(credit);
	}
	
	public void selectMilitaryCourseCreditType(String creditType) {
		selectFromDropDownByValue(driver.findElement(militaryCourseCreditTypeSelectList), creditTypeMap.get(creditType));
	}
	
	public void selectMilitaryCourseLevel(String level) {
		selectFromDropDownByValue(driver.findElement(militaryCourseLevelDropDownList), militaryLevelMap.get(level));
	}

	public void selectMilitaryCourseSkillLevel(String skillLevel) {
		selectFromDropDownByValue(driver.findElement(militaryCourseSkillLevelDropDownList), militarySkillLevelMap.get(skillLevel));
	}

	public void enterMilitaryCourseNote(String text) {
		waitForElement(militaryCourseNoteBox);
		driver.findElement(militaryCourseNoteBox).clear();
		driver.findElement(militaryCourseNoteBox).sendKeys(text);
	}
	
	public void clickMilitaryCourseSubmitButton() {
		List<WebElement> courses = driver.findElements(militaryCourse);
		driver.findElement(militaryCourseSubmitButton).click();
		while (courses.size() == driver.findElements(militaryCourse).size()) {
		}
		sleep(2);
	}
	
	public void verifyMilitaryCourseNumber(String courseCode) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td/span"))
				.getText().equalsIgnoreCase(courseCode));
	}
	
	public void verifyMilitaryCourseACEExhibitNumber(String aceExhibitNumber) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(collegeCourse)
				.get(1)
				.findElement(By.xpath(".//td[2]/span"))
				.getText().equalsIgnoreCase(aceExhibitNumber));
	}

	public void verifyMilitaryCourseTitle(String courseTitle) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[3]/span"))
				.getText().equalsIgnoreCase(courseTitle));
	}

	public void verifyMilitaryCourseMonth(String courseMonth) {
		waitForElement(militaryCourseEditButton);
		String[] courseDateParts = driver.findElements(militaryCourse).get(1).findElement(By.xpath(".//td[4]/span")).getText().split("/");
		Assert.assertTrue(courseDateParts[0].equalsIgnoreCase(monthMap.get(courseMonth).split(":")[1]));
	}
	
	public void verifyMilitaryCourseYear(String courseYear) {
		waitForElement(militaryCourseEditButton);
		String[] courseDateParts = driver.findElements(militaryCourse).get(1).findElement(By.xpath(".//td[4]/span")).getText().split("/");
		Assert.assertTrue(courseDateParts[1].equalsIgnoreCase(courseYear));
	}

	public void verifyMilitaryCourseACECreditRecommendation(String creditRecommendation) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[5]/span"))
				.getText().equalsIgnoreCase(creditRecommendation));
	}

	public void verifyMilitaryCourseCredits(String courseCredits) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[6]/span"))
				.getText().equalsIgnoreCase(courseCredits));
	}
	
	public void verifyMilitaryCourseCreditType(String creditType) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//select[@name = 'calendarSystemID']/option[@selected = 'selected']"))
				.getText().equalsIgnoreCase(creditType));
	}
	
	public void verifyMilitaryCourseLevel(String level) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//select[@ng-model = 'course.LevelID']/option[@selected = 'selected']"))
				.getText().equalsIgnoreCase(level));
	}
	
	public void verifyMilitaryCourseSkillLevel(String skillLevel) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//select[@ng-model = 'course.SkillLevel']/option[@selected = 'selected']"))
				.getText().equalsIgnoreCase(skillLevel));
	}

	public void verifyMilitaryCourseNote(String note) {
		waitForElement(militaryCourseEditButton);
		Assert.assertTrue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[10]/span"))
				.getText().equalsIgnoreCase(note));
	}

	public void clickMilitaryCourseEditButton() {
		waitForElement(militaryCourse);
		clickElement(driver.findElements(militaryCourse).get(1).findElement(militaryCourseEditButton));
		waitForElement(militaryCourseOkButton);
	}
	
	public void clickMilitaryCourseCopyButton() {
		waitForElement(militaryCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(militaryCourseCopyButton));
	}
	
	public void clickMilitaryCourseOkButton() {
		waitForElement(militaryCourse);
		clickElement(driver.findElements(collegeCourse).get(1).findElement(militaryCourseOkButton));
		waitForElement(collegeCourseEditButton);
	}
	
	public void clickMilitaryCourseCancelButton() {
		waitForElement(militaryCourse);
		clickElement(driver.findElements(militaryCourse).get(1).findElement(militaryCourseCancelButton));
		waitForElement(collegeCourseEditButton);
	}
		
	public void enterMilitaryCourseNumberInEditBox(String courseCode) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td/.//input[@name = 'militaryCourseNo']")).clear();
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td/.//input[@name = 'militaryCourseNo']")).sendKeys(courseCode);
	}
	
	public void enterMilitaryCourseACEExhibitNumberInEditBox(String exhibitNumber) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[2]/.//input[@name = 'code']")).clear();
		driver.findElements(militaryCourse)
		.get(1)		
		.findElement(By.xpath(".//td[2]/.//input[@name = 'code']")).sendKeys(exhibitNumber);
	}
	
	public void enterMilitaryCourseTitleInEditBox(String title) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[3]/.//input[@name = 'title']")).clear();
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[3]/.//input[@name = 'title']")).sendKeys(title);
	}
	
	public void selectMilitaryCourseMonthInEditDropDownList(String month) {
		waitForElement(militaryCourseOkButton);
		selectFromDropDownByValue(driver.findElements(militaryCourse)
				.get(1).findElement(By.xpath(".//td[4]/.//select[@name = 'month']")), monthMap.get(month));
	}
	
	public void selectMilitaryCourseYearInEditDropDownList(String year) {
		waitForElement(militaryCourse);
		selectFromDropDownByValue(driver.findElements(militaryCourse)
				.get(1).findElement(By.xpath(".//td[4]/.//select[@name = 'year']")), yearMap.get(year));
	}
	
	public void enterMilitaryCourseACECreditRecommendationInEditBox(String credit) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[5]/.//input[@name = 'aceCreditRecommend']")).clear();
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[5]/.//input[@name = 'aceCreditRecommend']")).sendKeys(credit);
	}

	public void enterMilitaryCourseCreditsInEditBox(String credits) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[6]/.//input[@name = 'credits']")).clear();
		driver.findElements(collegeCourse)
		.get(1)
		.findElement(By.xpath(".//td[6]/.//input[@name = 'credits']")).sendKeys(credits);
	}
	
	public void selectMilitaryCourseCreditTypeInEditDropDownList(String creditType) {
		waitForElement(militaryCourseOkButton);
		selectFromDropDownByValue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[7]/.//select[@name = 'calendarSystemID']")), creditTypeMap.get(creditType));
	}
	
	public void selectMilitaryCourseLevelInDropDownEditList(String level) {
		waitForElement(militaryCourseOkButton);
		selectFromDropDownByValue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[8]/.//select[@ng-model= 'course.LevelID']")), militaryLevelMap.get(level));
	}
	
	public void selectMilitaryCourseSkillLevelInDropDownEditList(String skillLevel) {
		waitForElement(militaryCourseOkButton);
		selectFromDropDownByValue(driver.findElements(militaryCourse)
				.get(1)
				.findElement(By.xpath(".//td[9]/.//select[@ng-model= 'course.SkillLevel']")), militarySkillLevelMap.get(skillLevel));
	}

	public void enterMilitaryCourseNoteInEditBox(String note) {
		waitForElement(militaryCourseOkButton);
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[10]/.//textarea")).clear();
		driver.findElements(militaryCourse)
		.get(1)
		.findElement(By.xpath(".//td[10]/.//textarea")).sendKeys(note);;
	}
				
	public void verifyMilitaryCourseNumberInTextBox(String courseCode) {
		Assert.assertTrue(driver.findElement(militaryCourseCodeTextBox)
						.getAttribute("value").equalsIgnoreCase(courseCode));
	}
	
	public void verifyMilitaryCourseACEExhibitNumberInTextBox(String aceExhibitNumber) {
		Assert.assertTrue(driver.findElement(militaryExhibitNumberTextBox)
						.getAttribute("value").equalsIgnoreCase(aceExhibitNumber));
	}
	
	public void verifyMilitaryCourseTitleInTextBox(String courseTitle) {
		Assert.assertTrue(driver.findElement(militaryCourseTitleTextBox)
				.getAttribute("value")
				.equalsIgnoreCase(courseTitle));
	}
		
	public void verifyMilitaryCourseMonthInDropDownList(String courseMonth) {
		Assert.assertTrue(driver.findElement(militaryCourseMonthSelectList).getAttribute("value").equalsIgnoreCase(monthMap.get(courseMonth)));
	}
	
	public void verifyMilitaryCourseYearInDropDownList(String courseYear) {
		Assert.assertTrue(driver.findElement(militaryCourseYearSelectList).getAttribute("value").equalsIgnoreCase(yearMap.get(courseYear)));
	}
	
	
	public void verifyMilitaryCourseACECreditRecommendationInTextBox(String aceCreditRecommendation) {
		Assert.assertTrue(driver.findElement(militaryACECreditRecommendationTextBox)
						.getAttribute("value").equalsIgnoreCase(aceCreditRecommendation));
	}
		
	public void verifyMilitaryCourseCreditsInTextBox(String courseCredit) {			
		Assert.assertTrue(driver.findElement(militaryCourseCreditsTextBox)
						.getAttribute("value").equalsIgnoreCase(courseCredit));
	}
		
	public void verifyMilitaryCourseCreditTypeInDropDownList(String courseCreditType) {
		Assert.assertTrue(driver.findElement(militaryCourseCreditTypeSelectList)
						.getAttribute("value")
						.equalsIgnoreCase(creditTypeMap.get(courseCreditType)));
	}	
	
	public void verifyMilitaryCourseLevelInDropDownList(String courseLevel) {
		Assert.assertTrue(driver.findElement(militaryCourseLevelDropDownList)
						.getAttribute("value")
						.equalsIgnoreCase(militaryLevelMap.get(courseLevel)));
	}
	
	public void verifyMilitaryCourseSkillLevelInDropDownList(String courseSkillLevel) {
		Assert.assertTrue(driver.findElement(militaryCourseSkillLevelDropDownList)
						.getAttribute("value")
						.equalsIgnoreCase(militarySkillLevelMap.get(courseSkillLevel)));
	}
	
	public void verifyMilitaryCourseNoteInTextBox(String note) {
		Assert.assertTrue(driver.findElement(militaryCourseNoteBox)
				.getAttribute("value").equalsIgnoreCase(note));
	}
	
	public void verifyAutoCompleteListMilitaryCourseNumber(String courseNumber) {
		boolean isCourseNumberIsDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding']"))
				.getText().equalsIgnoreCase(courseNumber)) {
				isCourseNumberIsDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseNumberIsDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseAceExhibitNumber(String courseNumber) {
		boolean isCourseNumberDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding'][2]"))
				.getText().equalsIgnoreCase(courseNumber)) {
				isCourseNumberDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseNumberDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseTitle(String courseTitle) {
		boolean isCourseTitleIsDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-3 ng-binding']"))
				.getText().equalsIgnoreCase(courseTitle)) {
				isCourseTitleIsDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseTitleIsDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseAceCreditRecommendation(String AceCreditRecommendation) {
		boolean isCourseAceCreditRecommendationDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-1 ng-binding']"))
				.getText().equalsIgnoreCase(AceCreditRecommendation)) {
				isCourseAceCreditRecommendationDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseAceCreditRecommendationDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseCredits(String credits) {
		boolean isCourseCreditsDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//span[@class = 'badge ng-binding']"))
				.getText().equalsIgnoreCase(credits)) {
				isCourseCreditsDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseCreditsDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseCreditType(String creditType) {
		boolean isCourseCreditTypeDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding'][3]"))
				.getText().equalsIgnoreCase(creditType)) {
				isCourseCreditTypeDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseCreditTypeDisplayed);
	}
	
	public void verifyAutoCompleteListMilitaryCourseLevel(String level) {
		boolean isCourseLevelDisplayed = false;
		waitForElement(militaryAutoCompleteCourse);
		List<WebElement> courses = driver.findElements(militaryAutoCompleteCourse);
		for(WebElement course : courses) {
			if(course.findElement(By.xpath(".//div[@class = 'col-xs-1 text-right ng-binding']"))
				.getText().equalsIgnoreCase(level)) {
				isCourseLevelDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseLevelDisplayed);
	}
}
