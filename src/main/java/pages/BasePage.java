package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	@FindBy(id="notistack-snackbar") private WebElement toastMessage; 
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String waitForToastMessage() {
		return wait.until(ExpectedConditions.visibilityOf(toastMessage)).getText();
	}
	
	public void waitToDisapperToastMessage() {
		wait.until(ExpectedConditions.invisibilityOf(toastMessage));
	}
	
	
}