package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.TestBaseSetup;

public class HomePage extends Page {

	private By headerText = By.xpath("//p[@class = 'lead']");
	private By DegreeProgramManagerLink = By.xpath("//a[contains(text(), 'Degree Plan Manager')]");
	private By creditEvaluationLink = By.xpath("//a[contains(text(), 'CET Transcript')]");
	private By courseModuleLink = By.xpath("//a[text() = 'Course Modules']");
	private By campusManagerLink = By.xpath("//a[text() = 'Campus Manager']");
	private By equivalencyAdminLink = By.xpath("//a[text() = 'Equivalency Admin']");

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void goToHomePage() {
		driver.navigate().to(TestBaseSetup.getApplicationURL());

	}
		
	public String getPageTitle(){
		String title = driver.getTitle();
		return title;
	}
	
	public boolean verifyPageTitle() {
		String expectedPageTitle="";
		return getPageTitle().contains(expectedPageTitle);
	}
	
	public boolean verifyPage() {
		String expectedPageHeaderText="Choose Your Blue Print Project";
		return driver.findElement(headerText).getText().contains(expectedPageHeaderText);
	}
	
	public SignInPage getSignInPage() {
		driver.findElement(campusManagerLink).click();
		return new SignInPage(driver);
	}
	
	public DegreeProgramManagerPage clickDegreeProgramManagerLink() {
		driver.findElement(DegreeProgramManagerLink).click();
		return new DegreeProgramManagerPage(driver);
	}
	
	public CreditEvaluationPage clickCreditEvaluationPage() {
		driver.findElement(creditEvaluationLink).click();
		return new CreditEvaluationPage(driver);
	}
	
	public CourseModulePage clickCourseModuleLink() {
		driver.findElement(courseModuleLink).click();
		return new CourseModulePage(driver);
	}
	
	public CampusManagerPage clickCampusManagerLink() {
		driver.findElement(campusManagerLink).click();
		return new CampusManagerPage(driver);
	}
	
	public EquivalencyAdminPage clickEequivalencyAdminLink() {
		driver.findElement(equivalencyAdminLink).click();
		return new EquivalencyAdminPage(driver);
	}
	
}
