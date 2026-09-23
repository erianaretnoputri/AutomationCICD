package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver; 
	
	public LandingPage(WebDriver driver)
	{
		// send to AbstractComponents
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	// Page Factory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css = ".toast-message")
	WebElement errorMessage;
	
	// div[aria-label='Incorrect email or password.']
	
	public ProductCatalogue LoginApplication(String email,String password)
	{
		userEmail.sendKeys(email); 
		passwordEle.sendKeys(password); 
		// submit.click(); 
		click(submit);
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		// gives the object of the next page class
		return productCatalogue; 
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
