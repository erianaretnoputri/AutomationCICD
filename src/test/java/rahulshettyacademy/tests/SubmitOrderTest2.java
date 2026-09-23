package rahulshettyacademy.tests;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest2 {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver); 
		landingPage.goTo();
		landingPage.LoginApplication("erianaretnoputri@gmail.com", "Iamking@000");
		System.out.println("✓ STEP 1 : Login berhasil");
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement>products = productCatalogue.getProductList(); 
		System.out.println("✓ STEP 2 : Product list berhasil dimuat");
		
		productCatalogue.AddProductToCart(productName);
		System.out.println("✓ STEP 3 : Produk berhasil ditambahkan ke Cart");
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		System.out.println("✓ STEP 4 : Berhasil klik Cart");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
		        By.cssSelector(".cartSection")));
		System.out.println("✓ STEP 5 : Halaman Cart sudah tampil");
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); 
		Assert.assertTrue(match);
		System.out.println("✓ STEP 6 : Produk ditemukan di Cart");
		
		
		// 1. klik checkout

		// =======================
		// CHECKOUT DEBUG
		// =======================

		wait.until(ExpectedConditions.elementToBeClickable(
		        By.cssSelector(".totalRow button")));

		driver.findElement(By.cssSelector(".totalRow button")).click();
		


		// cek apakah spinner masih ada
		List<WebElement> spinner =
		        driver.findElements(By.cssSelector(".ng-animating"));



		// cek apakah toast masih ada
		List<WebElement> toast =
		        driver.findElements(By.cssSelector("#toast-container"));

	


		// cek apakah overlay lain masih ada
		List<WebElement> overlay =
		        driver.findElements(By.cssSelector(".ngx-spinner"));



		System.out.println("✓ STEP 8 : Berhasil masuk halaman Checkout");


		// =======================
		// CHECKOUT PAGE
		// =======================

		Actions a = new Actions(driver);

		a.sendKeys(
		        driver.findElement(By.cssSelector("input[placeholder='Select Country']")),
		        "india")
		        .build()
		        .perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
		        By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText(); 
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order.")); 
		
		System.out.println("Laporan Hasil Tes Akhir: PASS");
		// driver.close();
		// driver.quit();
	}
}