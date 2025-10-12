package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	@FindBy(xpath = "//button[text()='Login']")
	public WebElement loginBtn;
	@FindBy(xpath = "//button[text()='Register']")
	public WebElement registerBtn;
	@FindBy(xpath = "//input[@placeholder='Search for items/categories']")
	public WebElement searchBox;
	@FindBy(tagName = "img")
	public List<WebElement> allImages;
	@FindBy(tagName = "a")
	public List<WebElement> allLinks;
	@FindBy(tagName = "select")
	public WebElement sizeSelectDropdown;
	@FindBy(xpath = "//button[text()='Checkout']")
	public WebElement checkoutButton;
	@FindBy(xpath = "//div[starts-with(@class, 'cart MuiBox-root')]/div[@class='MuiBox-root css-0']")
	private List<WebElement> cartItems;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean validateAllUIElements() {
		return isDisplayed(loginBtn) && isDisplayed(registerBtn) && isDisplayed(searchBox);
	}

	public int getImageCount() {
		return allImages.size();
	}

	public int getLinkCount() {
		return allLinks.size();
	}

	public String getSearchPlaceholder() {
		return searchBox.getAttribute("placeholder");
	}

	public void searchForProduct(String query) {
		// WebElement searchField = driver.findElement(searchInput);
		wait.until(ExpectedConditions.elementToBeClickable(searchBox));
		searchBox.clear();
		searchBox.sendKeys(query);
	}

	private final By addToCartButton(String productName) {
		return By.xpath("//p[text()='" + productName + "']/following::button[text()='Add to cart'][1]/span");
	}

	public void addProductToCart(String productName) {
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton(productName))).click();
	}

	public void selectItemSize(String size) {
		// Explicit Wait for the dropdown to become visible and clickable
		WebElement dropdown = wait.until(ExpectedConditions.visibilityOf(sizeSelectDropdown));
		new org.openqa.selenium.support.ui.Select(dropdown).selectByVisibleText(size);
	}

	public List<String> getCartItemNames(int noOfItemsAdded) {
		// Wait for the cart to have at least one item (based on the presence of the
		// checkout button)
		wait.until(ExpectedConditions.visibilityOf(checkoutButton));
		// wait.until(ExpectedConditions.numberOfElementsToBe(cartItems,
		// noOfItemsAdded));
		wait.until(driver -> cartItems.size() == noOfItemsAdded);
		List<String> itemTexts = new ArrayList<String>();

		for (WebElement cartItem : cartItems) {
			WebElement textDiv = cartItem.findElement(By.xpath("./div/div[2]/div"));
			String text = textDiv.getText().trim();
			itemTexts.add(text);
			System.out.println(text);
		}

		// System.out.println("Texts from cart items: " + itemTexts);

		return itemTexts;
	}

	public void clickCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
	}
}