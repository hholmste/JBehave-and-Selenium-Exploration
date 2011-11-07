package no.nrk.stuff;

import java.util.List;

import no.nrk.stuff.fixtures.LookAndFeelStep;
import no.nrk.stuff.fixtures.NumberSteps;
import no.nrk.stuff.fixtures.Pages;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
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
 * 	A concrete class that runs multiple stories.
 */
public class MultiBDD extends JUnitStories {

	private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
	private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
	private Pages pages = new Pages(driverProvider);
	private SeleniumContext context = new SeleniumContext();
	private ContextView contextView = new LocalFrameContextView().sized(500, 100);
	private Configuration configuration = configuration();

	public MultiBDD() {
		configuredEmbedder().useExecutorService(MoreExecutors.sameThreadExecutor());
	}

	@Override
	public Configuration configuration() {
		if (configuration == null) {
			Class<?> embeddableClass = this.getClass();

			return new SeleniumConfiguration()
					.useSeleniumContext(context)
					.useWebDriverProvider(driverProvider)
					.useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
					.useStoryLoader(new LoadFromClasspath(embeddableClass))
					.useStoryReporterBuilder(
							new StoryReporterBuilder()
									.withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
									.withDefaultFormats()
									.withFormats(Format.CONSOLE, Format.TXT, Format.HTML, Format.XML));
		} else {
			return configuration;
		}
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(
				configuration(),
				new LookAndFeelStep(pages),
				new NumberSteps(pages),
				lifecycleSteps,
				new WebDriverScreenshotOnFailure(driverProvider, configuration.storyReporterBuilder()))
				.createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "");
	}

}
