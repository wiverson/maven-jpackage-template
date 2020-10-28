#!/bin/bash -o xtrace

JAVA_FX_LIBS=javafx-sdk-15.0.1/lib
JAVA_FX_MODS=javafx-jmods-15.0.1

ROOT_DIR=$PWD

SHADE_TEST_JAR=target/shade-test.jar
SHADE=shade.test

KNOWN_MODULES=javafx.base,javafx.controls,javafx.graphics,java.logging
# KNOWN_MODULES=javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,java.logging

APP_CLASS=com.doublerobot.HelloWorld
APP_NAME=TestApp

JARPATH=$SHADE_TEST_JAR
MOD=$SHADE

JLINK_OPTIONS="--strip-native-commands --strip-debug --no-header-files --no-man-pages --compress=2"

rm -rf target/work
rm -rf target/modules 
mkdir target/work
mkdir target/modules 

echo "Running jdeps to see what the shaded JAR needs to run..."
jdeps  --module-path "$ROOT_DIR/$JAVA_FX_LIBS" --add-modules $KNOWN_MODULES --generate-module-info target/work $SHADE_TEST_JAR
mkdir -p target/site
cp target/work/$SHADE/module-info.java target/site/module-info.java

echo "Copy original jar into place"
cp $ROOT_DIR/$JARPATH $ROOT_DIR/target/modules/$MOD.jar

echo "Unpacking the JAR into clean working director"
rm -rf classes
mkdir classes
cd classes
jar xf $ROOT_DIR/$JARPATH

echo "Starting javac to compile module-info"
cd $ROOT_DIR/target/work/$MOD
javac -p $MOD,$KNOWN_MODULES --module-path $ROOT_DIR/$JAVA_FX_MODS -d $ROOT_DIR/classes module-info.java

echo "Pack the jar with the new module-info"
jar uf $ROOT_DIR/target/modules/$MOD.jar -C $ROOT_DIR/classes module-info.class

cd $ROOT_DIR

echo "Remove the temporary directory used for the unpacked jar"
rm -rf target/work classes 

echo "Place modular jars in $PWD/module"
cd target/modules
rm -rf target/build

# echo "starting jlink"
# jlink --strip-native-commands --strip-debug --no-header-files --no-man-pages --compress=2 --module-path $JAVA_HOME/jmods/:/Users/wiverson/src/shade-test/javafx-jmods-15.0.1/:$PWD --add-modules shade.test,javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,java.logging --launcher runner=shade.test/com.doublerobot.HelloWorld --output ../TestApp/build

echo "Start jpackage to build native installer"
cd ..
jpackage --jlink-options "$JLINK_OPTIONS" --name $APP_NAME --icon ../app-icon.icns --module-path $JAVA_HOME/jmods/:$PWD/../$JAVA_FX_MODS/:$PWD/modules/ --add-modules $SHADE,$KNOWN_MODULES  -m $SHADE/$APP_CLASS

echo "Ready."
