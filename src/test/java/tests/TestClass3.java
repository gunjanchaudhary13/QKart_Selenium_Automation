package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class TestClass3 extends BaseTest {
	@Test
	public void PageMetadataValidation() {
		
		HomePage homePage = new HomePage(driver);
		System.out.println("--- Executing Class 3: Page Metadata Validation ---");
		
		// Verify the Placeholder in Search Textbox
		String expectedPlaceholder = "Search for items/categories";
		String actualPlaceholder = homePage.getSearchPlaceholder();
		Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Search Textbox placeholder mismatch.");

		// Verify the Title of the page
		String expectedTitle = "QKart";
		String actualTitle = homePage.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Page Title mismatch.");

		// Verify the url contains = 'https'
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.startsWith("https"), "URL should start with 'https'.");
	}
}
