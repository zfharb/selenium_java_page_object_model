package pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import exceptions.ElementNotClickableException;
import exceptions.ElementNotFoundException;

public class Page {

	protected static WebDriver driver;
	
	public Page(WebDriver driver) {
		this.driver = driver;
	}
		
	public void waitForElement(By by) {
		for (int second = 0;; second++) {
			sleep(1);
			try {
				if (second >= 5){
					throw new ElementNotFoundException("element was not found.");
				}
				driver.findElement(by);
	            break;	
			}  catch (ElementNotFoundException e) {
				throw e;
	        } catch (Exception e) {
	        }			
	    }		
	}
	
	public void clickElement(WebElement webElement) {
		for (int second = 0;; second++) {
			try {
				if (second >= 5){
					throw new ElementNotClickableException("element is not selectable on the browser.");
				}
				webElement.click();
				break;
			} catch (ElementNotClickableException e) {
				throw e;
	        } catch (Exception e) {

	        } 
			sleep(1);
	    }		
	}

	public void selectFromDropDownByValue(WebElement selectWebElement, String value) {
		Select dropDownList = new Select(selectWebElement);
		dropDownList.selectByValue(value);
	}
			
	public void selectFromDropDownByIndex(WebElement selectWebElement, int index) {
		Select  dropdown = new Select(selectWebElement);
		dropdown.selectByIndex(index);	
	}

	public void selectBrowserWindows(String windowTitle) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        Set<String> windows =driver.getWindowHandles();

        for (String window : windows) {
        	driver.switchTo().window(window);
            if (driver.getPageSource().contains(windowTitle)) {
                return;
            }
        }
    }
		
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickAlertPopUpAcceptButton() {
		sleep(3);
		driver.switchTo().alert().accept();
		sleep(2);
	}
	
	public void clickAlertPopUpCancelButton() {
		sleep(3);
		driver.switchTo().alert().dismiss();
		sleep(2);
	}

}
