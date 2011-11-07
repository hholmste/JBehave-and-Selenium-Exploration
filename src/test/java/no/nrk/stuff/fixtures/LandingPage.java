package no.nrk.stuff.fixtures;

import static org.fest.assertions.Assertions.assertThat;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Page object for the only page in the project.
 * 
 * The Page object is responsible for interacting with the selenium
 * implementation in order to navigate around the actual web-page.
 * 
 * It is in this object, and only this object, that knowledge of how the
 * web-page is implemented should live.
 */
public class LandingPage extends WebDriverPage {

	/**
	 * Constructs the landing page with a particular webdriver implementation.
	 * 
	 * @param webDriverProvider
	 */
	public LandingPage(WebDriverProvider webDriverProvider) {
		super(webDriverProvider);
	}

	/**
	 * Find and load the web site's landing page.
	 */
	public void open() {
		get("file:///C:/dev/workspace2/bddEx/src/main/html/index.html");
	}

	/**
	 * Clicks a has the label given as input. If no such button exist, this will
	 * throw an exception, and the test will fail.
	 * 
	 * @param buttonLabel
	 */
	public void click(String buttonLabel) {
		WebElement button = findElement(By.xpath("//input[@value='" + buttonLabel + "']"));
		button.sendKeys(Keys.ENTER);
	}

	/**
	 * Checks to see if the output-field contains the exact message given as
	 * input.
	 * 
	 * @param message
	 */
	public void statusTextIs(String message) {
		WebElement output = findElement(By.id("output"));
		assertThat(output.getText()).isEqualTo(message);
	}

	/**
	 * Types a number into any input field with the id given as input.
	 * 
	 * @param number
	 * @param id
	 */
	public void putNumber(Integer number, String id) {
		WebElement input = findElement(By.id(id));
		input.sendKeys("" + number);
	}

}
