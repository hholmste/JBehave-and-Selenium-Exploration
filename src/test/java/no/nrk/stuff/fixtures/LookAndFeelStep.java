package no.nrk.stuff.fixtures;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class LookAndFeelStep {

	private final Pages pages;

	public LookAndFeelStep(Pages pages) {
		this.pages = pages;
	}

	@Given("I am at the landing page")
	public void openPage() {
		pages.landingPage().open();
	}
	
	@When("I click on the button labelled '$color'")
	public void click(String color) {
		pages.landingPage().click(color);
	}
	
	@Then("The text in the output box becomes '$message'")
	public void read(String message) {
		pages.landingPage().statusTextIs(message);
	}
	
	
}
