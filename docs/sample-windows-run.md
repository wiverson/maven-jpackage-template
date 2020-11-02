
For reference, here is the complete output from a successful run of the project as
generated on Windows 10.

``` 
C:\Users\wiver\src\shade-test>mvn clean install
WARNING: Using incubator modules: jdk.incubator.jpackage
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< com.doublerobot:shade-test >---------------------
[INFO] Building shade-test 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ shade-test ---
[INFO] Deleting C:\Users\wiver\src\shade-test\target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ shade-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\wiver\src\shade-test\src\main\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ shade-test ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to C:\Users\wiver\src\shade-test\target\classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ shade-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\wiver\src\shade-test\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ shade-test ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ shade-test ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ shade-test ---
[INFO] Building jar: C:\Users\wiver\src\shade-test\target\shade-test-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.2.4:shade (default) @ shade-test ---
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
[INFO] Replacing C:\Users\wiver\src\shade-test\target\shaded-jar\shade-test.jar with C:\Users\wiver\src\shade-test\target\shaded-jar\shade-test-1.0-SNAPSHOT-shaded.jar
[INFO]
[INFO] --- maven-antrun-plugin:3.0.0:run (prepare) @ shade-test ---
[INFO] Executing tasks
[INFO]     [unzip] Expanding: C:\Users\wiver\src\shade-test\target\shaded-jar\shade-test.jar into C:\Users\wiver\src\shade-test\target\unpacked-shade
[INFO] Executed tasks
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jdeps) @ shade-test ---
[INFO] writing to C:\Users\wiver\src\shade-test\target\work\shade.test\module-info.java
[INFO]
[INFO] --- moditect-maven-plugin:1.0.0.RC1:add-module-info (add-module-infos) @ shade-test ---
[INFO]
[INFO] --- maven-install-plugin:3.0.0-M1:install (default-install) @ shade-test ---
[INFO] Skipping artifact installation
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jpackage-win-installer) @ shade-test ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  21.640 s
[INFO] Finished at: 2020-11-02T10:14:20-08:00
[INFO] ------------------------------------------------------------------------
C:\Users\wiver\src\shade-test>dir target
 Volume in drive C is Blade
 Volume Serial Number is 20B2-1569

 Directory of C:\Users\wiver\src\shade-test\target

11/02/2020  10:14 AM    <DIR>          .
11/02/2020  10:14 AM    <DIR>          ..
11/02/2020  10:14 AM    <DIR>          antrun
11/02/2020  10:13 AM    <DIR>          classes
11/02/2020  10:13 AM    <DIR>          generated-sources
11/02/2020  10:14 AM    <DIR>          maven-archiver
11/02/2020  10:13 AM    <DIR>          maven-status
11/02/2020  10:14 AM    <DIR>          moditect
11/02/2020  10:14 AM    <DIR>          modules
11/02/2020  10:14 AM            14,780 shade-test-1.0-SNAPSHOT.jar
11/02/2020  10:14 AM    <DIR>          shaded-jar
11/02/2020  10:14 AM        37,172,224 TestApp-1.0.exe
11/02/2020  10:14 AM    <DIR>          unpacked-shade
11/02/2020  10:14 AM    <DIR>          work
               2 File(s)     37,187,004 bytes
              12 Dir(s)  353,946,611,712 bytes free

C:\Users\wiver\src\shade-test>




