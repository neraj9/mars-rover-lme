Implementation details:
1) Java 11, Spring boot based application

2) MarsRoverIT is an integration test

3) The core functionality is implemented in classes: MarsRoverManager.java and MarsRoverProcessor.java(nearly a command pattern implementation)



Improvements needed:

1) MarsRoverScentManager usage as a singleton bean is not the right thing to do.
Another incoming feed will create issues as that other feed will have different boundaries while the previous set of lost scents are being used.
If I had more time, I would have used a prototype instance of MarsRoverScentManager for each input feed.

2) I would have liked to code and test for edge scenarios like invalid inputs



How to run:
1) This is a spring boot application. Right click on MarsRoverApplication and run the java class
2)from command line: mvn spring-boot:run
