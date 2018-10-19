package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class DegreeProgramManagerPage extends Page{

	private By savePublishDropDownList = By.xpath(".//small/.//span[@class = 'ng-scope' and contains(text(), 'save/publish')]");
	private By dropDownListSaveButton = By.xpath(".//a[@ng-click = 'save()']");
	private By coursesButton = By.xpath(".//a[@title = 'Course builder']");
	private By sequenceButton = By.xpath(".//a[@title = 'Course sequencer']");
	private By sequencePageHeader = By.xpath(".//strong[text() = 'SEQUENCE']");
	private By sequencePageCourse = By.xpath(".//li[@ng-repeat = 'course in orderedCourses']");
	private By degreeProgramManagerPageHeader = By.xpath(".//strong[contains(text(), 'TOTAL PROGRAM REQUIREMENTS')]");
	private By DegreeProgramSearchBox = By.xpath("//input[contains(@placeholder, 'Search for Degree Programs by Name')]");
	private By searchCourseTab = By.xpath(".//a[text() = 'Search Courses']");
	private By searchGroupTab = By.xpath(".//a[text() = 'Search Groups']");
	private By courseSearchBox = By.xpath(".//input[@placeholder = 'Search for Courses by Name']");
	private By groupSearchBox = By.xpath(".//input[@placeholder = 'Search for Course Groups by name']");
	private By course = By.xpath(".//li[contains(@ng-repeat, 'course in selectedCourses')]");
	private By group = By.xpath(".//li[@ng-repeat = 'match in matches track by $index']");
	private By selectedGroup = By.xpath(".//li[@ng-model = 'selectedCourseGroup']");
	private By category = By.xpath("//div[contains(@ng-repeat, 'category in program.Categories')]");
	private By categoryCourseDropBox = By.xpath(".//li[@ng-model = 'category.Courses']/p[contains(text(), 'Drop course here')]");
	private By categoryCourse = By.xpath(".//li[contains(@ng-repeat, 'course in category.Courses')]");
	private By addNewCategoryButton = By.xpath(".//button[contains(.,'Add new category')]");
	private By addnewCategoryModal = By.xpath(".//*[contains(text(),'Create Category')]");
	private By modalEnterNameBox = By.id("cgPromptInput");
	private By modalOkButton = By.xpath("//div[@class = 'modal-footer']/.//button[contains(.,'OK')]");
	private By modalCancelButton = By.xpath("//div[@class = 'modal-footer']/.//button[contains(.,'Cancel')]");
	private By categoryDeleteButton = By.xpath(".//button[contains(@ng-click, 'removeGroup')]");
	private By categoryEditButton = By.xpath(".//button[contains(@ng-click, 'editItemName')]");
	private By electivePoolButton = By.xpath(".//button[contains(@ng-click, 'createElectivePool')]");
	private By electivePool = By.xpath(".//div[@ng-repeat = 'electivePool in category.ElectivePools']");
	private By electivePoolCourse = By.xpath(".//li[contains(@ng-repeat, 'course in electivePool.Courses')]");
	private By courseModularizeButton = By.xpath(".//div[@class = 'col-md-5 ng-scope']/.//button[text() = 'Modularize']");
	private By courseUnmodularizeButton = By.xpath(".//div[@class = 'col-md-5 ng-scope']/.//button[text() = 'Unmodularize']");
	private By electivePoolGroup = By.xpath(".//div[@ng-repeat = 'courseGroup in electivePool.CourseGroups']");
	private By electivePoolCourseDropBox = By.xpath(".//li[@ng-model = 'electivePool.Courses']/p[contains(text(), 'Drop course here')]");
	private By electivePoolGroupDropBox = By.xpath(".//li[@ng-model = 'electivePool.CourseGroups']/p[contains(text(), 'Drop course group here')]");
	private By electivePoolCourseSequenceCheckBox = By.xpath(".//div[@class = 'col-md-5 ng-scope']/.//span[@ng-if = '::showCheckbox']/.//input[@type = 'checkbox']");
	private By modularizeModal = By.xpath(".//div[@class = 'modal-content']");
	private By okButton = By.xpath(".//button[text() = 'Ok']");
	private By cancelButton = By.xpath(".//button[text() = 'Cancel']");

	public DegreeProgramManagerPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterProgramName(String degreeProgramName)
	{
		waitForElement(DegreeProgramSearchBox);
		driver.findElement(DegreeProgramSearchBox).sendKeys(degreeProgramName);
	}
	
	public void selectFirstItemFromProgramDropDownList() {
		waitForElement(By.xpath("//li[contains(@id, 'option-0')]"));
		clickElement(driver.findElement(By.xpath("//li[contains(@id, 'option-0')]")));
	}
	
	public void verifyDegreeProgramName(String degreeProgramName) {
		waitForElement(By.xpath("//a[contains(@ng-click, 'User.Program.isSet') and contains(text(), '" + degreeProgramName + "')]"));
	}
	
	public void clickCoursesButton() {
		clickElement(driver.findElement(coursesButton));
	}
	
	public void clickSequenceButton() {
		clickElement(driver.findElement(sequenceButton));
	}
	
	public void clickSaveButton() {
		if (driver.findElements(savePublishDropDownList).size() == 0) {
			return;
		}
		clickElement(driver.findElement(savePublishDropDownList));
		waitForElement(dropDownListSaveButton);
		clickElement(driver.findElement(dropDownListSaveButton));
		while (driver.findElements(savePublishDropDownList).size() > 0) {
		}
	}
	
	public void verifySequencePage() {
		waitForElement(sequencePageHeader);			
	}

	public void verifyDegreeProgramManagerPage() {
		waitForElement(degreeProgramManagerPageHeader);			
	}
	
	public void clickSearchCourseTab() {
		clickElement(driver.findElement(searchCourseTab));	
	}
	
	public void clickSearchGroupTab() {
		clickElement(driver.findElement(searchGroupTab));	
	}
	
	public void enterCourseCode(String courseCode) {
		waitForElement(courseSearchBox);
		driver.findElement(courseSearchBox).clear();
		sleep(2);
		driver.findElement(courseSearchBox).sendKeys(courseCode);
		sleep(5);
		waitForElement(course);
		Assert.assertTrue(driver.findElement(course).findElement(By.xpath(".//div[@class = 'col-md-2 ng-binding']")).getText().equalsIgnoreCase(courseCode));	
	}
	
	public void enterGroupName(String groupName) {
		waitForElement(groupSearchBox);
		driver.findElement(groupSearchBox).clear();
		driver.findElement(groupSearchBox).sendKeys(groupName);
		waitForElement(group);
		driver.findElement(group).findElement(By.xpath(".//a/strong[contains(text(), '" + groupName + "')]"));
		driver.findElement(group).click();
	}
	
	public void dragAndDropCourseToCategory(String courseName, String categoryName) {
		WebElement element = driver.findElement(course)
							.findElement(By.xpath(".//div[@class = 'col-md-8 ng-binding'and contains(., '" + courseName + "')]"));	
	
		driver.findElements(category).get(driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));
		
		WebElement target = driver.findElements(category)
							.get(driver.findElements(category).size() - 1)
							.findElement(categoryCourseDropBox);

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void dragAndDropCourseToElectivePool(String courseName, String electivePoolName, String categoryName) {
		Assert.assertTrue(driver.findElement(course)
		.findElement(By.xpath(".//div[@class = 'col-md-8 ng-binding']")).getText().equalsIgnoreCase(courseName));

		WebElement element = driver.findElement(course);
			
		driver.findElements(category).get(driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));

		List<WebElement> categoryElectivePools =  driver.findElements(category)
												.get(driver.findElements(category).size() - 1)
												.findElements(electivePool);
		
		categoryElectivePools.get(categoryElectivePools.size() - 1).findElement(By.xpath(".//span[contains(text(), '" + electivePoolName + "')]"));
		
		WebElement target = categoryElectivePools
							.get(categoryElectivePools.size() - 1)
							.findElement(electivePoolCourseDropBox);

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void dragAndDropGroupToElectivePool(String groupName, String electivePoolName, String categoryName) {
		WebElement element = driver.findElement(selectedGroup)
							.findElement(By.xpath(".//span[@class = 'course-title ng-binding' and contains(text(), '" + groupName + "')]"));	
	
		driver.findElements(category).get(driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));

		List<WebElement> categoryElectivePools =  driver.findElements(category)
												.get(driver.findElements(category).size() - 1)
												.findElements(electivePool);
		
		categoryElectivePools.get(categoryElectivePools.size() - 1).findElement(By.xpath(".//span[contains(text(), '" + electivePoolName + "')]"));
		
		WebElement target = categoryElectivePools
							.get(categoryElectivePools.size() - 1)
							.findElement(electivePoolGroupDropBox);

		(new Actions(driver)).dragAndDrop(element, target).perform();
	}
	
	public void verifyCategoryCourseAdded(String courseName, String categoryName) {
		driver.findElements(category).get( driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));	

		List<WebElement> categoryCourses =	driver.findElements(category).get(driver.findElements(category).size() - 1)
		.findElements(categoryCourse);
		
		String actualCourseName = categoryCourses.get(categoryCourses.size() -1).findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[@ng-if = '::!course.EquivalentCourse']/span")).getText();
		
		Assert.assertTrue(actualCourseName.contains(courseName));
	}

	public void verifyCategoryCourseUnmodularizeButton(String courseName, String categoryName) {
		boolean isModularizeButtonDisplayed = false;
		waitForElement(category);
		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
			
		List<WebElement> courses =  categories.get(categories.size() - 1).findElements(categoryCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseUnmodularizeButton);
				isModularizeButtonDisplayed = true;
				} catch (Exception e) {
				}		
		}			
		Assert.assertTrue(isModularizeButtonDisplayed);	
	}	
	
	public void clickCategoryCourseUnmodularizeButton(String courseName, String categoryName) {
		waitForElement(category);
		List<WebElement> programModules = driver.findElements(category);
		Assert.assertTrue(programModules.get(programModules.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
			
		List<WebElement> courses =  programModules.get(programModules.size() - 1).findElements(categoryCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseUnmodularizeButton).click();			
				} catch (Exception e) {
				}
		}			
	
	}

	public void verifyCategoryCourseModularizeButton(String courseName, String categoryName) {
		boolean isModularizeButtonDisplayed = false;
		waitForElement(category);

		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
			
		List<WebElement> courses =  categories.get(categories.size() - 1).findElements(categoryCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseModularizeButton);
				isModularizeButtonDisplayed = true;
				} catch (Exception e) {
				}		
		}			
		Assert.assertTrue(isModularizeButtonDisplayed);	
	}	
	
	public void clickCategoryCourseModularizeButton(String courseName, String categoryName) {
		waitForElement(category);

		List<WebElement> programModules = driver.findElements(category);
		Assert.assertTrue(programModules.get(programModules.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
			
		List<WebElement> courses =  programModules.get(programModules.size() - 1).findElements(categoryCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseModularizeButton).click();			
				} catch (Exception e) {
				}
		}			
	
	}
	
	public void verifyElectivePoolCourseAdded(String courseName, String electivePoolName, String categoryName) {
		boolean isCourseAdded = false;

		driver.findElements(category).get( driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));	
		List<WebElement> categoryElectivePools =  driver.findElements(category)
				.get(driver.findElements(category).size() - 1)
				.findElements(electivePool);

		categoryElectivePools.get(categoryElectivePools.size() - 1).findElement(By.xpath(".//span[contains(text(), '" + electivePoolName + "')]"));

		List<WebElement> categoryElectivePoolCourses =	categoryElectivePools.get(categoryElectivePools.size() - 1)
		.findElements(electivePoolCourse);
		
		for(WebElement categoryElectivePoolCourse : categoryElectivePoolCourses) {
			if(categoryElectivePoolCourse.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[@ng-if = '::!course.EquivalentCourse']/span")).getText().equalsIgnoreCase(courseName)) {
				isCourseAdded = true;
			}
		}
		
		Assert.assertTrue(isCourseAdded);
		
	}
		
	public void verifyElectivePoolGroupAdded(String groupName, String electivePoolName, String categoryName) {
		boolean isGroupAdded = false;

		driver.findElements(category).get( driver.findElements(category).size() - 1).findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));	
		List<WebElement> categoryElectivePools =  driver.findElements(category)
				.get(driver.findElements(category).size() - 1)
				.findElements(electivePool);

		categoryElectivePools.get(categoryElectivePools.size() - 1).findElement(By.xpath(".//span[contains(text(), '" + electivePoolName + "')]"));

		List<WebElement> categoryElectivePoolGroups =	categoryElectivePools.get(categoryElectivePools.size() - 1)
		.findElements(electivePoolGroup);
		
		for(WebElement categoryElectivePoolGroup : categoryElectivePoolGroups) {
			if(categoryElectivePoolGroup.findElement(By.xpath(".//span[@class = 'course-title ng-binding']")).getText().equalsIgnoreCase(groupName)) {
				isGroupAdded = true;
			}
		}
		
		Assert.assertTrue(isGroupAdded);		
	}

	public void verifyElectivePoolCourseModularizeButtonUnder(String courseName, String electiveName, String categoryName) {
		boolean isModularizeButtonDisplayed = false;
		
		waitForElement(category);
		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
	
		List<WebElement> moduleElectivePools =  categories.get(categories.size() - 1).findElements(electivePool);
		Assert.assertTrue(moduleElectivePools.get(moduleElectivePools.size() - 1).findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electiveName));	
			
		List<WebElement> courses =  moduleElectivePools.get(moduleElectivePools.size() - 1).findElements(electivePoolCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseModularizeButton);
				isModularizeButtonDisplayed = true;
				} catch (Exception e) {
				}		
		}					
		Assert.assertTrue(isModularizeButtonDisplayed);	
	}
	
	public void clickElectivePoolCourseModularizeButton(String courseName, String electiveName, String categoryName) {
		waitForElement(category);

		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
	
		List<WebElement> moduleElectivePools =  categories.get(categories.size() - 1).findElements(electivePool);
		Assert.assertTrue(moduleElectivePools.get(moduleElectivePools.size() - 1).findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electiveName));	
			
		List<WebElement> courses =  categories.get(categories.size() - 1).findElements(electivePoolCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseModularizeButton).click();

				} catch (Exception e) {
				}		
		}
	}
	
	public void verifyElectivePoolCourseUnmodularizeButton(String courseName, String electiveName, String categoryName) {
		boolean isModularizeButtonDisplayed = false;
		waitForElement(category);

		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
	
		List<WebElement> moduleElectivePools =  categories.get(categories.size() - 1).findElements(electivePool);
		Assert.assertTrue(moduleElectivePools.get(moduleElectivePools.size() - 1).findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electiveName));	
			
		List<WebElement> courses =  moduleElectivePools.get(moduleElectivePools.size() - 1).findElements(electivePoolCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseUnmodularizeButton);
				isModularizeButtonDisplayed = true;
				} catch (Exception e) {
				}		
		}					
		Assert.assertTrue(isModularizeButtonDisplayed);	
	}
	
	public void clickElectivePoolCourseUnmodularizeButton(String courseName, String electiveName, String categoryName) {
		waitForElement(category);

		List<WebElement> categories = driver.findElements(category);
		Assert.assertTrue(categories.get(categories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[@class = 'ng-binding']")).getText().contains(categoryName));	
	
		List<WebElement> moduleElectivePools =  categories.get(categories.size() - 1).findElements(electivePool);
		Assert.assertTrue(moduleElectivePools.get(moduleElectivePools.size() - 1).findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electiveName));	
			
		List<WebElement> courses =  categories.get(categories.size() - 1).findElements(electivePoolCourse);
		
		for (WebElement course : courses ) {
			try {
				course.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[contains(text(), '" + courseName + "')]"));
				course.findElement(courseUnmodularizeButton).click();

				} catch (Exception e) {
				}		
		}
	}

	public void verifyModularizeModal() {
		waitForElement(modularizeModal);
	}
	
	public void clickModularizeModalOkButton() {
		clickElement(driver.findElement(modularizeModal).findElement(okButton));
	}
	
	public void clickModularizeModalCancelButton() {
		clickElement(driver.findElement(modularizeModal).findElement(cancelButton));
	}
	
	public void checkCourseSequenceButton(String courseName, String electivePoolName, String categoryName) {
		List<WebElement> categories = driver.findElements(category);
		for (WebElement category : categories) {
			if (category.findElement(By.xpath(".//div[@class = 'col-md-6']/.//span[@class = 'ng-binding']")).getText().contains(categoryName)){
				List<WebElement> electivePools =  category.findElements(electivePool);
				for (WebElement electivePool : electivePools) {
					if (electivePool.findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electivePoolName)){
						List<WebElement> electivePoolCourses = electivePool.findElements(electivePoolCourse);
						for (WebElement electivePoolCourse : electivePoolCourses) {
							if (electivePoolCourse.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[@ng-if = '::!course.EquivalentCourse']/span")).getText().equalsIgnoreCase(courseName)){
								if(electivePoolCourse.findElement(electivePoolCourseSequenceCheckBox).getAttribute("class").contains("ng-empty") ) {
									electivePoolCourse.findElement(electivePoolCourseSequenceCheckBox).click();	
								}
												
							}
					
						}
					}
				}
			}
		}
	}
	
	public void uncheckCourseSequenceButton(String courseName, String electivePoolName, String categoryName) {
		List<WebElement> categories = driver.findElements(category);
		for (WebElement category : categories) {
			if (category.findElement(By.xpath(".//div[@class = 'col-md-6']/.//span[@class = 'ng-binding']")).getText().contains(categoryName)){
				List<WebElement> electivePools =  category.findElements(electivePool);
				for (WebElement electivePool : electivePools) {
					if (electivePool.findElement(By.xpath(".//span[@class = 'ng-binding']")).getText().contains(electivePoolName)){
						List<WebElement> electivePoolCourses = electivePool.findElements(electivePoolCourse);
						for (WebElement electivePoolCourse : electivePoolCourses) {
							if (electivePoolCourse.findElement(By.xpath(".//div[@class = 'col-md-5']/.//span[@ng-if = '::!course.EquivalentCourse']/span")).getText().equalsIgnoreCase(courseName)){
								if(electivePoolCourse.findElement(electivePoolCourseSequenceCheckBox).getAttribute("class").contains("ng-not-empty") ) {
									electivePoolCourse.findElement(electivePoolCourseSequenceCheckBox).click();									
								}
												
							}
					
						}
					}
				}
			}
		}
	}

	public void verifyCourseAddedToSequenceList(String courseName) {
		List<WebElement> courses = driver.findElements(sequencePageCourse);
		boolean isCourseDisplayed = false;
		for (WebElement course : courses) {
			if (course.findElement(By.xpath(".//span[@class = 'course-title ng-binding']")).getText().equalsIgnoreCase(courseName)){
				isCourseDisplayed = true;
			}
		}
		Assert.assertTrue(isCourseDisplayed);
	}
	
	public void verifyCourseNotAddedToSequenceList(String courseName) {
		List<WebElement> courses = driver.findElements(sequencePageCourse);
		boolean isCourseDisplayed = false;
		for (WebElement course : courses) {
			if (course.findElement(By.xpath(".//span[@class = 'course-title ng-binding']")).getText().equalsIgnoreCase(courseName)){
				isCourseDisplayed = true;
			}
		}
		Assert.assertFalse(isCourseDisplayed);
	}
	
	public void deleteAllCategories() {
		sleep(1);
		if(driver.findElements(category).size() > 0) {
			List<WebElement> modules = driver.findElements(category);
			for (WebElement module : modules){
				module.findElement(categoryDeleteButton).click();
				clickAlertPopUpAcceptButton();
				while (modules.size() == driver.findElements(category).size()) {
				}
			}
			sleep(1);
		}
	}
	
	public void clickAddNewCategoryButton() {
		driver.findElement(addNewCategoryButton).click();
	}

	public void verifyAddNewCategoryModal() {
		waitForElement(addnewCategoryModal);
	}
	
	public void enterCategoryName(String category) {
		waitForElement(modalEnterNameBox);
		sleep(2);
		driver.findElement(modalEnterNameBox).clear();
		driver.findElement(modalEnterNameBox).sendKeys(category);
	}
	
	public void clickModalOkButton() {
		driver.findElement(modalOkButton).click();
	}
	
	public void clickModalCancelButton() {
		driver.findElement(modalCancelButton).click();
	}
	
	public void verifyCategoryAdded(String categoryName) {
		List<WebElement> programCategories = driver.findElements(category);
		programCategories.get(programCategories.size() - 1).findElement(By.xpath(".//div[@class = 'panel-heading']/.//span[contains(text(), '"+ categoryName +"')]"));
		sleep(1);
	}
	
	public void verifyElectivePoolAdded(String categoryName, String electiveName) {
		sleep(2);
		List<WebElement> programCategories = driver.findElements(category);
		programCategories.get(programCategories.size() - 1)
     	.findElement(By.xpath(".//span[contains(text(), '" + categoryName + "')]"));


		List<WebElement> moduleElectivePools =  programCategories.get(programCategories.size() - 1)
								.findElements(By.xpath(".//div[@ng-repeat = 'electivePool in category.ElectivePools']"));
		moduleElectivePools.get(moduleElectivePools.size() - 1)
		.findElement(By.xpath(".//span[contains(text(), '" + electiveName + "')]"));
		sleep(1);
	}
	
	public void clickCategoryEditButton(String categoryName) {
		List<WebElement> programCategories = driver.findElements(category);
		programCategories.get(programCategories.size() - 1).findElement(By.xpath(".//span[text()[contains(., '" + categoryName + "')]]"));
		programCategories.get(programCategories.size() - 1).findElement(categoryEditButton).click();
	}
	
	public void clickAddNewElectivePoolButton() {
		List<WebElement> programCategories = driver.findElements(category);
		programCategories.get(programCategories.size() - 1).findElement(electivePoolButton).click();			
	}
	
	public void verifyAddNewElectivePoolModal() {
		waitForElement(By.xpath("//div[contains(@class,'modal-content')]"));			
	}
	
	public void enterElectivePoolName(String electivePoolName) {
		driver.findElement(modalEnterNameBox).clear();
		driver.findElement(modalEnterNameBox).sendKeys(electivePoolName);
	}
	
	
	public void verifyCoursesNotDisplayedSequencePage() {
		Assert.assertEquals(driver.findElements(sequencePageCourse).size(), 0);
	}
}
