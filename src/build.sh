#!/bin/sh

javac -d ../dest -cp ../dest/lib/*: com/csw/sample/contentconverter/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/output/xml/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/output/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/input/json/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/factory/*.java
javac -d ../dest -cp ../dest/*:../dest/lib/*: com/csw/sample/contentconverter/constants/*.java

cd ..
cd dest
jar cfmv JSONToXMLConverter.jar META-INF/MANIFEST.MF com lib
