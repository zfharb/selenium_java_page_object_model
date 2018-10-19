package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CourseModulePage  extends Page {
	private By filterModuleBox = By.xpath(".//input[@placeholder='Filter modules']");
	private By courseModulePageHeader = By.xpath(".//strong[text() = 'MODULE MANAGER']");
	private By courseSearchBox = By.xpath(".//input[@placeholder='Search for Courses by Name']");
	private By courseSearchResultList = By.xpath(".//ul[@class = 'list-group']/li[@ng-model = 'selectedCourses']");
	private By createNewModuleDropBox = By.xpath(".//div[text() = 'Drop a master course here to create a new module']");
	private By module = By.xpath(".//div[@ng-repeat = 'module in modules | filter:filterCourses']");
	private By moduleDropCourseBox = By.xpath(".//p[text() = 'Drop course here']");
	private By moduleName = By.xpath(".//div[@class = 'col-xs-9']/strong");
	private By moduleDeleteButton = By.xpath(".//button[@ng-click = 'removeCourseModule(module)']");
	private By moduleTotalCredits = By.xpath(".//div[@class = 'credit-bar']/.//span[@class = 'badge badge-white-background ng-binding']");
	private By moduleRequiredCredits = By.xpath(".//div[@class = 'credit-bar']/.//span[@class = 'badge badge-credits ng-binding']");
	private By moduleCourse = By.xpath(".//li[@ng-repeat = 'course in module.moduleCourses']");
	private By moduleCourseName = By.xpath(".//div[@class = 'col-md-6']/.//span[@class = 'ng-binding']");
	private By moduleCourseDeleteButton = By.xpath(".//button[@ng-click = 'remove()']");

	public CourseModulePage(WebDriver driver) {
		super(driver);
	}
	
	public void verifyCampusManagerPage() {
		waitForElement(courseModulePageHeader);
	}
	
	public void deleteModule(String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				module.findElement(moduleDeleteButton).click();
				clickAlertPopUpAcceptButton();			
				while (modules.size() == driver.findElements(this.module).size()) {
				}
			}
		}
	}
	
	
	public void enterNameInFilterBox(String courseOrModuleName) {
		String[] name = courseOrModuleName.split(" ");
		driver.findElement(filterModuleBox).clear();
		for (int x = 0; x < name.length ;x++) {
			driver.findElement(filterModuleBox).sendKeys(name[x] + " ");				
			sleep(1);
		}
	}
	
	public void verifyFilteredModule(String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		String actualModuleName = modules.get(0).findElement(this.moduleName).getText();
		Assert.assertTrue(actualModuleName.equalsIgnoreCase(moduleName));
		Assert.assertTrue(modules.size()==1);
	}
	
	public void enterNameInCourseSearchBox(String courseName) {
		String[] name = courseName.split(" ");
		driver.findElement(courseSearchBox).clear();
		sleep(1);
		for (int x = 0; x < name.length ;x++) {
			driver.findElement(courseSearchBox).sendKeys(name[x] + " ");				
			sleep(1);
		}
	}

	public void verifyCourseDisplayedUnderCourseSearchBox(String courseName) {
		boolean isCourseListed = false;
		List<WebElement> courses = driver.findElements(courseSearchResultList);
		for (WebElement course : courses){
			if(course.findElement(By.xpath(".//div[@class = 'col-md-8 ng-binding']")).getText().equalsIgnoreCase(courseName)) {
				isCourseListed = true;
				break;
			}
		}
		Assert.assertTrue(isCourseListed);
	}
	
	public void dragAndDropModule(String moduleName) {
		WebElement courseToDrag = null;
		List<WebElement> courses = driver.findElements(courseSearchResultList);
		for (WebElement course : courses){
			if(course.findElement(By.xpath(".//div[@class = 'col-md-8 ng-binding']")).getText().equalsIgnoreCase(moduleName)) {
				courseToDrag = course;
				break;
			}
		}
		WebElement target = driver.findElement(createNewModuleDropBox);
		(new Actions(driver)).dragAndDrop(courseToDrag, target).perform();
	}
	
	public void clickModuleDeleteButton(String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				module.findElement(moduleDeleteButton).click();
				break;
			}
		}
		
	}
	
	public void dragAndDropCourseToModule(String courseName, String moduleName) {
		WebElement courseToDrag = null;
		WebElement target = null;
		List<WebElement> courses = driver.findElements(courseSearchResultList);
		for (WebElement course : courses){
			if(course.findElement(By.xpath(".//div[@class = 'col-md-8 ng-binding']")).getText().equalsIgnoreCase(courseName)) {
				courseToDrag = course;
				break;
			}
		}
				
		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				target = module.findElement(moduleDropCourseBox);
				break;
			}
		}
				
		(new Actions(driver)).dragAndDrop(courseToDrag, target).perform();
		sleep(1);
	}
	
	public void clickModuleCourseDeleteButton(String courseName, String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				List<WebElement> moduleCourses = driver.findElements(moduleCourse);
				for (WebElement moduleCourse : moduleCourses){
					if(moduleCourse.findElement(By.xpath(".//div[@class = 'col-md-6']/span[@class = 'ng-binding']")).getText().equalsIgnoreCase(courseName)) {
						moduleCourse.findElement(moduleCourseDeleteButton).click();
						break;
					}
				}
				break;
			}
		}
	}
	
	public void verifyModuleDisplayedUnderModuleManager(String moduleName) {
		waitForElement(By.xpath(".//div[@ng-repeat = 'module in modules | filter:filterCourses']/.//strong[text() = '" + moduleName + "']"));
	}
	
	public void verifyModuleTotalCreditsNumber(String totalCreditsNumber, String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		String moduleTotalCreditsNumber = null;
		
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				moduleTotalCreditsNumber = module.findElement(moduleTotalCredits).getText();
				break;
			}
		}
		Assert.assertEquals(totalCreditsNumber, moduleTotalCreditsNumber);
	}
	
	public void verifyModuleRequiredCreditsNumber(String requiredCreditsNumber, String moduleName) {
		List<WebElement> modules = driver.findElements(module);
		String moduleRequiredCreditsNumber = null;
		
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				moduleRequiredCreditsNumber = module.findElement(moduleRequiredCredits).getText();
				break;
			}
		}
		Assert.assertEquals(requiredCreditsNumber, moduleRequiredCreditsNumber);
	}
	
	public void verifyCourseDisplayedUnderModule(String moduleCourseName, String moduleName) {
		boolean isModuleNameCorrect = false;
		boolean isModuleCourseNameCorrect = false;

		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(this.moduleName).getText().equalsIgnoreCase(moduleName)) {
				isModuleNameCorrect = true;
				List<WebElement> moduleCourses = module.findElements(this.moduleCourse);
				for (WebElement moduleCourse : moduleCourses){
					if(moduleCourse.findElement(this.moduleCourseName).getText().equalsIgnoreCase(moduleCourseName)) {
						isModuleCourseNameCorrect = true;
						break;
					}
				}
			}
		}
		Assert.assertTrue(isModuleNameCorrect);
		Assert.assertTrue(isModuleCourseNameCorrect);
	}
	
	public void verifyNoDuplicateCourseDisplayedUnderModule(String moduleCourseName, String moduleName) {
		int courseCounter = 0;

		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(this.moduleName).getText().equalsIgnoreCase(moduleName)) {
				List<WebElement> moduleCourses = module.findElements(this.moduleCourse);
				for (WebElement moduleCourse : moduleCourses){
					if(moduleCourse.findElement(this.moduleCourseName).getText().equalsIgnoreCase(moduleCourseName)) {
						courseCounter++;
					}
				}
			break;
			}
		}
		Assert.assertEquals(courseCounter, 1);
	}
	
	public void verifyModuleDropCourseBoxNotDisplayed(String moduleName) {

		boolean isModuleDropCourseBoxDisplayed = false;

		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(By.xpath(".//div[@class = 'col-xs-9']/strong")).getText().equalsIgnoreCase(moduleName)) {
				try {
					module.findElement(moduleDropCourseBox);				
					isModuleDropCourseBoxDisplayed = true;
				} catch (Exception e) {
				}
				break;
			}
		}
		Assert.assertFalse(isModuleDropCourseBoxDisplayed);
	}
	
	public void verifyCourseNotDisplayedUnderModule(String moduleCourseName, String moduleName) {
		boolean isModuleCourseDisplayed = false;

		List<WebElement> modules = driver.findElements(module);
		for (WebElement module : modules){
			if(module.findElement(this.moduleName).getText().equalsIgnoreCase(moduleName)) {
				List<WebElement> moduleCourses = module.findElements(this.moduleCourse);
				for (WebElement moduleCourse : moduleCourses){
					if(moduleCourse.findElement(this.moduleCourseName).getText().equalsIgnoreCase(moduleCourseName)) {
						isModuleCourseDisplayed = true;
						break;
					}
				}
				break;
			}
		}
		Assert.assertFalse(isModuleCourseDisplayed);
	}
}
