spring initilizer : https://start.spring.io/
package:
Spring Web
Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
----------------------------------------------------------------------
fix (version fix JDK):
Fix Option 2: Update the project settings in IntelliJ

If you’re not using Maven or Gradle:

Open File → Project Structure → Project

Set Project SDK to a valid JDK (e.g., 17 or 21)

Set Project language level to match it (e.g., “17 – Sealed types, pattern matching for switch”)

Click Apply and OK

Then go to:

File → Settings → Build, Execution, Deployment → Compiler → Java Compiler

Ensure Target bytecode version is also 17 or 21
---------------------------------------------------------
to change the port if default 8080 is in process :

Open this file:

src/main/resources/application.properties

Add this line:

server.port=9090

Now your app will start on
http://localhost:9090

guide :-
https://www.codingshuttle.com/spring-boot-handbook/spring-boot-tutorial-a-comprehensive-guide-for-beginners/