# Java + Maven + GitHub Actions = Native Desktop Apps

[JavaFX](https://openjfx.io) or Swing + [jpackage](https://docs.oracle.com/en/java/javase/18/docs/specs/man/jpackage.html) +
  [Maven](http://maven.apache.org) template project for generating native desktop applications.

# Goals

1. Build nice, small cross-platform JavaFX or Swing desktop apps with native installers!
2. Just use Maven - no shell scripts required!
3. Use standard Maven dependency system to manage dependencies.
4. Generate [MacOS (.dmg), Windows (.msi) and Unix (e.g. deb/rpm)](https://github.com/wiverson/maven-jpackage-template/releases)
installers/packages in the cloud
with [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows).

Out of the box, this template generates "Hello World" installers - 30-40mb .dmg, .msi and .deb files. Check out the
example builds in
[releases](https://github.com/wiverson/maven-jpackage-template/releases).

If you are on MacOS, you notice the MacOS builds are not signed. Current versions of MacOS will report
installers downloaded via browsers as damaged/unopenable. You can [clear this flag via the command-line](docs/apple-sign-notarize.md). As this is not a reasonable solution for end users, a GitHub Action is included to notarize, sign, and staple 
MacOS installers, but the secrets aren't set up for this repository by default. You will need an Apple Developer account
to get this working. [More information on MacOS signing](docs/apple-sign-notarize.md).

# Overview

This template uses a [Maven plugin](https://github.com/wiverson/jtoolprovider-plugin) to generate a custom JVM and
installer package for a JavaFX application. It can easily be adapted to work with Swing instead.

Check out the [announcements and recent updates](https://github.com/wiverson/maven-jpackage-template/discussions/categories/announcements).

## Requirements

- [Java 18](https://bell-sw.com/pages/downloads/#/java-18-current).
    - If you are using JavaFX, use an SDK that has JavaFX bundled:
      - [Liberica with JavaFX](https://bell-sw.com/pages/downloads/#/java-18-current)
      - [Azul Zulu with JavaFX](https://www.azul.com/downloads/?version=java-18-sts&package=jdk-fx)
    - If you are using Swing, pretty much any Java 17 or 18 JDK will work.
- [Maven](https://maven.apache.org/).
- On MacOS XCode is required.
- On Windows the free [WiX Toolset](https://wixtoolset.org/) is required.

## Installation

If you are on Windows, you will need to install Java, Maven, and Wix manually.

If you are on MacOS or Linux, you can use [SDKMAN!](https://sdkman.io/) to simplify installing Java and Maven. Once 
SDKMAN! is installed, you can run the following to install Liberica or Azul Zulu and Maven.

```bash
sdk install java 18.0.2.fx-librca
# or
sdk install java 18.0.2.fx-zulu
sdk current java
sdk install maven
```

## Installation Verification

1. Verify that Java is installed by opening a fresh Terminal/Command Prompt and enter `java --version`. As of this 
writing, the Java version should be 18.0.2 or later.
2. Verify that Maven is installed with `mvn --version`. Maven should be version 3.8.6 or later.
3. Install platform-specific tools.
   1. **MacOS only:** Verify that XCode is installed & license accepted by a) launching it and b)
      running `sudo xcodebuild -license`.
   2. **Windows only:** Install [Wix 3 binaries](https://github.com/wixtoolset/wix3/releases/).
4. Clone/download this project.
5. Run `mvn clean install` from the root of the project to generate the `target\TestApp.dmg` or `target\TestApp.msi`
   installers.
    - The generated installer will include a version number in the file name.
    - For reference, here is a complete run log for [a successful run](docs/sample-run.md).

Because these builds use stripped down JVM images, the
[generated installers are in the 30-40mb range](https://github.com/wiverson/maven-jpackage-template/releases).

On MacOS you should [add signing to avoid error messages](https://github.com/wiverson/maven-jpackage-template/issues/49)
related to the security system(s).

To [re]generate an installer, run...

`mvn clean install`

To do everything up until the actual installer generation (including generating the custom JVM)...

`mvn clean package`

To generate reports, include to check if you are using the current version[s] of your dependencies, run...

`mvn site`

...and open target/site/index.html to see the generated reports.

## Key Features

Here are few cool things in this template:

- Only uses Java and Maven. No shell scripts required.
- Includes sample [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows) to
  build MacOS, Windows and Linux installers. These GitHub Actions are configured to use the Liberica JDK 18 with 
JavaFX to simplify the build process. If you prefer to use Azul Zulu, modify the distribution name to `distribution: 'zulu'` as described on the [Usage description of setup-java](https://github.com/actions/setup-java/blob/main/docs/advanced-usage.md#Zulu) 
- Demonstrates setting the application icon
- Builds a .dmg on MacOS, .msi on Windows, and .deb on Linux, but can be easily tweaked to generate other jpackage
supported installers (e.g. .pkg)
- Includes a JavaFX demo to simplify getting started.
    - Just delete the JavaFX stuff if you are using Swing
- Template includes several examples of JavaFX / native desktop integration
    - Drag & drop with Finder / Explorer
    - Change the Dock icon dynamically on MacOS
    - Menu on the top for MacOS, in the window itself on Windows
    - Request user attention (bouncing dock icon) on MacOS
    - Removing the code and the demonstration dependencies gets a "Hello World" build size closer to 30mb than
      40mb.
- Java + Java modules are used to build a trimmed
  JVM ([a few thoughts on Java modules](https://changenode.com/articles/fomo-java-modules))
- The user application uses ordinary Maven dependencies and classpath to run the application
    - Nice illustration of how to use jlink to build a slim JVM, point jpackage at that JVM and still use the ordinary
      Maven managed classpath for the application

Once you get started, you might find these lists of tutorials, tools, libraries for
[JavaFX](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2)
and general [Java desktop integration](https://gist.github.com/wiverson/e9dfd73ca9a9a222b2d0a3d68ae3f129) helpful.

### Version Numbering

Usually you want a "marketing version" of an app as released to customers, and a "developer version" for use in internal
testing. For example, to the end user it's just "Windows 11" but there are countless build numbers for all the
different versions of Windows 11.

The end-user value is set in the pom.xml as `app.version`. This value is updated to use a GitHub environment variable
when the installers are run on GitHub.

If you look in the `src/main/resources` you will see a version.txt file. This file has information in it that will
be useful for creating a developer build UI. You might want to convert this to a properties file or a JSON file and
display the information in your about UI.

Most projects will want to set up a coherent versioning strategy to manage both the user visible and development
build version numbers. This is usually project specific.

### Does this work with Apple Silicon aka M1/M2?

Yes, although as of this writing I don't believe there are GitHub Action runners that support M1. But building locally
on my M1/M2 systems works great and generates native Apple Silicon builds.

### Does this support macOS signing, notarization, and stapling?

Yes, there is a GitHub Action and a Maven profile to assist with setting all of this up
for macOS applications.

For more information, see
the [documentation on getting MacOS signing/notarization/stapling](/docs/apple-sign-notarize.md) set
up. 

To get this working, you will need to:

1. You need to sign up for an Apple Developer account.
2. Add [four GitHub Secrets based on information from Apple]((/docs/apple-sign-notarize.md)).
3. Update the [build all installer GitHub Action yaml](https://github.com/wiverson/maven-jpackage-template/blob/6d4ef8a80a562f2d49ec41204927d07aa8990d25/.github/workflows/maven-build-all-installer.yml#L14) 
4. Update the [pom.xml](https://github.com/wiverson/maven-jpackage-template/blob/6d4ef8a80a562f2d49ec41204927d07aa8990d25/pom.xml#L331).

### What about Linux?

The JavaFX builds include several other architectures, including aarch64 and arm32. In theory,
you should be able to add those just like the other builds. Haven't tested it though, as I only use Linux for
server-side stuff. Feel free to post in
the [discussion](https://github.com/wiverson/maven-jpackage-template/discussions) section and
also check the [Q&A](docs/qna.md) if you are using Linux.

### Can I Use this with Swing instead of JavaFX?

tl;dr absolutely.

Just delete the JavaFX stuff, including the JavaFX modules declarations in `pom.xml` and add a Swing main class instead.
If you are reasonably familiar with Maven this shouldn't be very hard to do.

I *highly* recommend the [FlatLaf](https://www.formdev.com/flatlaf/) as a must for working with Swing in 2022. That
look-and-feel plus designers such as
the [IntelliJ GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html)
or [JFormDesigner](https://www.formdev.com/jformdesigner/) can work very well, arguably with an easier learning curve
than JavaFX.

Suggested changes to the pom.xml for Swing:

1. Remove the javafx modules from the jvm.modules property
2. Remove the javafx.version property.
3. Remove the three org.openjfx dependencies
4. Remove the configuration/excludeGroupIds section from the maven-dependency-plugin
5. Remove javafx-maven-plugin from the plugins list
6. Remove the modulePath delcaration from the jtoolprovider-plugin execution/configuration

# Debugging

1. If the built app fails to run, make sure the JavaFX app runs as expected first by using the `mvn javafx:run` command.
   This will run the app in development mode locally, and you should see standard System.out debug lines appear in your
   console.
    - Many flavors of Linux fail to run here for a variety of reasons. Head over to
      the [discussions](https://github.com/wiverson/maven-jpackage-template/discussions) or perhaps consider your
      [consulting budget](https://changenode.com) or
      a [JavaFX support contract from Gluon](https://gluonhq.com/services/javafx-support/).
2. Check the Maven build logs (of course).
3. By default, the app will generate debug*****.log files containing the output from System.out. You can look at the
   main method of `BaseApplication.java` to see how this is done. For a production app, you would want to place these
   logs in the correct OS specific location. On a Unix machine you can `tail -f` the log normally.

# Help

Problems? Make sure everything is installed and working right!

- Compiler not recognizing the --release option? Probably on an old JDK.
- Can't find jdeps or jpackage? Probably on an old JDK.
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Could be a left-over MAVEN_OPTS setting when you switched from Java 15 to Java 16/17
    - If you are still on Java 15, you may not have
      [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).
- No certificate found matching [Developer ID Application: Company Name, Inc. (BXPXTXC35S)] using keychain [] -> Update the Developer ID info at the top of your build all installers and also in the macOS signing profile in the pom.xml.

- Getting errors about not being able to find JavaFX classes in your IDE? Make 
sure your IDE is pointing to the right JDK. For example, MacOS IntelliJ -> select 
File, Project Structure and make sure you have Liberica with JavaFX selected.

If you need consulting support, feel free to reach out at [ChangeNode.com](https://changenode.com/). I've helped several
companies with Swing and JavaFX clean up/modernize their old apps to include updated look & feels, add MacOS 
sign/staple/notarization, or even in a few cases helped port the app to Spring Boot.

# Q&A

If you are using the template, browsing the [Q&A](docs/qna.md) is highly recommended.
