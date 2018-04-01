# 如何创建自定义系统输入法

本教成以Andoid Studio 3.1和monogl-library 1.1.0测试的 （mongol-library不能低于1.1.0版）

## 1、 新的项目

新建一个项目,把它命名为`Jianpan`

## 2、 导入mongol-library

在build.gradle(Module: app)文件里的dependencies中加蒙文控件库

```java
implementation 'net.studymongolian:mongol-library:1.1.0'
```

## 3、 自定义键盘布局

创建一个java文件，这是你的键盘试图类，要继承`net.studymongolian.mongollibrary.Keyboard`类。最简单的方法是复制已存在的键盘然后改成你想要的布局。参考下面的键盘试图：

- [KeyboardAeiou](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardAeiou.java)
- [KeyboardQwerty](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardQwerty.java)
- [KeyboardEnglish](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardEnglish.java)
- [KeyboardCyrillic](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardCyrillic.java)
- [CustomKeyboard](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/CustomKeyboard.java)
- [CustomKeyboardTwo](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/CustomKeyboardTwo.java)

命名为`WodeJianpan.java`

## 4、 键盘样式

在`res/layout`里新建xml文件，把它命名为`jianpan_yangshi.xml`。内容如下：

```xml
<?xml version="1.0" encoding="utf-8"?>
<net.studymongolian.mongollibrary.ImeContainer xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="#dbdbdb">

    <com.example.jianpan.WodeJianpan
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:keyBorderColor="#000000"
        app:keyBorderRadius="3dp"
        app:keyBorderWidth="1px"
        app:keyColor="#ffffff"
        app:keyImageTheme="light"
        app:keyPressedColor="#b3b3b3"
        app:keySpacing="3dp"
        app:popupHighlightColor="#dbdbdb"
        app:popupTextColor="#fe9a52"
        app:primaryTextColor="000000"
        app:primaryTextSize="30sp"
        app:secondaryTextColor="b3b3b3" />

</net.studymongolian.mongollibrary.ImeContainer>
```

**备注**

- 把`com.example.Jianpan.WodeJianpan`改成你的项目和类名。
- 如果你想切换键盘，可以多加几个键盘视图类。

## 5、 InputMethodService类

创建一个java文件，把它命名为`WodeInputMethodService.java`，要继承`InputMethodService`，还要实现`ImeContainer.OnSystemImeListener`。内容如下：

```java
public class WodeInputMethodService extends InputMethodService implements ImeContainer.OnSystemImeListener {

    @Override
    public View onCreateInputView() {
        LayoutInflater inflater = getLayoutInflater();
        ImeContainer jianpan = (ImeContainer) inflater.inflate(R.layout.jianpan_yangshi, null, false);
        jianpan.showSystemKeyboardsOption("ᠰᠢᠰᠲ᠋ᠧᠮ");
        jianpan.setOnSystemImeListener(this);
        return jianpan;
    }

    @Override
    public InputConnection getInputConnection() {
        return getCurrentInputConnection();
    }

    @Override
    public void onChooseNewSystemKeyboard() {
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (im == null) return;
        im.showInputMethodPicker();
    }
}
```

**备注**

- 也可以实现Keyboard.On...

