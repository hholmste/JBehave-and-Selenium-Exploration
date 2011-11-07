Scenario: 	As a user of the color-site,
			I want to be able to change the look and feel of everything,
			so that I can get the website to look and feel awesome
			However, I don't want anything to actually change,
			as this is merely a exploration of BDD

Given I am at the landing page
When I click on the button labelled 'Click this for redness'
Then The text in the output box becomes 'You clicked red'

Given I am at the landing page
When I click on the button labelled 'Click this for the blues'
Then The text in the output box becomes 'You clicked blue'

Given I am at the landing page
When I click on the button labelled 'Click this for green living'
Then The text in the output box becomes 'You clicked green'