package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver; 
	
	public ProductCatalogue(WebDriver driver)
	{
		// every child has to send to parent
		super(driver);
		this.driver = driver;
		// pagefoactory for driver.findelement construction
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	// Page Factory
	
	// @FindBy(id="userEmail")
	// WebElement userEmail;
	
	@FindBy(css=".mb-3")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement spinner; 
	
	
	
	By productsBy = By.cssSelector(".mb-3"); 
	By addToCart = By.cssSelector(".card-body button:last-of-type"); 
	By toastMessage = By.cssSelector("#toast-container");
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy); 
		return products; 
		
	}
	public WebElement getProductByName(String productName)
	{
		
		WebElement prod = getProductList().stream()
	            .filter(product -> product.findElement(By.cssSelector("b"))
	            .getText().equalsIgnoreCase(productName))
	            .findFirst()
	            .orElse(null);

	    return prod;
	}
	
	public void AddProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName); 
		
		  // tunggu tombol Add To Cart pada card ini bisa diklik
	    // waitForElementToBeClickable(addToCart);
		// prod.findElement(addToCart).click();
		
		click(prod.findElement(addToCart));
		waitForElementToAppear(toastMessage); 
		waitForElementToDisappear(spinner); 
		

		
	}

	


}
