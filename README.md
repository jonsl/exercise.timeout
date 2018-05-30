# exercise.timeout
## Problem statement
The Time Out team loves to socialise, some of us are also really fussy! In order to spend less time deciding where to go we’d like a program that decides for us. All members of the team will want both food and drink so if a member of staff cannot eat anything, or no drinks are served that they like, the team will not visit the venue.
## Prerequisites
For this project I have used the following during implementation:
### IDE
```
Intellij IDEA 2018.1.4 COMMUNITY  
```
### Operating Systems
```
This project was written in Linux:

Distributor ID:	Ubuntu
Description:	Ubuntu 18.04 LTS
Release:	18.04
Codename:	bionic
```
```
Also runs on windows:

Edition: Windows 10 Home
Version: 1803
OS build: 17134.81
System type: 64-bit operating system, x64-based processor
```
### Java version
```
java version "1.8.0_172"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)
```
### Open Source Libraries
```
compile group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.5'
testCompile group: 'junit', name: 'junit', version: '4.4'
```
## Command-line
Following are instructions how to build and run the server.
### Common
Download Java JDK 1.8.0_172: 
```
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```
### ubuntu
```
$ sudo apt install gradle
$ git clone https://github.com/jonsl/exercise.form3.git
$ cd exercise.form3/
$ ./gradlew build
$ ./gradlew run
```
### windows
```
> git clone https://github.com/jonsl/exercise.form3.git
> cd exercise.form3/
> gradlew build
> gradlew run
```
