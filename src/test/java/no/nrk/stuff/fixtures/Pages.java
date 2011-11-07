package no.nrk.stuff.fixtures;

import org.jbehave.web.selenium.WebDriverProvider;

/**
 * Abstracts away the actual access to the web-site and navigation on it.
 */
public class Pages {

	private LandingPage landingPage;
	private final WebDriverProvider driverProvider;
	
	public Pages(WebDriverProvider driverProvider) {
		this.driverProvider = driverProvider;
	}

	/**
	 * Retrieve the landing page.
	 * 
	 * In a more complex application, this would be one of many services offered
	 * by the Pages object.
	 * 
	 * @return
	 */
	public LandingPage landingPage() {
		if (landingPage == null) {
			landingPage = new LandingPage(driverProvider);
		}
		return landingPage;
	}

}
