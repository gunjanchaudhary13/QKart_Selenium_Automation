package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class TestClass2 extends BaseTest {
	@Test
	public void PageContentValidation() {
		System.out.println("--- Executing Class 2: Page Content Validation ---");
		HomePage home = new HomePage(driver);
		int imageCount = home.getImageCount();
		int linksCount = home.getLinkCount();
		//System.out.println("Total images found: " + imageCount);
		//System.out.println("Total links found: " + linksCount);

		Assert.assertTrue(imageCount > 2, "Expected more than 5 images on the Home page.");
		Assert.assertTrue(linksCount > 2, "Expected more than 5 links on the Home page.");

	}

}
