package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponents {
	WebDriver driver; 
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader; 
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader; 
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public CartPage goToCartPage()
	{
		// cartHeader.click();
		click(cartHeader);
		CartPage cartPage = new CartPage(driver); 
		return cartPage; 
	}
	
	public OrderPage goToOrdersPage()
	{
		// orderHeader.click();
		click(orderHeader);
		OrderPage orderPage = new OrderPage(driver); 
		return orderPage;
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(3000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void click(WebElement element)
	{
	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.visibilityOf(element));
	    
	    ((JavascriptExecutor) driver).executeScript(
	            "arguments[0].scrollIntoView({block: 'center'});",
	            element
	    );
	    wait.until(ExpectedConditions.elementToBeClickable(element));

	    try
	    {
	        element.click();
	    }
	    catch(Exception e)
	    {
	        JavascriptExecutor js =
	                (JavascriptExecutor)driver;

	        js.executeScript("arguments[0].click();", element);
	    }
	}

}
