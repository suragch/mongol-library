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

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="20dp">
    
    <net.studymongolian.mongollibrary.MongolTextView
        android:id="@+id/mongol_text_view_id"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        app:text="ᠰᠠᠢᠨ ᠪᠠᠢᠨ᠎ᠠ ᠤᠤ︖"
        app:textSize="24sp"
        app:textColor="@android:color/black"/>
    
</LinearLayout>
```

Java

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MongolTextView mongolTextView = findViewById(R.id.mongol_text_view_id);
        mongolTextView.setText("ᠮᠣᠩᠭᠣᠯ");
        mongolTextView.setTextColor(Color.BLUE);
    }
}
```

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