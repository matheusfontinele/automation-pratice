package compasso.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	// Elementos UI
	private By titleCheckoutProcess = By.cssSelector("h1.page-heading:not(span)");

	private By proceedToCheckoutSpan = By.cssSelector("p.cart_navigation > a[title='Proceed to checkout']");

	//AUTHENTICATION
	private By email = By.id("email");
	private By password = By.id("passwd");
	private By signInButton = By.id("SubmitLogin");
	private By messageError = By.cssSelector("div.alert-danger > ol >  li");
	
	
	// Adress
	private By proceedToCheckoutButton = By.cssSelector("button[name='processAddress']");

	private By proceedToCheckoutButtonShipping = By.cssSelector("button[name='processCarrier']");

	private By checkBoxTermsOfService = By.id("cgv");
	
	//PAYMENT METHOD
	private By totalPrice = By.cssSelector("#total_price");
	private By bankwire = By.cssSelector("a.bankwire");
	private By cheque = By.cssSelector("a.cheque");
	private By confirmOrderButton = By.cssSelector("p#cart_navigation > button");
	private By orderSucessMessage = By.cssSelector("p.cheque-indent > strong");
	
	public String getTitle() {
		return driver.findElement(titleCheckoutProcess).getText();
	}

	public CheckoutPage clickToProceedCheckoutSummary() {
		driver.findElement(proceedToCheckoutSpan).click();

		return new CheckoutPage(driver);
	}

	public CheckoutPage login(String email, String password) {
		driver.findElement(this.email).sendKeys(email);
		driver.findElement(this.password).sendKeys(password);

		driver.findElement(signInButton).click();

		return new CheckoutPage(driver);
	}
	
	public String getErrorMessageLogin() {
		return driver.findElement(messageError).getText();
	}

	// Adress
	public CheckoutPage clickToProceedCheckoutAdress() {
		driver.findElement(proceedToCheckoutButton).click();

		return new CheckoutPage(driver);
	}

	public CheckoutPage clickToProceedCheckoutShipping() {
		driver.findElement(proceedToCheckoutButtonShipping).click();

		return new CheckoutPage(driver);
	}

	public CheckoutPage checkTermsOfService() {
		driver.findElement(checkBoxTermsOfService).click();

		return new CheckoutPage(driver);
	}
	
	public String getTotalPriceFormated() {
		
		String value = driver.findElement(totalPrice).getText();
		
		return value.replace("$", "");
	}
	
	public  CheckoutPage selectBankwirePayment() {
		driver.findElement(bankwire).click();

		return new CheckoutPage(driver);
	}
	
	public  CheckoutPage selectChequePayment() {
		driver.findElement(cheque).click();

		return new CheckoutPage(driver);
	}
	
	public void clickConfirmOrder() {
		driver.findElement(confirmOrderButton).click();
	}
	
	public String getOrderCompletedMessage() {
		return driver.findElement(orderSucessMessage).getText();
	}
}
