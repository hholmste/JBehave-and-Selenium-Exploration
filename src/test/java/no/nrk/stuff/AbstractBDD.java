package no.nrk.stuff;

import no.nrk.stuff.fixtures.LookAndFeelStep;
import no.nrk.stuff.fixtures.NumberSteps;
import no.nrk.stuff.fixtures.Pages;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.jbehave.web.selenium.WebDriverSteps;

import com.google.common.util.concurrent.MoreExecutors;

/**
 * An abstract superclass for running standalone JBehave tests using Selenium
 * Webdriver.
 * 
 * This maps user stories to concrete implementations that have similar names;
 * the classes should follow normal java conventions, while the stories should
 * use train-casing (e.g. the class ColorClicking maps to color_clicking.story).
 * 
 * If a test fails, a screenshot is provided of the moment when the rest failed.
 */
public abstract class AbstractBDD extends JUnitStory {

	private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
	private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
	private Pages pages = new Pages(driverProvider);
	private SeleniumContext context = new SeleniumContext();
	private ContextView contextView = new LocalFrameContextView().sized(500, 100);

	public AbstractBDD() {
		Class<?> embeddableClass = this.getClass();

		Configuration configuration = new SeleniumConfiguration()
				.useSeleniumContext(context)
				.useWebDriverProvider(driverProvider)
				.useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML));
		useConfiguration(configuration);

		addSteps(new InstanceStepsFactory(
				configuration,
				new LookAndFeelStep(pages),
				new NumberSteps(pages),
				lifecycleSteps,
				new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()))
				.createCandidateSteps());

		configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
	}
}
