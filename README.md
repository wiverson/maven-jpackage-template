
# maven-jpackage-template

### Goal

1. Build nice, small cross-platform JavaFX-based desktop apps with native installers
2. Continue to use the standard Maven dependency system to automatically manage transitive dependencies

### Problems

The jlink/jpackage tools which provide nice, small installers rely on the new Java
module system. This system is a bit difficult to use, as it expects the various
Java libraries used by an application to add additional information (typically either via
extra manifest entries or a new, compiled module-info.java/.class).

In the worst-case scenario, every time a developer adds a standard Maven dependency, the entire
build process breaks due to module problems. This presents a grim choice - either give up on jpackage to produce
nice, tiny installers, or give up on Maven dependency management and return to the bad old days of shell scripts
and manually managing library paths.

### Solution

In this sample project, these problems are manged through the use of a single
application-scoped [shaded jar](https://maven.apache.org/plugins/maven-shade-plugin/). 
The application's normal Maven dependencies are merged into a single JAR, and then jdeps automatically 
generates the module-info.java.

While this is a *terrible* strategy for libraries, it works just fine for end-user
desktop applications.

So, what we have here is a pretty small, simple Maven project template that can be used to 
build nice, small native installers for a JavaFX application using only Maven, Java 15, and 
the jpackage required macOS XCode or Windows [WiX Toolset](https://wixtoolset.org/).

On macOS and Windows, these installers are coming in at around 30-40MB. This demonstration app
includes several native desktop demonstration features - for example, drag-and-drop from the Finder/Explorer,
as well as a few macOS Dock integration examples. The "Hello World" versions are closer to 30MB than 40MB.

This also provides an eventual path for migrating to fully modularized libraries. As libraries are converted to
modules, eventually the build could be migrated to Maven-managed modules, perhaps eventually leveraging
[Maven copy-dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/copy-dependencies-mojo.html).

# Usage

Once everything is installed (see below) it's really easy to use:

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation...

`mvn clean package`

# Installation

1. Install [Java 15](https://adoptopenjdk.net/). Verify this by opening a fresh Terminal or
Command Prompt and typing `java --version`.
2. Install [Apache Maven](http://maven.apache.org/install.html) and make sure it's on your path.
Verify this by opening a fresh Terminal or Command Prompt and typing `mvn --version`.
3. Clone/download this project.
4. Download and the [platform-specific JavaFX](https://gluonhq.com/products/javafx/) files.
5. Place the contents of the JavaFX jmods and JavaFX SDK in the proper locations in your project. 
Here's a sample showing what that should look like:

![Install Sample](docs/file-layout.png)

6. Add the jpackage configuration to your MAVEN_OPTS for your shell environment (described in 
more detail below).
As of Java 15, you can verify this is working by observing the warning about using an incubator
project. Here's what the output looks like on Windows - notice the first line WARNING.
``` 
C:\Users\wiver\src\shade-test>mvn --version
WARNING: Using incubator modules: jdk.incubator.jpackage
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Users\wiver\devenv\apache-maven-3.6.3\apache-maven-3.6.3\bin\..
Java version: 15.0.1, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-15.0.1.9-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

7. If you are running on macOS, make sure you have XCode installed and accepted any needed
agreements. Try opening Terminal and running the command `hdiutil` and make sure it's on the path.
8. If you are running on Windows, install the [Wix 3 binaries](https://github.com/wixtoolset/wix3/releases/).
As of this writing, merely installing Wix via the installer was sufficient for jpackage to find it.
9. Once all that is working, you should be able to just run `mvn clean install` from the root of the project
to generate the `target\TestApp.dmg` or `target\TestApp.exe` (installer).
For reference, here is a complete run log for [a successful run on Windows](docs/sample-windows-run.md).

Because these builds use stripped down JVM images, the final installers on both macOS and Windows are in
the 30-40MB range.

## jpackage Configuration

This project relies on [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin) 
to perform key build steps. To generate the actual installers, the jpackage tool must be available to the ToolProvider 
API.  For most people, adding a `MAVEN_OPTS` environment variable will work nicely.

For example, on macOS, this can be done by adding to
the following line to the `~/.zshrc` file.

`export MAVEN_OPTS="--add-modules jdk.incubator.jpackage"`

Current versions of Windows 10 have a nice UI for adding an environment
variable. You can find it in the modern control panel via search -
just start a search for "env" and that should bring up the appropriate control panel.

Unfortunately, as of this writing adding this entry to the IntelliJ
options for Maven (either in the IntelliJ Maven JVM importer UI or 
via `project-directory/.mvn/jvm.config`) will break the Maven sync. 
This bug is tracked by JetBrains as 
[IDEA-246963](https://youtrack.jetbrains.com/issue/IDEA-246963).
There is a `.mvn/Xjvm.config file` in this project - once the bug is fixed,
 or if you use a different editor, 
just try renaming that file to `jvm.config`. Or, presumably, when Java 16 
ships and jpackage is no longer in incubation this will just go away as an issue.

# Help

Problems? Make sure everything is installed and working right!

- Compiler not recognizing the --release option? Probably on an old JDK.
- Can't find jpackage? Make sure the MAVEN_OPTS are set right.
- Can't find jdeps? Probably on an old JDK.
- Can't find javafx.base? Probably didn't install the javafx jmods and/or sdk correctly. Check the screenshot
above to make sure your directory structure is right. The build will NOT work out of the box until you install
one of these! (This might change in the future)

# Miscellaneous

Q: What about the Linux versions? Just macOS and Windows? You Linux-hating monster!

A: I'm pretty sure if you are a Java developer working on Linux you can figure out how to copy-and-paste
and then tweak the pom.xml to get it working with Linux.  I love Linux for server-side work, but
I don't use it for desktop.  It's a more productive use of my time to focus on the two (fussy)
macOS and Windows platforms for now. That said, feel free to [reach out](https://doublerobot.com/contact)
if you'd like to incentivize me to add Linux, or send a PR if you are interested...  :)

Q: Any Tips for Windows?

A: As of this writing, the Windows options will work, but the lack of a version number means that right now
you have to manually uninstall and reinstall via the Windows Control Panel to update to a new version. Not a
big deal for development, but when you go to install an update you'll have to uninstall/reinstall manually.  
In a proper CI build, the installer version should be set by the CI system. Use this project as a starting
point and add the version info based on your CI approach.

Q: What about macOS Signing?

A: This is a good starting point, but you will likely need to add additional options to ship - most notably,
you may want to sign and/or notarize your app for macOS. As of this writing, you may want to check out tools
such as [Gon](https://github.com/nordcloud/gon) 
or [this command-line signing tutorial](https://blog.dgunia.de/2020/02/12/signed-macos-programs-with-java-14/).

Q: Can I generate macOS installers on Windows, or Windows installers on macOS?

A: [No.](https://openjdk.java.net/jeps/392) I strongly suggest some kind of CI system that supports both macOS 
and Windows runners if you are doing this professionally. If you are looking for someone to help out with setting 
this up, feel free to [make contact](https://doublerobot.com/contact).

Q: Does this support auto-updating, crash reporting, or analytics?

A: No... but that sure would be interesting. If you are looking for someone to help out with setting this up, feel free 
 to [make contact](https://doublerobot.com/contact)...

Q: I'd rather use shell scripts.

A: Cool - check out [JPackageScriptFX](https://github.com/dlemmermann/JPackageScriptFX) - the original shell scripts
that were the start of this project.
