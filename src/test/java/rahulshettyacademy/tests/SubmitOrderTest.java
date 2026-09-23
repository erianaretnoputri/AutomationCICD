package rahulshettyacademy.tests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver); 
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.LoginApplication("erianaretnoputri@gmail.com", "Iamking@000");
		// ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		
		List<WebElement>products = productCatalogue.getProductList(); 
		productCatalogue.AddProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		// CartPage cartPage = new CartPage(driver); 
		
		Boolean match = cartPage.VerifyProductDisplay(productName); 
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); 
		// Assert.assertTrue(match);
		// driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement checkout =
		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.cssSelector(".totalRow button")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);
		Actions a = new Actions(driver); 
		
		a.sendKeys(
				driver.findElement(By.cssSelector("input[placeholder='Select Country']")),
				"india")
				.build()
				.perform();

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		// a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		// driver.findElement(By.cssSelector(".action__submit")).click();
				
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));

		for(WebElement country : countries)
			{
				 if(country.getText().equalsIgnoreCase("India"))
						 {
					 			System.out.println("Klik : " + country.getText());
						        JavascriptExecutor js2 = (JavascriptExecutor) driver;
						        js2.executeScript("arguments[0].click();", country);
						        break;
						    }
			}
		
		WebElement submit =
				wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".action__submit")));

				JavascriptExecutor js3 = (JavascriptExecutor) driver;
				js3.executeScript("arguments[0].click();", submit);
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText(); 
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order.")); 
		
		System.out.println("Laporan Hasil Tes Akhir: PASS");
		// driver.close();
		// driver.quit();
	}
}