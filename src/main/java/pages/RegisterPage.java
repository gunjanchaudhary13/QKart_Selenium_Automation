package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
	
	@FindBy(id="username") public WebElement usernameInput;
    @FindBy(id="password") public WebElement passwordInput;
    @FindBy(id="confirmPassword") public WebElement confirmPasswordInput;
    @FindBy(xpath="//button[normalize-space() = 'Register Now']") public WebElement registerButton;
    @FindBy(xpath="//button[text()='Logout']") public WebElement logoutButton;
    
    
	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void register(String user,String pass, String confirmPass)throws InterruptedException{
        usernameInput.sendKeys(user); 
        passwordInput.sendKeys(pass);
        confirmPasswordInput.sendKeys(confirmPass);
        registerButton.click(); 
        Thread.sleep(2000);
    }
	
	public boolean isLogoutDisplayed(){ return isDisplayed(logoutButton); }

}
