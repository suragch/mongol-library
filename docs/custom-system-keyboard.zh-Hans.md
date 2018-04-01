# 如何创建自定义系统输入法

本教成以Andoid Studio 3.1和monogl-library 1.1.0测试的 （mongol-library不能低于1.1.0版）

## 1、 新建一个项目

命名为`Jianpan`

## 2、 导入mongol-library

在build.gradle(Module: app)文件里的dependencies中加蒙文控件库

```java
implementation 'net.studymongolian:mongol-library:1.1.0'
```

## 3、 创建自定义键盘试图类

你的键盘试图类要继承`net.studymongolian.mongollibrary.Keyboard`类。最简单的方法是复制已存在的键盘然后改成你想要的布局。参考下面的键盘试图：

- [KeyboardAeiou](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardAeiou.java)
- [KeyboardQwerty](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardQwerty.java)
- [KeyboardEnglish](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardEnglish.java)
- [KeyboardCyrillic](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardCyrillic.java)
- [CustomKeyboard](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/CustomKeyboard.java)
- [CustomKeyboardTwo](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/CustomKeyboardTwo.java)

命名为`WodeJianpan.java`

## 4、 创建键盘样式文件

在`res/layout`里新建xml文件，把它命名为`jianpan_yangshi.xml`。内容如下：

