package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	@FindBy(id = "username")
	public WebElement usernameInput;
	@FindBy(id = "password")
	public WebElement passwordInput;
	@FindBy(xpath = "//button[text()='Login to QKart']")
	public WebElement loginButton;
	@FindBy(xpath = "//button[text()='Logout']")
	public WebElement logoutButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String user, String pass) throws InterruptedException {
		usernameInput.sendKeys(user);
		passwordInput.sendKeys(pass);
		loginButton.click();
		Thread.sleep(2000);
		waitToDisapperToastMessage();
	}

	public boolean isLogoutDisplayed() {
		return isDisplayed(logoutButton);
	}
}