# JavaFX + Maven = Native Desktop Apps

[JavaFX](https://openjfx.io) + [jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) +
[Maven](http://maven.apache.org) template project for generating native desktop applications.

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/maven-jpackage-template/community)

# Goal

1. Build nice, small cross-platform [JavaFX](https://openjfx.io)-based desktop apps with native installers
    - Apx 20-30mb .dmg, .msi and .deb installers - check out the example builds in
      [releases](https://github.com/wiverson/maven-jpackage-template/releases).
2. Just use Maven - no shell scripts required.
3. Use standard Maven dependency system to automatically manage ordinary jar dependencies
    - Automatically convert ordinary jars into modules
4. Generate [macOS (.dmg), Windows (.msi) and Unix (e.g. deb/rpm)](https://github.com/wiverson/maven-jpackage-template/releases)
installers/packages automatically
with [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)

## Overview

The [jlink](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jlink.html) &
[jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) tools provide nice, small installers,
using the [Java module system](https://www.baeldung.com/java-9-modularity) to generate trimmed custom Java virtual
machines. Unfortunately, the Java module system is in practice a bit difficult to use, as it expects Java libraries to
add special module information.

In a typical scenario, every time a developer adds a standard Maven dependency, the entire build process breaks due to
module problems. This presents a grim choice - either give up on tools like jpackage or give up on Maven dependency
management and return to the bad old days of shell scripts and manually managing library paths.

Instead, this project uses a [Maven plugin](https://github.com/wiverson/jtoolprovider-plugin) to gather and transform
Maven declared dependencies into modules. More technical details about how this works can be found in the Q&A below and
in the documentation for the plugin.

This leaves us with relatively simple Maven project template that can be used to build nice, small native installers for
a JavaFX application using only Maven, Java 15/16, and jpackage. On macOS XCode is required, and on Windows the
free [WiX Toolset](https://wixtoolset.org/). For most dependencies, just add a Maven dependency declaration and things
should just work.

The generated installers come in at around 30-40mb. The included source includes several native desktop demonstration
features - for example, drag-and-drop from the Finder/Explorer, as well as a few macOS Dock integration examples.
Removing the code and the demonstration dependendencies gets a "Hello World" build size closer to 30mb than 40mb.

## Cool Features

Here are few cool things in this template:

- Only uses Java and Maven. No shell scripts required.
- Includes sample [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows) to
  build macOS, Windows and Linux installers
- Demonstrates setting the application icon
- Builds a .dmg on macOS, .msi on Windows, and .deb on Linux
- Now bundles the JavaFX SDK & modules to simplify getting started.
- Template includes examples of many JavaFX / native desktop integration for macOS & Windows (Linux varies).
    - Drag & drop with Finder / Explorer
    - Change the Dock icon dynamically on macOS
    - Menu on the top for macOS, in the window itself on Windows
    - Request user attention (bouncing dock icon) on macOS

# Usage

Once everything is installed (see below) it's really easy to use:

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation...

`mvn clean package`

# Installation

1. Install [Java 15](https://adoptopenjdk.net/) or later.
    - Verify by opening a fresh Terminal/Command Prompt and typing `java --version`.
2. Install [Apache Maven 3.6.3](http://maven.apache.org/install.html) or later and make sure it's on your path.
    - Verify this by opening a fresh Terminal/Command Prompt and typing `mvn --version`.
3. Clone/download this project.
4. On Java 15, you will need to add the jpackage configuration to your MAVEN_OPTS for your shell environment
   ([described in more detail](docs/java-15-jpackage.md)).
    - On Java 15, verify this is working by typing `mvn --version` and notice the warning about using an incubator
      project.
    - Java 16 is expected to bundle jpackage, which will allow you to skip this step.
    - Here's what the output looks like on Windows - notice the first line WARNING.

``` 
C:\Users\wiver\src\shade-test>mvn --version
WARNING: Using incubator modules: jdk.incubator.jpackage
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Users\wiver\devenv\apache-maven-3.6.3\apache-maven-3.6.3\bin\..
Java version: 15.0.1, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-15.0.1.9-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

5. macOS: verify XCode is installed and needed agreements accepted.
    - Just launch XCode and accept, or verify in Terminal with the command `sudo xcodebuild -license`.
6. Windows: install [Wix 3 binaries](https://github.com/wixtoolset/wix3/releases/).
    - As of this writing, merely installing Wix via the installer was sufficient for jpackage to find it.
7. Final step: run `mvn clean install` from the root of the project to generate the `target\TestApp.dmg`
   or `target\TestApp.msi` (installer).
    - Note that the actual generated installer will include a version number in the file name
    - For reference, here is a complete run log for [a successful run on Windows](docs/sample-windows-run.md).

Because these builds use stripped down JVM images, the
[generated installers are in the 30-40mb range](https://github.com/wiverson/maven-jpackage-template/releases).

# Sponsor

This project is sponsored by [ChangeNode.com](https://changenode.com/) - if you would like to add easy automatic
updates, crash reporting, analytics, etc. to your Java/JavaFX desktop application, go check it out... and be sure to
subscribe for more information about desktop Java development.

# Help

Problems? Make sure everything is installed and working right!

- Compiler not recognizing the --release option? Probably on an old JDK.
- Can't find jdeps? Probably on an old JDK.
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Probably don't have [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).

If you need consulting support, feel free to reach out to [ChangeNode.com](https://changenode.com/).

# Miscellaneous Q&A

##### Q: Can you give me a few more details about how this works?

A: In this project, a plugin walks through all the declared Maven dependencies. If the dependency is a module, cool,
drop it into a directory. If the dependency declares a bad module-info, strip it and regenerate it. If it's not a
module (or it was stripped), use jdeps to turn it into a module automatically.

The same plugin is then used to run jdeps on the user code, add the module-info to the app's module, and then finally
run jpackage to tie it all together.

The prior version of this template used the Maven Shade plugin. Sadly, that didn't work with a lot of scenarios, such as
multi-release jars, jars with bad module-info, jars that declared services, etc. So, the new plugin and this updated
template.

##### Q: What about the javafx.web.jmod files? I'm getting errors when I'm trying to use javafx.web?

A: GitHub won't allow cloning a template if the source has files over 10mb in size. The javafx.web components basically
bundle a full native web browser under the covers. As of JavaFX 15 the javafx.web.jmod is roughly 25mb in size. If you
need it, you can [download it](https://gluonhq.com/products/javafx/) and install it in the JavaFX projects in your local
project.

##### Q: Tell me a bit about the Linux version?

A: The current GitHub Workflow for the Linux build runs on a GitHub Ubuntu instance, and by default it generates a
amd64.deb file. jpackage supports other distribution formats, including rpm, so if you want a different packaging format
you can tweak the
[GitHub Action for the Ubuntu build](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-unix.yml)
and
the [jpackage command for Unix](https://github.com/wiverson/maven-jpackage-template/blob/main/src/packaging/unix-jpackage.txt)
to generate whatever you need. As long as you can find the right combination of configuration flags for
[jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) and can set up the GitHub Actions
runner to match, you can generate whatever packaging you might need. If you need, you could set up several combinations
of Maven profile and GitHub Action to generate as many different builds as you wish to support. For example, you could
support generating macOS .dmg and .pkg files, Windows .msi and .exe, Linux .deb and .rpm in several different binary
formats.

##### Q: Any Tips for Windows?

A:
The [Windows GitHub workflow](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-windows.yml)
for this project downloads the Wix Installer toolkit and adds it to the path to automatically build the Windows
installer. On your local dev machine just install [WiX Toolset](https://wixtoolset.org/)
locally instead - it'll be a lot faster.

Make sure that you set the --win-upgrade-uuid in the file `src\packaging\windows-jpackage.txt` to a unique id! This UUID
is used to uniquely identify the app as YOUR app by the installer system, and is critical for allowing users to
seamlessly upgrade. You can quickly [grab a UUID of your own](https://www.uuidgenerator.net/) and pop that value in
instead.

##### Q: What about macOS Signing?

A: You will likely need to add additional options to ship properly on macOS - most notably, you will want to sign and
notarize your app for macOS to make everything work without end user warnings. Check out tools such
as [Gon](https://github.com/nordcloud/gon)
or [this command-line signing tutorial](https://blog.dgunia.de/2020/02/12/signed-macos-programs-with-java-14/).

##### Q: Can I generate macOS installers on Windows, or Windows installers on macOS? Or macOS/Windows on Linux?

A: [No](https://openjdk.java.net/jeps/392), but this project uses GitHub workflows to generate
[macOS](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer.yml)
and
[Windows](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-windows.yml)
installers automatically, regardless of your development platform. This means that (for example)
you could do your dev work on Linux and rely on the GitHub Actions to generate macOS and Windows builds. If you need
help, reach out to [ChangeNode.com](https://changenode.com/).

You still should (of course) do platform specific testing on your apps, but that's a different topic.

##### Q: Does this support auto-updating, crash reporting, or analytics?

A: No... for that, you should check out [ChangeNode.com](https://changenode.com/)!

##### Q: I'd rather use shell scripts.

A: Ok. Check out [JPackageScriptFX](https://github.com/dlemmermann/JPackageScriptFX) - the original shell scripts
used as a reference when I initially started work on this project.

##### Q: I didn't realize JavaFX was so cool - any pointers?

Sure - here's
my [personal list of cool JavaFX resources](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2), which
includes links to a few other big lists of resources.

##### Q: Any Maven tips?

If you are not familiar with the standard Maven build lifecycle, you are highly encouraged to review the documentation
["Introduction to the Build Lifecycle"](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
to understand how Maven builds work.

The project also uses os-activated, platform-specific
[profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html) for configuration - really cool
for setting up platform-specific stuff.