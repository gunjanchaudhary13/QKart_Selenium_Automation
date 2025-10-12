package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// --- Locators ---
	@FindBy(xpath = "//button[text()='Add new address']")
	public WebElement addNewAddressButton;
	@FindBy(xpath = "//textarea[@placeholder='Enter your complete address']")
	public WebElement addressTextArea;
	@FindBy(xpath = "//button[text()='Add']")
	public WebElement addButton;
	@FindBy(xpath = "//button/p[contains(text(), 'Delete')]")
	public WebElement deleteButton;

	public void addNewAddress(String address) {
		wait.until(ExpectedConditions.elementToBeClickable(addNewAddressButton)).click();
		WebElement addressInput = wait.until(ExpectedConditions.visibilityOf(addressTextArea));
		addressInput.sendKeys(address);
		wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[contains(@class, 'address-item')]"),
				0));
	}

	public boolean isAddressDisplayed(String partialAddress) {
		List<WebElement> addresses = driver.findElements(By.xpath("//div[contains(@class, 'address-item')]//p"));
		return addresses.stream().anyMatch(element -> element.getText().contains(partialAddress));
	}

	public void deleteFirstAddress() {
		wait.until(ExpectedConditions.visibilityOf(deleteButton));
		WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
		deleteBtn.click();
		wait.until(ExpectedConditions.invisibilityOf(deleteBtn));
	}
}
