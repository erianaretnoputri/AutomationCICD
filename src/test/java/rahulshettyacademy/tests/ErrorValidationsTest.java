package rahulshettyacademy.tests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;
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
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

	public class ErrorValidationsTest extends BaseTest{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
			
		String productName = "ZARA COAT 3";
		landingPage.LoginApplication("erianaretnoputri@gmail.com", "Iamki@000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		//  errorMessage = landingPage.getErrorMessage();

//		System.out.println("ACTUAL MESSAGE = [" + errorMessage + "]");
//
//		Assert.assertEquals(
//		    errorMessage,
//		    "Incorrect email or password."
//		);
	
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.LoginApplication("erianaretnoputri@gmail.com", "Iamking@000");
			
		List<WebElement>products = productCatalogue.getProductList(); 
		productCatalogue.AddProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33"); 
		Assert.assertFalse(match);
		

	}
}