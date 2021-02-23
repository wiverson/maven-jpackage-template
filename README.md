# JavaFX + Maven = Native Desktop Apps

[JavaFX](https://openjfx.io) + [jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) +
[Maven](http://maven.apache.org) template project for generating native desktop applications.

[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/maven-jpackage-template/community)

# Goal

1. Build nice, small cross-platform [JavaFX](https://openjfx.io)-based desktop apps with native installers
    - Apx 20-30mb .dmg, .msi and .deb installers - check out the example builds in
      [releases](https://github.com/wiverson/maven-jpackage-template/releases).
2. Just use Maven - no shell scripts required. Use standard Maven dependency system to automatically manage ordinary JAR
   dependencies (including transitive Maven dependencies).
3. Generate [macOS (.dmg) and Windows (.msi) installers](https://github.com/wiverson/maven-jpackage-template/releases)
   automatically with [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)

## Problems

The [jlink](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jlink.html) &
[jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) tools provide nice, small installers,
using the [Java module system](https://www.baeldung.com/java-9-modularity) to generate embedded, stripped down JVMs.
Unfortunately, modules are in practice a bit difficult to use, as it expects Java libraries to add module information (
typically either via extra manifest entries or a new, compiled module-info.java/.class).

In the worst-case scenario, every time a developer adds a standard Maven dependency, the entire build process breaks due
to module problems. This presents a grim choice - either give up on tools like jpackage or give up on Maven dependency
management and return to the bad old days of shell scripts and manually managing library paths.

## Solution

In this sample project, these problems are managed through the use of a single
application-scoped [shaded jar](https://maven.apache.org/plugins/maven-shade-plugin/). The application's normal Maven
dependencies are merged into a single JAR, and then jdeps automatically generates the module-info.java.

While this is a *terrible* strategy for libraries, it works just fine for end-user desktop applications.

So, what we have here is a pretty small, simple Maven project template that can be used to build nice, small native
installers for a JavaFX application using only Maven, Java 15, and jpackage. In addition, on macOS XCode is required,
and on Windows the free [WiX Toolset](https://wixtoolset.org/).

On macOS, Windows and Linux these installers are coming in at around 30-40mb. This demonstration app includes several
native desktop demonstration features - for example, drag-and-drop from the Finder/Explorer, as well as a few macOS Dock
integration examples. The "Hello World" versions are closer to 30mb than 40mb.

This also provides an eventual path for migrating to fully modularized libraries. As libraries are converted to modules,
eventually the build could be migrated to Maven-managed modules, perhaps eventually leveraging
[Maven copy-dependencies](https://maven.apache.org/plugins/maven-dependency-plugin/copy-dependencies-mojo.html).

## Cool Features

Here are few cool things in this template:

- Only uses Maven. No shell scripts required.
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

# Sponsor

This project is sponsored by [ChangeNode.com](https://changenode.com/) - if you would like to add easy automatic
updates, crash reporting, analytics, etc. to your Java/JavaFX desktop application, go check it out.

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
5. On Java 15, add the jpackage configuration to your MAVEN_OPTS for your shell environment (described in more detail
   below).
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

6. macOS: verify XCode is installed and needed agreements accepted.
    - Just launch XCode and accept, or verify in Terminal with the command `sudo xcodebuild -license`.
7. Windows: install [Wix 3 binaries](https://github.com/wixtoolset/wix3/releases/).
    - As of this writing, merely installing Wix via the installer was sufficient for jpackage to find it.
8. Final step: run `mvn clean install` from the root of the project to generate the `target\TestApp.dmg`
   or `target\TestApp.msi` (installer).
    - For reference, here is a complete run log for [a successful run on Windows](docs/sample-windows-run.md).

Because these builds use stripped down JVM images, the
[generated installers are in the 30-40mb range](https://github.com/wiverson/maven-jpackage-template/releases).

## jpackage Configuration

This project relies on [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin)
to perform key build steps. To generate the actual installers, the jpackage tool must be available to the ToolProvider
API. For most people, adding a `MAVEN_OPTS` environment variable will work nicely.

For example, on macOS, this can be done by adding to the following line to the `~/.zshrc` file.

`export MAVEN_OPTS="--add-modules jdk.incubator.jpackage"`

Current versions of Windows 10 have a nice UI for adding an environment variable. You can find it in the modern control
panel via search - just start a search for "env" and that should bring up the appropriate control panel. Note that on
Windows, you don't need the quote marks - here's a
[screenshot illustrating the proper configuration for a Windows 10 environment](https://github.com/wiverson/maven-jpackage-template/issues/2)
.

Unfortunately, as of this writing adding this entry to the IntelliJ options for Maven (either in the IntelliJ Maven JVM
importer UI or via `project-directory/.mvn/jvm.config`) will break the Maven sync. This bug is tracked by JetBrains as
[IDEA-246963](https://youtrack.jetbrains.com/issue/IDEA-246963). There is a `.mvn/Xjvm.config file` in this project -
once the bug is fixed, or if you use a different editor, just try renaming that file to `jvm.config`. Or, presumably,
when Java 16 ships and jpackage is no longer in incubation this will just go away as an issue.

This version of the project uses Maven Profiles to set a platform property. This property is then used to generate paths
to the proper version of the JavaFX modules and the configuration for
the [platform-specific jpackage execution](https://github.com/wiverson/maven-jpackage-template/tree/main/src/packaging).

# How Does This Work?

If you are not familiar with the standard Maven build lifecycle, you are highly encouraged to review the documentation
["Introduction to the Build Lifecycle"](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
to understand how Maven builds work.

The project automatically activate platform-specific
[profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html), setting the
properties `javafx.libs` and `javafx.mods` to the proper locations, and also specifying the platform-specific
configuration file.

### Maven package

1. Use [maven-shade-plugin](https://maven.apache.org/plugins/maven-shade-plugin/) to generate a merged JAR containing
   all the Maven managed dependencies. It strips out all the
   existing [module-info](https://www.oracle.com/corporate/features/understanding-java-9-modules.html), unless you
   exclude it that manually via the filters. This generated jar file is saved in the `target/shaded-jar/` folder.
2. Uses the [maven-antrun-plugin](https://maven.apache.org/plugins/maven-antrun-plugin/) to unzip the generated JAR into
   a `target/unpacked-shade/`
3. Runs [jtoolprovider-plugin](https://github.com/wiverson/jtoolprovider-plugin) to
   run [jdeps](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jdeps.html) on the `target/unpacked-shade/`
   directory to generate a new module-info file for the project.
4. Runs [moditect-maven-plugin](https://github.com/moditect/moditect) to compile and add the module-info back into the
   shaded jar file.

### Maven verify

1. _(Optional: requires activating profile add-jlink-package, e.g. `mvn clean verify -Padd-jlink-package`)_
   jtoolprovider-plugin will run [jlink](https://www.baeldung.com/jlink) to generate a native image. This is mostly
   helpful for local development only - end-users generally prefer an installer.

### Maven install

1. Tells the [maven-install-plugin](https://maven.apache.org/plugins/maven-install-plugin/) to NOT install the files for
   this project in the local .m2 repository. No need to waste time and disk space copying unneeded files.
2. If Maven detects the build is running on macOS or Windows, it will automatically add
   running [jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) via jtoolprovider-plugin
   to the install phase.

# Help

Problems? Make sure everything is installed and working right!

- Compiler not recognizing the --release option? Probably on an old JDK.
- Can't find jdeps? Probably on an old JDK.
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Probably don't have [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).

If you need consulting support, feel free to reach out to [ChangeNode.com](https://changenode.com/).

# Miscellaneous Q&A

##### Q: What about the javafx.web.jmod files? I'm getting errors when I'm trying to use javafx.web?

A: GitHub won't allow cloning a template if the source has files over 10mb in size. The javafx.web components basically
bundle a full native web browser under the covers. As of JavaFX 15 the javafx.web.jmod is roughly 25mb in size. If you
need it, you can [download it](https://gluonhq.com/products/javafx/) and install it in the JavaFX projects in your local
project.

##### Q: A few things seem to be hard-coded...?

A: Yeah. There are a lot of moving parts in a build script like this. Most notably, the name of the generated
application is set to Test-App-1.0 in a few places. Unfortunately, this is kind of complicated - for example, the name
of the build application exists both in the GitHub Actions and the pom.xml. I tried to strike a balance between using
various properties to avoid duplication and (worst case) degenerating into a sea of strange expressions. Hopefully you
can follow everything, but if you have questions or need consulting support you can always reach out.

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

As of this writing, the Windows options will work, but the lack of a version number means that right now you have to
manually uninstall and reinstall via the Windows Control Panel to update to a new version. Not a big deal for
development, but when you go to install an update you'll have to uninstall/reinstall manually.

In a proper CI build, the installer version should be set by the CI system. Use this project as a starting point and add
the version info based on your CI and versioning approach. Perhaps some combination of a GUID and/or timestamp.

As an aside, [ChangeNode](https://changenode.com) supports forcing reinstalls of .msi regardless of the installer
version - for example, if you need to revert a deployment to an earlier version due to a regression.

##### Q: What about macOS Signing?

A: You will likely need to add additional options to ship properly on macOS - most notably, you will want to sign and
notarize your app for macOS to make everything work without end user warnings. Check out tools such
as [Gon](https://github.com/nordcloud/gon)
or [this command-line signing tutorial](https://blog.dgunia.de/2020/02/12/signed-macos-programs-with-java-14/).

##### Q: Can I generate macOS installers on Windows, or Windows installers on macOS? Or macOS/Windows on Linux?

A: [No,](https://openjdk.java.net/jeps/392) but this project uses GitHub workflows to generate
[macOS](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer.yml)
and
[Windows](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-windows.yml)
installers automatically, regardless of your development platform. This means that (for example)
you could do your dev work on Linux and rely on the GitHub Actions to generate macOS and Windows builds. If you need
help, reach out to [ChangeNode.com](https://changenode.com/).

You still should (of course) do QA on your apps, but that's a different topic.

##### Q: Does this support auto-updating, crash reporting, or analytics?

A: No... for that, you should check out [ChangeNode.com](https://changenode.com/)!

##### Q: I'd rather use shell scripts.

A: Cool - check out [JPackageScriptFX](https://github.com/dlemmermann/JPackageScriptFX) - the original shell scripts
used as a reference when I initially started work on this project.

##### Q: I didn't realize JavaFX was so cool - any pointers?

Sure - here's my [personal list of cool JavaFX resources](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2),
which includes links to a few other big lists of resources.
