# macOS Application Signing, Notarization, and Stapling

So, you just want to build and release a macOS application. You have managed
to use this template with JavaFX or Swing to produce a DMG with a nice application,
but when you download the application and try to run it you get an error
from macOS saying that the application is damaged and unrunnable. What is going on?

## Signing

When you download a disk image or app on macOS, it checks to see if the app
has been digitally signed. If not, macOS adds a quarantine flag to the dmg (and the 
bundled app). Then, when you go to run the application macOS notices the quarantine
flag and then refuses to run. You can't work around this quarantine flag with a 
right-click and open. As far as I know there is no GUI for working around a quarantine
flag - you have to use the Terminal.

You can verify if the quarantine flag is set with this command:

 xattr MyFancyApp.app

You can remove the quarantine flag with this command:

 sudo xattr -r -d com.apple.quarantine MyFancyApp.app
 
Needless to say, that's not acceptable for end users.

To sign the application, you need to run a suite of command-line tools that does
the Right Thing(tm) for signing all of the application assets, including the various
bundled libraries. Fortunately, jpackage will do this for us if we pass along the 
right arguments and have our keychain set up properly for command-line use.

With a signed application, you can download the file, but macOS will initially
still flag the application as sketchy and will initially tell users that the
application can't be opened. You can work around this by right-clicking on the
MyFancyApp.app and selecting open - this will bring up the dialog with the option
to Open the file.

## Notarization and Stapling

In order to allow the application to actually run nicely for an end user - as in,
the first time the user runs the app macOS will still point out that it was downloaded
from the Internet, but offer an Open button without having to right-click on the app,
you have to notarize the app.

Notarization is simply sending the entire dmg to Apple to analyze (run a virus checker?)
and then getting back a digital certificate.

It appears that if you want you can notarize the dmg, and then if the user goes to
run the app macOS will then go online to verify the signature of the dmg against
a database of notarized apps. The only problem with is that it requires macOS to hit
the online database, which is a bummer for a variety of reasons.

Instead, if we have gone so far as to notarize the app, we should go ahead and staple
(just another word for attach) the notarization certificate directly to the dmg.
As long as the local copy of macOS doesn't have the notarization on a block-list,
now even an offline user can go ahead and install the application.

## How Does This Template Deal With This?

This template includes a complete working example of how to get all of this
stuff working via a GitHub Action.

You will need to set several GitHub Secrets for the project, based on the
Apple ID and Developer information you have set up with Apple.

You can find the GitHub Action for building a [signed, notarized macOS application](https://github.com/wiverson/maven-jpackage-template/blob/main/.github/workflows/maven-build-installer-macos.yml).
That action includes documentation on several secrets you will need to set, based
on values from Apple.

You can find more information on how to how to find and set these variables
[in this article](https://localazy.com/blog/how-to-automatically-sign-macos-apps-using-github-actions)
summarizing the steps.

In addition to setting the values in the GitHub Action, you will also need
to set a `<macos.sign.identity>` value in your pom.xml file.

## A Brief Note on GitHub Actions

If you are using GitHub Actions on a private repository you have to (at least eventually)
pay for minutes. As of this writing the minutes are x1 for Linux builds, x2 for Windows,
and x10 for macOS. Apple can easily take 5-10 minutes to notarize a build, which means
that if it uses 10 minutes that's x10 = 100 minutes of GitHub Action build time for purposes
of billing.

## Ugh... Help Please

If you need help setting this up, [feel free to reach out](https://changenode.com/contact)
for consulting support, or you can post in the [discussion forum](https://github.com/wiverson/maven-jpackage-template/discussions) for this template.
