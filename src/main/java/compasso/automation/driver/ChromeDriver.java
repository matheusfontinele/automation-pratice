package compasso.automation.driver;

public class ChromeDriver extends Driver{

	@Override
	protected void createWebDriver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\java\\drivers\\chromedriver.exe");
		
		this.driver = new org.openqa.selenium.chrome.ChromeDriver();
	}

	
}
