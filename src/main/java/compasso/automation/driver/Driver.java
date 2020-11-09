package compasso.automation.driver;

import org.openqa.selenium.WebDriver;

public abstract class Driver {

	protected WebDriver driver;
	
	protected abstract void createWebDriver();
	
	public void quitWebDriver() {
		if(null != driver) {
			driver.quit();
			driver = null;
		}
	}
	
	public WebDriver getDriver() {
		if(null == driver) {createWebDriver();}
				
		return driver;
	}
}
