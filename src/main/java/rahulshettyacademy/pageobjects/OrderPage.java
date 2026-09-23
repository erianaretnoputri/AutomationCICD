package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver; 
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle; 
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
	public OrderPage(WebDriver driver)
	{
		// every child has to send to parent
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName)); 
		return match;
	}
	

}
