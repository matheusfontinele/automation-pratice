package compasso.automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import compasso.automation.driver.Driver;
import compasso.automation.driver.DriverFactory;
import compasso.automation.driver.DriverType;
import compasso.automation.pages.CheckoutPage;
import compasso.automation.pages.HomePage;

public class CheckoutTest {

	WebDriver driver;
	Driver driverManager;

	@DataProvider(name = "BasicTest")
	public Object[][] createData() {
		return new Object[][] { 
			{ "Faded Short Sleeve T-shirts", new Double(16.51), new Integer(2) },
			{ "Blouse", new Double(27.00), new Integer(3) } };
	}

	@BeforeMethod
	public void setUp() {

		driverManager = DriverFactory.getDriver(DriverType.CHROME);

		driver = driverManager.getDriver();

		driver.get("http://automationpractice.com/index.php");
	}

	@org.testng.annotations.Test(dataProvider = "BasicTest")
	public void validCheckout(String product, double unitValue, int quantity) {

		// Test Variables
		double shippingTax = 2; //This tax is the same for all products
		double finalResult = unitValue * quantity + shippingTax;

		HomePage homePage = new HomePage(this.driver);
		CheckoutPage checkoutPage = 
				homePage.clickOnProductImageUsingTitle(product)
				.chooseQuantityAndSize(quantity, "M")
				.addToCartAndCheckout();
		
		//Verify if it's the right page
		assert (checkoutPage.getTitle().contains("SHOPPING-CART SUMMARY"));
		
		checkoutPage.clickToProceedCheckoutSummary();
		//Verifiy if it's in the authentication part
		assert (checkoutPage.getTitle().equals("AUTHENTICATION"));
		
		//Login with valid user and go to confirm order
		checkoutPage.login("testing.test@test.com", "test123")
		.clickToProceedCheckoutAdress()
		.checkTermsOfService()
		.clickToProceedCheckoutShipping();
		
		//Assert if the total price it's correct
		assert (Double.parseDouble(checkoutPage.getTotalPriceFormated()) == (finalResult));
		
		checkoutPage.selectBankwirePayment()
		.clickConfirmOrder();
		
		assert (checkoutPage.getOrderCompletedMessage().equals("Your order on My Store is complete."));
	}

	@Test
	public void shouldNotContinueCheckoutWithAWrongCredentials() {
		
		HomePage homePage = new HomePage(this.driver);
		CheckoutPage checkoutPage = 
				homePage.clickOnProductImageUsingTitle("Blouse")
				.chooseQuantityAndSize(1, "S")
				.addToCartAndCheckout()
				.clickToProceedCheckoutSummary()
				.login("invaliduser@invalid.com", "invaldPass");
		
		assert (checkoutPage.getErrorMessageLogin().equals("Authentication failed."));
	}
	
	@AfterMethod
	public void quit() {
		driverManager.quitWebDriver();
	}

}
