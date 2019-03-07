# 如何创建自定义系统输入法

本教成以Andoid Studio 3.1和monogl-library 1.1.0测试的 （mongol-library不能低于1.1.0版）

## 1、 新的项目

新建一个项目,把它命名为`Jianpan`

## 2、 导入mongol-library

在build.gradle(Module: app)文件里的dependencies中加蒙文控件库

```java
implementation 'net.studymongolian:mongol-library:1.3.1'
```

## 3、 自定义键盘布局

创建一个java文件，这是你的键盘试图类，要继承`net.studymongolian.mongollibrary.Keyboard`类。最简单的方法是复制已存在的键盘然后改成你想要的布局。参考下面的键盘试图：

- [KeyboardAeiou](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardAeiou.java)
- [KeyboardQwerty](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardQwerty.java)
- [KeyboardLatin](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/KeyboardLatin.java)
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
        app:keyPressedColor="#b3b3b3"
        app:keySpacing="3dp"
        app:popupHighlightColor="#dbdbdb"
        app:popupTextColor="#fe9a52"
        app:primaryTextColor="#000000"
        app:primaryTextSize="30sp"
        app:secondaryTextColor="#b3b3b3" />

</net.studymongolian.mongollibrary.ImeContainer>
```

**备注**

- 把`com.example.Jianpan.WodeJianpan`改成你的项目和类名。
- 如果你想切换键盘，可以多加几个键盘视图类。

## 5、 InputMethodService类

创建一个java文件，把它命名为`WodeInputMethodService.java`，要继承`InputMethodService`，还要实现`ImeContainer.OnSystemImeListener`接口。内容如下：

```java
public class WodeInputMethodService extends InputMethodService implements ImeContainer.OnSystemImeListener {

    @Override
    public View onCreateInputView() {
        LayoutInflater inflater = getLayoutInflater();
        ImeContainer jianpan = (ImeContainer) inflater.inflate(R.layout.jianpan_yangshi, null, false);
        jianpan.showSystemKeyboardsOption("ᠰᠢᠰᠲ᠋ᠧᠮ"); // 长按键盘键可以切换到别的系统输入法
        jianpan.setOnSystemImeListener(this);
        return jianpan;
    }

    // ImeContainer.OnSystemImeListener的方法

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

- 如果你不想用`ImeContainer`或者你想自己控制键盘的输入法，从`onCreateInputView()`也可以返回一个`Keyboard`视图，这样的话要你的`InputMethodService`要实现`Keyboard.OnKeyboardListener`接口，就是要实现的方法较多一点。

## 6、 输入法子类型

在`res/xml/`里创建一个xml文件，把它命名为`method.xml`。内容如下：

```java
<?xml version="1.0" encoding="utf-8"?>
<input-method
    xmlns:android="http://schemas.android.com/apk/res/android">

    <subtype
        android:imeSubtypeMode="keyboard"/>

</input-method>
```

# 7、 申明输入法

在`AndroidManifest.xml`里要申明你的输入法：

```java
</application>
    ...
    
    <service
        android:name=".WodeInputMethodService"
        android:label="自定义输入法"
        android:permission="android.permission.BIND_INPUT_METHOD">
        <intent-filter>
            <action android:name="android.view.InputMethod"/>
        </intent-filter>
        <meta-data
            android:name="android.view.im"
            android:resource="@xml/method"/>
    </service>
</application>
```

你的键盘的基本功能已经完成了，但是`MainActivity`里可以加一些设置项目或帮助。

# 8、 激活输入法

在系统设置里用户都要激活你的输入法。
