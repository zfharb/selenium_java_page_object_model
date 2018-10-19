package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends Page {
		
	private By userNameBox = By.id("username");
	private By passwordBox = By.id("password");
	private By signInButton = By.xpath("//div[@class = 'ping-body-container']/.//a[@title = 'Sign in']");
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	public void verifyLoginPage()
	{
		selectBrowserWindows("Sign On");
		waitForElement(signInButton);
	}	
	
	public void login(String userName, String password) {
		driver.findElement(userNameBox).sendKeys(userName);
		driver.findElement(passwordBox).sendKeys(password);
		driver.findElement(signInButton).click();
	}
	
	
	

}
