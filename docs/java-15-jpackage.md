# Java 15 jpackage Configuration

The [Java 15 branch](https://github.com/wiverson/maven-jpackage-template/tree/java-15) of this template is available,
but no future updates are expected. You are strongly encouraged to move to Java 16, where jpackage is fully integrated
into the JDK.

In Java 15, the jpackage tool is only available as an incubator project. This means that you have to pass a special flag
to the JVM to enable it. This project relies on [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin)
to perform key build steps. To generate the actual installers, the jpackage tool must be available to the ToolProvider
API. Adding a `MAVEN_OPTS` environment variable is the solution.

## macOS & Linux

On macOS and Linux this can be done by adding to the following line to the `~/.zshrc` file (or a similar file on Linux,
depending on your preferred shell).

`export MAVEN_OPTS="--add-modules jdk.incubator.jpackage"`

## Windows

Current versions of Windows 10 have a nice UI for adding an environment variable. You can find it in the modern control
panel via search - just start a search for "env" and that should bring up the appropriate control panel. Note that on
Windows, you don't need the quote marks - here's a
[screenshot illustrating the proper configuration for a Windows 10 environment](https://github.com/wiverson/maven-jpackage-template/issues/2)
.

## IntelliJ Maven Options Bug

Unfortunately, as of this writing adding this entry to the IntelliJ options for Maven (either in the IntelliJ Maven JVM
importer UI or via `project-directory/.mvn/jvm.config`) will break the Maven sync. This bug is tracked by JetBrains as
[IDEA-246963](https://youtrack.jetbrains.com/issue/IDEA-246963). There is a `.mvn/Xjvm.config file` in this project -
once the bug is fixed, or if you use a different editor, just try renaming that file to `jvm.config`.

## Summary

So, to summarize: if you are on Java 15 you'll have to go through extra hoops to get Maven to work, and you'll likely
run into compatibility issues due to a bug in IntelliJ. Or, just update to Java 16 and call it a day.
