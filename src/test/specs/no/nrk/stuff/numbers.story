Scenario: 	As a user of the color clicking web site,
			I want to be able to add numbers
			So that I can utterly break the purpose of the color clicking website.
			
Given I am at the landing page
When I enter the number '2' into the left input
And I enter the number '3' into the right input
And I click on the button labelled 'Calculate'
Then The text in the output box becomes '2 plus 3 is 5'