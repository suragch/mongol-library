# Mongol Library
 
## Contents 
- Introduction
- Installing
- MongolTextView 
- MongolLabel
- MongolEditText
- Keyboards 
- MongolToast
- MongolAlertDialog
- Unicode 
- Fonts
- other
- TODO
- Developers
- Versions 
- External links
- Apps that use this library 
 
## Introduction
 
This Android library is a connection of UI components that support vertical Mongolian text. 
 
All of the native Android UI components only support horizontal text. In addition to this, Android support for Mongolian Unicode rendering is inadequate. These challenges at a big hurdle for new Mongolian app developers to overcome. Since most of the established Mongolian tech companies understandably do not share their source code. Developers must reinvent all the basic UI text components from scratch. This makes the Mongolian app development process very slow. 
 
The purpose of this library is to remove the obstacle of developing vertical text UI components Unicode rendering. Developers can simply import the mongol-library module and then just focus on the content of their app. 
 
Although this library is currently usable, there are still many improvements which need to be made. It is hoped that experienced developers will contribute with issue notifications and pull requests. Or fork your own version.
 
## Installing
 
You can import the library in your project from jCenter by adding the following line to your dependancies in your module's build.gradle file:
 
    dependencies {
        compile 'net.studymongolian:mongol-library:0.2.0'
    }
 
TODO: update the jCenter version

Documentation will be coming later but you can start with the following main componants:
 
## MongolTextView 

`MongolTextView` is a vertical TextView supporting Mongolian Unicode 9.0.

## MongolLabel

`MongolLabel is a light weight TextView for single line labels.
 
## MongolEditText

`MongolEditText` is a vertical EditText that accepts input from system keyboards, including Menksoft and Delehi.

Run the Demo App to see more of what is possible.
