package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CampusManagerPage extends Page {
	
	private By campusManagerPageHeader = By.xpath(".//strong[contains(text(), 'CAMPUS CREDITS MANAGER')]");
	private By creditModal = By.id("student-undergrad-credit-limit");
	private By creditModalUndergraduateCreditLimit = By.id("student-undergrad-credit-limit");
	private By creditModalGraduateCreditLimit = By.id("student-graduate-credit-limit");
	private By creditModalOkButton = By.xpath(".//button[contains(text(), 'Ok')]");
	private By creditModalCancelButton = By.xpath(".//button[contains(text(), 'Cancel')]");
	private By campusRow = By.xpath(".//tr[@ng-repeat = 'campus in campuses | orderBy:filterSettings.sortTable:filterSettings.sortTableReverse']");
	
	public CampusManagerPage(WebDriver driver) {
		super(driver);
	}
	
	public void verifyCampusManagerPage() {
		waitForElement(campusManagerPageHeader);
	}
	
	public void clickModalOkButton() {
		clickElement(driver.findElement(creditModalOkButton));
	}
	
	public void clickModalCancelButton() {
		clickElement(driver.findElement(creditModalCancelButton));
	}
	
	public void clickFirstCampusEditButton() {
		clickElement(driver.findElement(By.xpath(".//tr[@ng-repeat = 'campus in campuses | orderBy:filterSettings.sortTable:filterSettings.sortTableReverse']/.//span[@ng-click = 'editCampus(campus)']")));
	}

	public void enterUndergraduateCredit(String creditNumber) {
		waitForElement(creditModal);
		driver.findElement(creditModalUndergraduateCreditLimit).clear();
		driver.findElement(creditModalUndergraduateCreditLimit).sendKeys(creditNumber);
	}
	
	public void enterGraduateCredit(String creditNumber) {
		waitForElement(creditModal);
		driver.findElement(creditModalGraduateCreditLimit).clear();
		driver.findElement(creditModalGraduateCreditLimit).sendKeys(creditNumber);
	}
	
	public void verifyUndergraduateCreditLimit(String creditNumber) {
		waitForElement(campusRow);
		driver.findElement(campusRow).findElement(By.xpath(".//span[@ng-if = 'campus.UndergraduateCredits']"))
		.getText();
		Assert.assertTrue(driver.findElement(campusRow)
						.findElement(By.xpath(".//span[@ng-if = 'campus.UndergraduateCredits']"))
						.getText().equalsIgnoreCase(creditNumber));
	}
	
	public void verifyGraduateCreditLimit(String creditNumber) {
		waitForElement(campusRow);
		driver.findElement(campusRow).findElement(By.xpath(".//span[@ng-if = 'campus.UndergraduateCredits']"))
		.getText();
		Assert.assertTrue(driver.findElement(campusRow)
						.findElement(By.xpath(".//span[@ng-if = 'campus.GraduateCredits']"))
						.getText().equalsIgnoreCase(creditNumber));
	}
}
