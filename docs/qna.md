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
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Probably don't have [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).

If you need consulting support, feel free to reach out to [ChangeNode.com](https://changenode.com/).

# Miscellaneous Q&A

#### Q: Can you give me a few more details about how this works?

A: Maven plugins are used to copy all of the project dependencies into a folder, generate a slimmed-down JVM, and
then generate a platform-specific installer. The [pom.xml](https://github.com/wiverson/maven-jpackage-template/blob/main/pom.xml)
is heavily commented!

#### Q: What about the javafx.web.jmod files? I'm getting errors when I'm trying to use javafx.web?

A: GitHub won't allow cloning a template if the source has files over 10mb in size. The javafx.web components basically
bundle a full native web browser under the covers. As of JavaFX 15 the javafx.web.jmod is roughly 25mb in size. If you
need it, you can [download it](https://gluonhq.com/products/javafx/) and install it in the JavaFX projects in your local
project.

If you are delivering a project that essentially amounts to a Java web application bundled as a web application, instead of
bundling JavaFX and a WebKit browser, I would suggest creating a small preferences UI using Swing and 
the [System Tray API](https://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html). With a nice modern look and feel
such as [FlatLaf](https://www.formdev.com/flatlaf/), you can create a very nice preferences panel and then use the
standard Java [Desktop API](https://docs.oracle.com/javase/9/docs/api/java/awt/Desktop.html) to just launch the user's
browser to the local URL.

If you drop me a note that this is something you are interested in, I may go ahead and create a template for this... :)

#### Q: Tell me a bit about the Linux version?

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

#### Q: Any Tips for Windows?

A:

First, make sure you set a custom Windows installer UUID for your project, as described in 
the [pom.xml](https://github.com/wiverson/maven-jpackage-template/blob/main/pom.xml)!

This UUID is used to uniquely identify the app as YOUR app by the installer system, and is critical for allowing users to
seamlessly upgrade. You can quickly [grab a UUID of your own](https://www.uuidgenerator.net/) and pop that value in
instead.


The [Windows GitHub workflow](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-windows.yml)
for this project downloads the Wix Installer toolkit and adds it to the path to automatically build the Windows
installer. On your local dev machine just install [WiX Toolset](https://wixtoolset.org/)
locally instead - it'll be a lot faster.

#### Q: What about macOS Signing?

A: You will likely need to add additional options to ship properly on macOS - most notably, you will want to sign and
notarize your app for macOS to make everything work without end user warnings. Check out tools such
as [Gon](https://github.com/nordcloud/gon)
or [this command-line signing tutorial](https://blog.dgunia.de/2020/02/12/signed-macos-programs-with-java-14/).

#### Q: Can I generate macOS installers on Windows, or Windows installers on macOS? Or macOS/Windows on Linux?

A: [No](https://openjdk.java.net/jeps/392), but this project uses GitHub workflows to generate
[macOS](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer.yml)
and
[Windows](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-windows.yml)
installers automatically, regardless of your development platform. This means that (for example)
you could do your dev work on Linux and rely on the GitHub Actions to generate macOS and Windows builds. If you need
help, reach out to [ChangeNode.com](https://changenode.com/).

You still should (of course) do platform specific testing on your apps, but that's a different topic.

#### Q: Does this support auto-updating, crash reporting, or analytics?

A: No... for that, you should check out [ChangeNode.com](https://changenode.com/)!

#### Q: I'd rather use shell scripts.

A: Ok. Check out [JPackageScriptFX](https://github.com/dlemmermann/JPackageScriptFX) - the original shell scripts
used as a reference when I initially started work on this project.

#### Q: I didn't realize JavaFX was so cool - any pointers?

Sure - here's
my [personal list of cool JavaFX resources](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2), which
includes links to a few other big lists of resources.

#### Q: Any Maven tips?

If you are not familiar with the standard Maven build lifecycle, you are highly encouraged to review the documentation
["Introduction to the Build Lifecycle"](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
to understand how Maven builds work.

The project also uses os-activated, platform-specific
[profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html) for configuration - really cool
for setting up platform-specific stuff.

#### Q: Wait, didn't this project use to try to completely modularize the application first?

A: Correct.

There were two previous versions of this template, and both strategies were discarded as impractical in the real world.

The first attempt was to try to create a shaded jar (a single jar containing all of the project dependencies) and then
use jdeps to create a module-info.java and add it to the shaded jar. This failed to account for things like multi-release
jars and jars with service declarations. While it worked for trivial applications, it fell apart with more complex things
- in particular, attempting to integrate Spring Boot into the application caused many issues to appear.

The second attempt to try to generate a modularized version took a more sophisticated approach. A new plugin goal was
added. In brief, the plugin would walk through the entire Maven dependency tree and sort all of the declared dependencies
into folders. The dependencies that already were modularized (both basic and multi-release jars) went into one folder,
and ordinary non-modularized jars went into another. The plugin would then attempt to use jdeps to automatically generate
module-info.java and add the compiled module-info.classes into each jar.

Unfortunately, this also failed. There were numerous errors, such a circular references between libraries (e.g. slf4j and
logback). Some jars would only work when jdeps generated open module-info.java files, and others would only work when
jdeps generated module-info.java files with package level exports. Many, many jars would have large numbers of packages
exposed, which mean that the resulting module-info.java files contained many, many entries. The error messages and the
resolution for these messages were very, very confusing. The terminology around modules is unfortunately very inaccessible
to a typical Java developer - for example, a "static" declaration in a Java module-info.java is used to denote a concept
similar to the Maven notion of "provided" - but unfortunately jdeps fails to run if a needed module is declared as
a transitive reference. Even if it's optional.

In the end, even with the plugin, the error messages and the resolution of those messages is just simply not something
a typical Java developer can be expected to understand or fix.

Which brings us back to this project. The end result is effective the same - just change the `<jvm.modules>javafx.media,javafx.controls,javafx.fxml,java.logging</jvm.modules>` declaration in the pom.xml to
specify the JVM modules you need and just skip all trying to modularize your app.

For now, I would consider the creation and use of module-info.java to effective be a system programming interface
for the JDK itself and system-level modules such as JavaFX itself, where the entire dependency tree is very carefully
enforced - and likely includes native code. For ordinary developers, just enjoy the benefits of a trimmed down custom
JVM and don't worry about it. After all the challenges working with
 [(incorrectly written!) module-info.java files](https://github.com/sormuras/modules/tree/main/doc/suspicious)
I found just in the Spring Boot dependency graph, I would highly suggest that maintainers for ordinary, non-native
Java libraries remove their existing module-info.java files.
