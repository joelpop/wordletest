# wordletest

_An example use of Vaadin TestBench to demonstrate how to use Vaadin TestBench to test an arbitrary, non-Vaadin application._

This project uses Vaadin TestBench to test the New York Times' popular Wordle game/puzzle application.

It is a fairly comprehensive example of using TestBench—though not a comprehensive testing of Wordle. This example not only has no access to the Wordle application source code, the application it is testing isn’t even a Vaadin application. When testing an actual Vaadin-based application, you will be able to use TestBench's built-in Vaadin elements instead of having to create custom ones as were done for this example.

Also included in the project is an automatic browser webdriver library and a BaseTestCase class to spin everything up and tear it all down.

In addition to Vaadin TestBench, this example uses:
* Maven
* Java 17
* TestNG
* AssertJ
* Lombok
* SLF4J

But feel free to clone this project and swap any of them out for your preferred alternatives.
