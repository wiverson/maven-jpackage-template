
# shade-test

This is an example of how to build native installers for a JavaFX
application using nothing but a Maven build.

It relies on the [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin) 
for the key build steps. To generate the actual installers, the jpackage tool
must be available to the ToolProvider API.  For most people, adding a
MAVEN_OPTS environment variable will work nicely.

For example, on macOS, this can be done by adding to
the following line to the `~/.zshrc` file.

`export MAVEN_OPTS="--add-modules jdk.incubator.jpackage"`

Unfortunately, as of this writing adding this entry to the IntelliJ
options for Maven will break the Maven sync. This bug is tracked by 
JetBrains as [IDEA-246963](https://youtrack.jetbrains.com/issue/IDEA-246963).

## Usage

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation...

`mvn clean package`
