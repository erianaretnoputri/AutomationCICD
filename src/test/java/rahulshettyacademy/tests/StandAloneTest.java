package rahulshettyacademy.tests;

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
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver); 
		// 1. Proses Login
		driver.findElement(By.id("userEmail")).sendKeys("erianaretnoputri@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking000");
		driver.findElement(By.id("login")).click(); 
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		// 2. Tunggu sampai produk muncul di layar
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		// 3. Cari produk "ZARA COAT 3"
		WebElement prod = products.stream().filter(product ->
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
		
		// 4. Ambil elemen tombol Add to Cart & Klik menggunakan JS Click
		WebElement addToCartBtn = prod.findElement(By.cssSelector(".card-body button:last-of-type"));
		org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", addToCartBtn);
		
		// 5. Tunggu notifikasi sukses muncul
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		System.out.println("Sukses! Barang berhasil masuk ke keranjang.");
		
		// 6. Tunggu animasi menghilang & masuk ke halaman Cart
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		WebElement cartBtn = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		js.executeScript("arguments[0].click();", cartBtn); 
				
		// 7. Validasi produk di halaman Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); 
		Assert.assertTrue(match);
		
		// 8. Klik Checkout menggunakan JS Click untuk bypass overlay
		WebElement checkoutBtn = driver.findElement(By.cssSelector(".totalRow button"));
		js.executeScript("arguments[0].click();", checkoutBtn);
		
		// 9. Tunggu input kolom negara siap berinteraksi
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		
		// 10. MENGGUNAKAN ACTIONS (Gaya Rahul Shetty untuk input & pilih negara)
//		Actions a = new Actions(driver); 
//		WebElement countryField = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
//		a.sendKeys(countryField, "india").build().perform();
//		
		// 10. PROSES INPUT & PILIH NEGARA (Diperbaiki agar Angular merespons)
		WebElement countryField = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
				
		// Klik dulu kolomnya agar aktif, lalu ketik menggunakan sendKeys biasa (lebih dipercaya Angular)
		countryField.click();
		countryField.sendKeys("india");
		// Tunggu hingga elemen hasil dropdown saran (.ta-results) memunculkan opsinya
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		// Klik opsi kedua (India) menggunakan JS Click agar tembus overlay animasi dropdown
		WebElement countryOption = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		js.executeScript("arguments[0].click();", countryOption);
		
		// 11. Finalisasi: Klik tombol Place Order menggunakan JS Click
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		WebElement placeOrderBtn = driver.findElement(By.cssSelector(".action__submit"));
		js.executeScript("arguments[0].click();", placeOrderBtn);
		System.out.println("Hebat! Skenario Checkout Sukses Terlaksana.");
		
		// 12. Validasi Halaman Sukses
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText(); 
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order.")); 
		
		System.out.println("Laporan Hasil Tes Akhir: PASS");
		// driver.close();
		driver.quit();
	}
}