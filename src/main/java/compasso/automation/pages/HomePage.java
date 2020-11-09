package compasso.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	//Elementos UI da página
	private By productImgs = By.cssSelector("ul#homefeatured>li>div>div>div>a>img");

	
	//Ações da página
	public ProductDetailPage clickOnProductImageUsingTitle(String title) {
		List<WebElement> list = driver.findElements(productImgs);

		for (WebElement product : list) {
			if (product.getAttribute("title").equals(title)) {
				product.click();
				return new ProductDetailPage(driver);
			}
		}
		return null;
	}
}
