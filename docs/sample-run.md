
For reference, here is the complete output from a successful run of the project.

Note that on my (reasonably modern) Windows 10 laptop the line...

`[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jpackage-win-installer) @ maven-jpackage-template ---`

...can take a little while to run.

Here is a sample execution of `mvn clean install`

``` 
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------< com.changenode:maven-jpackage-template >---------------
[INFO] Building maven-jpackage-template 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ maven-jpackage-template ---
[INFO] Deleting /Users/wiverson/src/sample-project/target
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 3 resources to /Users/wiverson/src/sample-project/target/packaging
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ maven-jpackage-template ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to /Users/wiverson/src/sample-project/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory /Users/wiverson/src/sample-project/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ maven-jpackage-template ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0-M5:test (default-test) @ maven-jpackage-template ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ maven-jpackage-template ---
[INFO] Building jar: /Users/wiverson/src/sample-project/target/dependency/maven-jpackage-template-1.0-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-dependency-plugin:3.1.2:copy-dependencies (copy-dependencies) @ maven-jpackage-template ---
[INFO] Copying javafaker-1.0.2.jar to /Users/wiverson/src/sample-project/target/dependency/javafaker-1.0.2.jar
[INFO] Copying commons-lang3-3.5.jar to /Users/wiverson/src/sample-project/target/dependency/commons-lang3-3.5.jar
[INFO] Copying snakeyaml-1.23-android.jar to /Users/wiverson/src/sample-project/target/dependency/snakeyaml-1.23-android.jar
[INFO] Copying generex-1.0.2.jar to /Users/wiverson/src/sample-project/target/dependency/generex-1.0.2.jar
[INFO] Copying automaton-1.11-8.jar to /Users/wiverson/src/sample-project/target/dependency/automaton-1.11-8.jar
[INFO] 
[INFO] --- jtoolprovider-plugin:1.0.34:java-tool (jlink) @ maven-jpackage-template ---
[INFO] 
[INFO] --- maven-install-plugin:3.0.0-M1:install (default-install) @ maven-jpackage-template ---
[INFO] Skipping artifact installation
[INFO] 
[INFO] --- jtoolprovider-plugin:1.0.34:java-tool (jpackage) @ maven-jpackage-template ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  20.392 s
[INFO] Finished at: 2021-03-16T14:34:34-07:00
[INFO] ------------------------------------------------------------------------
```
