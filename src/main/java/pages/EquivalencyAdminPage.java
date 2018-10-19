package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EquivalencyAdminPage extends Page {
	
	private By newEquivalencyButton = By.xpath(".//button[@ng-click = 'newEquivalencyModal()']");
	private By itemsPerPageDropDownList = By.xpath(".//select[@ng-model = 'Parameters.itemsPerPage']");
	private By resetAllButton = By.xpath(".//a[text() = 'Reset All']");
	private By equivalency = By.xpath(".//div[@ng-repeat = 'equivalency in EquivalencyAdmin.Equivalencies']");
	private By equivalencyPriorLearningCourse = By.xpath(".//li[@ng-repeat = 'priorLearningCourse in equivalency.PriorLearningCourses']");
	private By equivalencyKaplanCourse = By.xpath(".//li[@ng-repeat = 'kaplanCourse in equivalency.KaplanCourses']");

	private By priorLearningInstitutionSearchBox = By.xpath(".//input[@ng-model = 'Parameters.Institution']");
	private By priorLearningInstitution = By.xpath(".//label[text() = 'Institution Name']/following-sibling::ul[@class = 'dropdown-menu ng-isolate-scope']/.//li[@ng-repeat = 'match in matches track by $index']");
	private By priorLearningCourseFilterBox = By.xpath(".//input[@ng-model= 'Parameters.InstitutionCourse' and @placeholder = 'Course Name or Code']");
	private By priorLearningCourse = By.xpath(".//p[text() = 'Institution']/following-sibling::div[@class = 'well well-sm']/div[2]/.//li[@ng-repeat = 'match in matches track by $index']");
	private By kaplanCourseFilterBox = By.xpath(".//input[@ng-model= 'Parameters.kaplanCourse' and @placeholder = 'Course Name or Code']");
	private By kaplanCourse = By.xpath(".//p[text() = 'Kaplan Course']/following-sibling::div[@class = 'well well-sm']/div/.//li[@ng-repeat = 'match in matches track by $index']");

	public EquivalencyAdminPage(WebDriver driver) {
		super(driver);		
	}
	
	public void verifyPage() {
		Assert.assertTrue(driver.findElement(newEquivalencyButton).getText().equalsIgnoreCase("New Equivalency"));
	}
	
	public void verifyItemsPerPageDropDownList(String itemsPerPageNumber) {
		driver.findElement(itemsPerPageDropDownList).findElement(By.xpath(".//option[text() = '" + itemsPerPageNumber +"']"));
	}
	
	public void clickResetAllButton() {
		clickElement(driver.findElement(resetAllButton));
	}
	
	public void enterInstitutionName(String institutionName) {
		driver.findElement(priorLearningInstitutionSearchBox).clear();
		driver.findElement(priorLearningInstitutionSearchBox).sendKeys(institutionName);
	}

	public void selectInstitution(String institutionName) {
		waitForElement(priorLearningInstitution);
		List<WebElement> institutions = driver.findElements(priorLearningInstitution);
		for (WebElement institution : institutions) {
			if(institution.findElement(By.xpath(".//a[@tabindex = '-1']")).getText().contains(institutionName) == true) {
				institution.click();
				waitForElement(By.xpath(".//label[text() = 'Institution Name']/following-sibling::ul[@class = 'dropdown-menu ng-isolate-scope ng-hide']"));
				waitForElement(equivalency);
				break;
			}
		}			
	}
		
	public void enterPriorLearningCourseCode(String courseCode) {
		driver.findElement(priorLearningCourseFilterBox).clear();
		driver.findElement(priorLearningCourseFilterBox).sendKeys(courseCode);
		waitForElement(priorLearningCourse);
	}
	
	public void selectPriorLearningCourse(String courseCode) {
		List<WebElement> courses = driver.findElements(priorLearningCourse);
		for (WebElement course : courses) {
			if(course.findElement(By.xpath(".//a[@tabindex = '-1']")).getText().equalsIgnoreCase(courseCode) == true) {
				course.click();
				waitForElement(By.xpath(".//p[text() = 'Institution']/following-sibling::"
						+ "div[@class = 'well well-sm']/div[2]/"
						+ ".//ul[@class = 'dropdown-menu ng-isolate-scope ng-hide']"));
				break;
			}
		}			
	}
	
	public void verifyFilteredEquivelancyListByPriorLearningCourse(String courseCode) {
		waitForElement(equivalency);
		List<WebElement> courses = driver.findElements(equivalency);
		for (WebElement course : courses) {
			Assert.assertTrue(course.findElement(equivalencyPriorLearningCourse)
			.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding']")).getText().equalsIgnoreCase(courseCode));		
		}			
				
	}
	
	public void enterKaplanCourseCode(String courseCode) {
		driver.findElement(kaplanCourseFilterBox).clear();
		driver.findElement(kaplanCourseFilterBox).sendKeys(courseCode);
		waitForElement(kaplanCourse);
	}

	public void selectKaplanCourse(String courseCode) {
		List<WebElement> courses = driver.findElements(kaplanCourse);
		for (WebElement course : courses) {
			if(course.findElement(By.xpath(".//a[@class = 'btn-link ng-binding ng-scope']")).getText().contains(courseCode) == true) {
				course.click();
				waitForElement(By.xpath(".//p[text() = 'Kaplan Course']/following-sibling::"
						+ "div[@class = 'well well-sm']/div/"
						+ ".//ul[@class = 'dropdown-menu ng-isolate-scope ng-hide']"));
				break;
			}
		}			
	}
	
	public void verifyFilteredEquivelancyListByKaplanCourse(String courseCode) {
		waitForElement(equivalency);
		List<WebElement> courses = driver.findElements(equivalency);
		for (WebElement course : courses) {
			Assert.assertTrue(course.findElement(equivalencyKaplanCourse)
			.findElement(By.xpath(".//div[@class = 'col-xs-2 ng-binding']")).getText().equalsIgnoreCase(courseCode));		
		}			
				
	}
}
