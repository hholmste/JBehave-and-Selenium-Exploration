package no.nrk.stuff.fixtures;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class NumberSteps {

	private final Pages pages;

	public NumberSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("I am at the landing page")
	public void openPage() {
		pages.landingPage().open();
	}
	
	@When("I enter the number '$number' into the $position input")
	public void enter(Integer number, String position) {
		pages.landingPage().putNumber(number, position);
	}
	
	@Then("The text in the output box becomes '$message'")
	public void read(String message) {
		pages.landingPage().statusTextIs(message);
	}
	
}
