package compasso.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

	private WebDriver driver;
	private WebDriverWait wait;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20);
	}

	// Elementos UI da página
	private By addToCartButton = By.cssSelector("button.exclusive");
	private By proceedToCheckoutButton = By.cssSelector("a[title='Proceed to checkout']");
	private By quantityProduct = By.id("quantity_wanted");
	private By selectSize = By.cssSelector("select#group_1");

	public ProductDetailPage chooseQuantityAndSize(int quantity, String size) {

		wait.until(ExpectedConditions.elementToBeClickable(quantityProduct)).clear();
		driver.findElement(quantityProduct).sendKeys(String.valueOf(quantity));

		Select selectSize = new Select(driver.findElement(this.selectSize));
		selectSize.selectByVisibleText(size);

		return new ProductDetailPage(driver);
	}

	public CheckoutPage addToCartAndCheckout() {

		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

		wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();

		return new CheckoutPage(driver);

	}
}
