package compasso.automation.driver;

public class DriverFactory {

	public static Driver getDriver(DriverType driverType) {
	Driver driver;
	
	switch (driverType) {
	case CHROME:
		driver = new ChromeDriver();
		break;
	
	case FIREFOX:
		//Instacia a classe do Firefox, onde será configurado o geckodriver e devolve
		// o driver do firefox
	default:
		driver = new ChromeDriver();
		break;
	}
	
	return driver;
	}
}
