# JavaFX + Maven = Native Desktop Apps

[JavaFX](https://openjfx.io) + [jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) +
[Maven](http://maven.apache.org) template project for generating native desktop applications.

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/maven-jpackage-template/community)

# Goal

1. Build nice, small cross-platform [JavaFX](https://openjfx.io)-based desktop apps with native installers
   - Apx 30-40mb .dmg, .msi and .deb installers - check out the example builds in
      [releases](https://github.com/wiverson/maven-jpackage-template/releases).
2. Just use Maven - no shell scripts required.
   - Use standard Maven dependency system to manage dependencies
3. Generate [macOS (.dmg), Windows (.msi) and Unix (e.g. deb/rpm)](https://github.com/wiverson/maven-jpackage-template/releases)
installers/packages automatically with [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)

## Overview

This template uses a [Maven plugin](https://github.com/wiverson/jtoolprovider-plugin) to generate a custom JVM
and installer package for a JavaFX application.

The basic requirements are just Java 15/16 and Maven. 

- On macOS XCode is required.
- On Windows the free [WiX Toolset](https://wixtoolset.org/) is required.

The project includes [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)
which automatically generate macOS, Windows, and Linux installers.

The generated installers come in at around 30-40mb. The example source in the project includes demonstrations of several 
native desktop features - for example, drag-and-drop from the Finder/Explorer, as well as a few macOS Dock integration examples.
Removing the code and the demonstration dependendencies gets a "Hello World" build size closer to 30mb than 40mb.

## Key Features

Here are few cool things in this template:

- Only uses Java and Maven. No shell scripts required.
- Includes sample [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows) to
  build macOS, Windows and Linux installers
- Demonstrates setting the application icon
- Builds a .dmg on macOS, .msi on Windows, and .deb on Linux
- Bundles the JavaFX SDK & modules to simplify getting started.
- Template includes several examples of JavaFX / native desktop integration
    - Drag & drop with Finder / Explorer
    - Change the Dock icon dynamically on macOS
    - Menu on the top for macOS, in the window itself on Windows
    - Request user attention (bouncing dock icon) on macOS

Once you get started, you might find these lists of tutorials, tools, libraries for 
[JavaFX](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2) 
and general [Java desktop integration](https://gist.github.com/wiverson/e9dfd73ca9a9a222b2d0a3d68ae3f129) helpful.

# Usage

Once everything is installed (see below) it's really easy to use:

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation (including generating the custom JVM)...

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
- Can't find jpackage? Probably haven't set up your system to [allow Java 15 to enable preview packages]((docs/java-15-jpackage.md)).
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Probably don't have [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).

If you need consulting support, feel free to reach out to [ChangeNode.com](https://changenode.com/).

# Q&A

If you are using the template, browsing the [Q&A](docs/qna.md) is highly recommended.
