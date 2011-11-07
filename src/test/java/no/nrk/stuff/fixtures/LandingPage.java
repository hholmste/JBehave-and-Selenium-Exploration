package no.nrk.stuff.fixtures;

import static org.fest.assertions.Assertions.assertThat;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LandingPage extends WebDriverPage {

	public LandingPage(WebDriverProvider webDriverProvider) {
		super(webDriverProvider);
	}
	
	public void open() {
		get("file:///C:/dev/workspace2/bddEx/src/main/html/index.html");
	}
	
	public void click(String color) {
		WebElement button = findElement(By.xpath("//input[@value='" + color + "']"));
		button.sendKeys(Keys.ENTER);
	}

	public void statusTextIs(String message) {
		WebElement output = findElement(By.id("output"));
		assertThat(output.getText()).isEqualTo(message);
	}

	public void putNumber(Integer number, String position) {
		WebElement input = findElement(By.id(position));
		input.sendKeys("" + number);
	}

}
