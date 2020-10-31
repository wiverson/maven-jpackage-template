
# shade-test

This is an example of how to build native installers for a JavaFX
application using nothing but a Maven build.

## jpackage Configuration

This project relies on [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin) 
to perform key build steps. To generate the actual installers, the 
jpackage tool must be available to the ToolProvider API.  
For most people, adding a `MAVEN_OPTS` environment variable 
will work nicely.

For example, on macOS, this can be done by adding to
the following line to the `~/.zshrc` file.

`export MAVEN_OPTS="--add-modules jdk.incubator.jpackage"`

Current versions of Windows 10 have a nice UI for adding an environment
variable. You can find it in the modern control panel via search -
just start a search for "env" and that should bring it up.

Unfortunately, as of this writing adding this entry to the IntelliJ
options for Maven (either in the IntelliJ Maven JVM importer UI or 
via `project-directory/.mvn/jvm.config`) will break the Maven sync. 
This bug is tracked by JetBrains as 
[IDEA-246963](https://youtrack.jetbrains.com/issue/IDEA-246963).
There is a `.mvn/Xjvm.config file` in this project - once the bug is fixed,
 or if you use a different editor, 
just try renaming that file to `jvm.config`. Or, presumably, when Java 16 
ships and jpackage is no longer in incubation.


## Usage

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation...

`mvn clean package`
