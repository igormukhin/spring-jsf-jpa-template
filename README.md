Application Template: Spring Boot, Security, JSF, EclipseLink
=================================================================

STATUS: UNFINISHED !!!

### Running

This setup can run in all possible ways:

* Either deploy on Tomcat inside Eclipse
* Or execute `SpringBootFacesApplication` as Java application
* Or run `mvn spring-boot:run`
* Or run `mvn package` and then:
  * either deploy `target\projectname.war`
  * or run `java -jar target\projectname.war` 

### More things shown

* How to configure Faces View Scope with Spring
* How to include PrimeFaces
* How to include a custom theme for PrimeFaces (in this case primefaces-bootstrap-theme)
* How to include Twitter Bootstrap CSS for responsive layout
* Spring Boot Actuator is added under /sysinfo path (try for example /sysinfo/metrics)
* How to write simple http tests
* ... much more