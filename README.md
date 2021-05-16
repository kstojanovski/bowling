# Repository: bowling

Two projects were added in this repository which are implementing the bowling game with **Java** as programming language.

## Project: bowling

The first project implementation is more **procedural like** where static methods are used for the business logic.
For testing the game profiles are used in form of XML files. Also profile validation is implemented.

## Project: bowling2

The second project can be seen as an improvement of the first project where **more object orientation** is used for the workflow process.
Also some design patterns are used like:
* Factory
* Strategy
* Observer

This project has not profiles as the previous one but many unit test which were realized with the testing frameworks **Junit** and **Mockito**.
Unit test coverage is over 90%.

## Project: bowling-third-version

This version was made with many classes which are separated into packages like:
* model
* calculation
* process

The process is decribed trough the many classes and the calculation are also done separately trough the classes.

Unit test and manual test is also defined.

This version is easier to understand because the natural flow of the process is build up int the splitted classes. Part of the calculation are done on setting the score value and the special cases like the spare and strike have extra calcualtion classes.
