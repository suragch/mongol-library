*中文不是我的母语，求读者的纠正*

# mongol-library

蒙古文的Android控件库

## 目录

- 简介
- 使用指南
- 组件
    - `MongolTextView` 蒙文文本框
    - `MongolEditText` 蒙文编辑文本框
    - `ImeContainer` 键盘包
    - `MongolAlertDialog` 蒙文对话框
    - `MongolToast` 蒙文Toast
- `MongolCode` 蒙文国际编码处理器
- 如何参与

## 简介

此Android库让开发者在应用里轻松地使用竖写的蒙古文。组件包括文本框、编辑文本框、对话框等常用的文本控件。另外，有蒙文键盘和输入法，对键盘布局不满意的开发者还可以自定键盘布局。所有的控件都支持国际编码和蒙科立编码。

## 使用指南

要求：

- Android Studio 3.0以上 （因为需要gradle和jcenter）
- 最低sdk版本14以上

导入方式

在你的app模块的build.gradle文件里dependencies依赖项区加`mongol-library`的链接；

```java
dependencies {
    implementation 'net.studymongolian:mongol-library:1.0.0'
}
```

## `MongolTextView`

蒙文文本框

![MongolTextView example](docs/images/mtv-example.png)

XML
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MongolTextView mongolTextView = findViewById(R.id.mongol_text_view_id);
        mongolTextView.setText("ᠮᠣᠩᠭᠣᠯ");
        mongolTextView.setTextColor(Color.BLUE);
    }
}

#### 备注

- 词库只包含蒙科立的[白体字库](http://www.menksoft.com/mdls/am/amview.aspx?pid=0&alias=menkcms&iid=168137&mid=15302&wv=U)。开发者可以自己添加更多字体：

```java
    String AMGLANG = "fonts/MAM8102.ttf";
    Typeface customFont = MongolFont.get(AMGLANG, getApplicationContext());
    mongolTextView.setTypeface(customFont);
```

- `MongolTextView`支持表情、中文、日文、韩文等文字的正确方向。

![MongolTextView (Demo App)](docs/images/mtv-demo.png)

- 不需要任何文字转向或span格式的情况下，也可以用`MongolLabel`来代替。

## `MongolEditText` 

蒙文编辑文本框

![MongolEditText](docs/images/met-example.png)

`MongolEditText`支持系统键盘的输入，就像上面的图片所示。（蒙科立公司和德力海公司的第三方输入法）

XML

```xml
<HorizontalScrollView
    android:id="@+id/hsvEditTextContainer"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:fillViewport="true">

    <net.studymongolian.mongollibrary.MongolEditText
        android:id="@+id/metExample"
        android:layout_width="wrap_content"
        android:layout_height="200dp"
        android:padding="10dp"
        android:background="@android:color/white"
        app:text=""
        app:textSize="30sp"/>

</HorizontalScrollView>
```

`MongolEditText`目前还不支持滚动，所以应该把它放在`HorizontalScrollView`里。

Java

```java
MongolEditText mongolEditText = findViewById(R.id.metExample);
String text = mongolEditText.getText().toString();
```

## `ImeContainer`

键盘包

![Keyboards](docs/images/keyboard-four.png)

`ImeContainer`的工作是管理键盘。此库有四种默认键盘布局，不是系统的，是可以嵌入在你的应用里。这样的话，如果用户没有安装第三方的蒙文输入法还能用你给提供的键盘来输入蒙文。你还可以定制别的键盘布局，只需要复制一个键盘的源码然后改一下，用法都一样。

XML

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <HorizontalScrollView
        android:id="@+id/hsvEditTextContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:layout_above="@id/ime_container"
        android:layout_margin="16dp"
        android:fillViewport="true">

        <net.studymongolian.mongollibrary.MongolEditText
            android:id="@+id/mongoledittext"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:background="@android:color/white"
            android:padding="10dp"
            app:textSize="30sp"
            tools:layout_editor_absoluteX="20dp"
            tools:layout_editor_absoluteY="128dp" />

    </HorizontalScrollView>

    <net.studymongolian.mongollibrary.ImeContainer
        android:id="@+id/ime_container"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:background="#dbdbdb"
        android:layout_alignParentBottom="true"
        android:layout_alignParentStart="true"
        android:layout_alignParentEnd="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true">

        <net.studymongolian.mongollibrary.KeyboardQwerty
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            style="@style/KeyboardTheme"
            />

        <net.studymongolian.mongollibrary.KeyboardAeiou
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:primaryTextSize="30sp"
            style="@style/KeyboardTheme"
            />

    </net.studymongolian.mongollibrary.ImeContainer>

</RelativeLayout>
```

颜色、大小等各种方面都能调整：

```xml
<style name="KeyboardTheme" parent="AppTheme">
    <item name="keyColor">#ffffff</item>
    <item name="keyPressedColor">#b3b3b3</item>
    <item name="primaryTextColor">#000000</item>
    <item name="secondaryTextColor">#b3b3b3</item>
    <item name="keyImageTheme">light</item>
    <item name="keySpacing">3dp</item>
    <item name="keyBorderWidth">1px</item>
    <item name="keyBorderColor">#000000</item>
    <item name="keyBorderRadius">3dp</item>
    <item name="popupTextColor">#fe9a52</item>
    <item name="popupHighlightColor">#dbdbdb</item>
</style>
```

Java

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        ImeContainer imeContainer = findViewById(R.id.ime_container);
        EditText editText = findViewById(R.id.edittext);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // MongolInputMethodManager管理ImeContainer（键盘）和MongolEditText（或EditText）的交流
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
        // 不让系统键盘出来
        mimm.setAllowSystemSoftInput(MongolInputMethodManager.NO_EDITORS);
    }
}
```

#### 候选试图

`ImeContainer`也有候选试图，如果你有单词库的话用户在输入的时候你可以提供候选词。

![keyboard candidates](docs/images/keyboard-candidates.png)

XML

```xml
<net.studymongolian.mongollibrary.KeyboardQwerty
    ...
    app:candidatesLocation="horizontal_top"
    ...
    />
    
<net.studymongolian.mongollibrary.KeyboardAeiou
    ...
    app:candidatesLocation="vertical_left"
    ...
    />
```

Java

```java
// 要声明ImeContainer.DataSource接口
public class MyActivity extends AppCompatActivity implements ImeContainer.DataSource {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
       
        imeContainer.setDataSource(this);

        // ...
    }

    // 实现ImeContainer.DataSource接口的方法

    @Override
    public List<String> onRequestWordsStartingWith(String text) {
        // 候选试图要求以text开头的词
    }

    @Override
    public List<String> onRequestWordsFollowing(String word) {
        // 候选词被选上后候选试图要求接下来的词
    }

    @Override
    public void onCandidateLongClick(int position, String text) {
        // 候选词被长按
    }
}
```

