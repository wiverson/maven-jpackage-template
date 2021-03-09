
For reference, here is the complete output from a successful run of the project as
generated on Windows 10.

Note that on my machine (a reasonably powerful Windows 10 laptp), the line...

`[INFO] --- jtoolprovider-plugin:1.0.23:java-tool (jpackage-win-installer) @ maven-jpackage-template ---`

...can take a minute or two to run.

``` 
CC:\Users\wiver\src\maven-jpackage-template>mvn clean install
WARNING: Using incubator modules: jdk.incubator.jpackage
[INFO] Scanning for projects...
[INFO]
[INFO] --------------< com.doublerobot:maven-jpackage-template >---------------
[INFO] Building maven-jpackage-template 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ maven-jpackage-template ---
[INFO] Deleting C:\Users\wiver\src\maven-jpackage-template\target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 3 resources to C:\Users\wiver\src\maven-jpackage-template\target/packaging
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.30:collect-modules (collect-modules) @ maven-jpackage-template ---
[INFO] Found 6 modular jars (stripped 1) and 5 ordinary jars.
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ maven-jpackage-template ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 5 source files to C:\Users\wiver\src\maven-jpackage-template\target\classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ maven-jpackage-template ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory C:\Users\wiver\src\maven-jpackage-template\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ maven-jpackage-template ---
[INFO] No sources to compile
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ maven-jpackage-template ---
[INFO] No tests to run.
[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ maven-jpackage-template ---
[INFO] Building jar: C:\Users\wiver\src\maven-jpackage-template\target\maven-jpackage-template-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.30:java-tool (jdeps) @ maven-jpackage-template ---
[INFO] writing to C:\Users\wiver\src\maven-jpackage-template\target\work\maven.jpackage.template\module-info.java
[INFO]
[INFO] --- moditect-maven-plugin:1.0.0.RC1:add-module-info (add-module-infos) @ maven-jpackage-template ---
[INFO]
[INFO] --- maven-install-plugin:3.0.0-M1:install (default-install) @ maven-jpackage-template ---
[INFO] Skipping artifact installation
[INFO]
[INFO] --- jtoolprovider-plugin:1.0.30:java-tool (jpackage) @ maven-jpackage-template ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  17.565 s
[INFO] Finished at: 2021-03-08T16:51:35-08:00
[INFO] ------------------------------------------------------------------------
```
