
For reference, here is the complete output from a successful run of the project as
generated on Windows 10.

Note that on my machine (a reasonably powerful Windows 10 laptp), the line...

`[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jpackage-win-installer) @ maven-jpackage-template ---`

...can take a minute or two to run.

``` 
C:\Users\wiver\src\shade-test>mvn clean install
WARNING: Using incubator modules: jdk.incubator.jpackage
[INFO] Scanning for projects...
[INFO]
[INFO] --------------< com.doublerobot:maven-jpackage-template >---------------
[INFO] Building maven-jpackage-template 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ maven-jpackage-template ---
[INFO] Deleting C:\Users\wiver\src\shade-test\target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\wiver\src\shade-test\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ maven-jpackage-template ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to C:\Users\wiver\src\shade-test\target\classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\wiver\src\shade-test\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ maven-jpackage-template ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ maven-jpackage-template ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ maven-jpackage-template ---
[INFO] Building jar: C:\Users\wiver\src\shade-test\target\maven-jpackage-template-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.2.4:shade (default) @ maven-jpackage-template ---
[INFO] Including com.github.javafaker:javafaker:jar:1.0.2 in the shaded jar.
[INFO] Including org.apache.commons:commons-lang3:jar:3.5 in the shaded jar.
[INFO] Including org.yaml:snakeyaml:jar:android:1.23 in the shaded jar.
[INFO] Including com.github.mifmif:generex:jar:1.0.2 in the shaded jar.
[INFO] Including dk.brics.automaton:automaton:jar:1.11-8 in the shaded jar.
[INFO] Excluding org.openjfx:javafx-controls:jar:15 from the shaded jar.
[INFO] Excluding org.openjfx:javafx-controls:jar:win:15 from the shaded jar.
[INFO] Excluding org.openjfx:javafx-graphics:jar:15 from the shaded jar.
[INFO] Excluding org.openjfx:javafx-graphics:jar:win:15 from the shaded jar.
[INFO] Excluding org.openjfx:javafx-base:jar:15 from the shaded jar.
[INFO] Excluding org.openjfx:javafx-base:jar:win:15 from the shaded jar.
[INFO] Replacing C:\Users\wiver\src\shade-test\target\shaded-jar\maven-jpackage-template.jar with C:\Users\wiver\src\shade-test\target\shaded-jar\maven-jpackage-template-1.0-SNAPSHOT-shaded.jar
[INFO]
[INFO] --- maven-antrun-plugin:3.0.0:run (prepare) @ maven-jpackage-template ---
[INFO] Executing tasks
[INFO]     [unzip] Expanding: C:\Users\wiver\src\shade-test\target\shaded-jar\maven-jpackage-template.jar into C:\Users\wiver\src\shade-test\target\unpacked-shade
[INFO] Executed tasks
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jdeps) @ maven-jpackage-template ---
[INFO] writing to C:\Users\wiver\src\shade-test\target\work\maven.jpackage.template\module-info.java
[INFO]
[INFO] --- moditect-maven-plugin:1.0.0.RC1:add-module-info (add-module-infos) @ maven-jpackage-template ---
[INFO]
[INFO] --- maven-install-plugin:3.0.0-M1:install (default-install) @ maven-jpackage-template ---
[INFO] Skipping artifact installation
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jpackage-win-installer) @ maven-jpackage-template ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  21.201 s
[INFO] Finished at: 2020-11-02T11:33:33-08:00
[INFO] ------------------------------------------------------------------------

C:\Users\wiver\src\shade-test>dir target\*.exe
 Volume in drive C is Blade
 Volume Serial Number is 1234-5678

 Directory of C:\Users\wiver\src\shade-test\target

11/02/2020  11:33 AM        37,172,224 TestApp-1.0.exe
               1 File(s)     37,172,224 bytes
               0 Dir(s)  353,618,784,256 bytes free

C:\Users\wiver\src\shade-test>

```
