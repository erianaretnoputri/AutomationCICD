package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
public class CartPage extends AbstractComponents{
	
	WebDriver driver; 
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle; 
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	
	public CartPage(WebDriver driver)
	{
		// every child has to send to parent
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	
	public Boolean VerifyProductDisplay(String productName)
	{
		waitForElementToAppear(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		// checkoutEle.click();
		click(checkoutEle);
		return new CheckoutPage(driver); 
	}
}
