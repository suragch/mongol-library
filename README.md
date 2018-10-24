# Mongol Library

[[中文]](https://github.com/suragch/mongol-library/blob/master/README.zh-Hans.md)

Android UI components for vertical Mongolian text 

## Table of Contents 

* [Introduction](https://github.com/suragch/mongol-library#introduction)
* [Installing](https://github.com/suragch/mongol-library#installing)
* [UI Components](https://github.com/suragch/mongol-library#ui-components)
    * [MongolTextView](https://github.com/suragch/mongol-library#mongoltextview) 
    * [MongolLabel](https://github.com/suragch/mongol-library#mongollabel)
    * [MongolEditText](https://github.com/suragch/mongol-library#mongoledittext)
    * [Keyboards](https://github.com/suragch/mongol-library#keyboards)
    * [MongolMenu](https://github.com/suragch/mongol-library#mongolmenu)
    * [MongolToast](https://github.com/suragch/mongol-library#mongoltoast)
    * [MongolAlertDialog](https://github.com/suragch/mongol-library#mongolalertdialog)
    * [Horizontal RecyclerView](https://github.com/suragch/mongol-library#horizontal-recyclerview)
    * [Deprecated views](https://github.com/suragch/mongol-library#deprecated-views)
* [Unicode](https://github.com/suragch/mongol-library#unicode)
* [Fonts](https://github.com/suragch/mongol-library#fonts)
* [How to contribute](https://github.com/suragch/mongol-library#how-to-contribute)
    * [TODO](https://github.com/suragch/mongol-library#todo)
    * [Version changes](https://github.com/suragch/mongol-library#version-changes)
* [External links](https://github.com/suragch/mongol-library#external-links)
* [Apps that use this library](https://github.com/suragch/mongol-library#apps-that-use-this-library)
 
## Introduction

This Android library is a collection of UI components that support vertical Mongolian text. 

All of the native Android UI components only support horizontal text. In addition to this, Android support for Mongolian Unicode rendering is inadequate. These challenges are a big hurdle for new Mongolian app developers to overcome. This makes the Mongolian app development process very slow. 

The purpose of this library is to make it easy to include vertical Mongolian text in your app. You only need to import the `mongol-library` module. 
 
## Installing

This library is a part of the jCenter repository, which is the default in Android Studio.

You can import `mongol-library` into your project from jCenter by adding the following line to your dependencies in your app module's `build.gradle` file:

```java
dependencies {
    implementation 'net.studymongolian:mongol-library:1.16.3'
}
```

If you are still using Android Studio 2.x, you should use `compile` rather than `implementation`.

#### Notes

- **The minimum SDK version for this library is 14.** So if you are supporting Android versions below API 14 (Android 4.0 Icecream Sandwich), then you won't be able to use this library.
- Upgrades in the form of `1.x` likely introduce breaking changes, so don't use `1.x+` notation.

## UI Components

The following are the primary UI components in the library. See also the [Demo App](https://github.com/suragch/mongol-library/tree/master/demo-app/release) for an example of how they are used. 

### MongolTextView 

The `MongolTextView` is a vertical text replacement for the standard Android `TextView`. It measures and lays out text from top to bottom and vertical lines are laid out from left to right. No mirroring is done internally so mirrored fonts are not required (if you want to add additional fonts). As much as possible the [API](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/MongolTextView.java) seeks to follow the standard `TextView` [API](https://developer.android.com/reference/android/widget/TextView.html).

#### Basic usage

You can create a `MongolTextView` exclusively in XML or in code.

![MongolTextView example](docs/images/mtv-example.png)

XML example

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

If you are defining a style in xml, then don't use the `android` or `app` namespace for any of the custom attributes:

```xml
<style name="AppTheme.MongolTextStyle">
    <item name="android:layout_width">wrap_content</item>
    <item name="android:layout_height">wrap_content</item>
    <item name="android:layout_centerInParent">true</item>
    <item name="textColor">@color/my_text_color</item>
    <item name="textSize">24sp</item>
</style>
```

Code example

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

#### Features

Other features of MongolTextView include the following:

* Text color
* Text size
* Font
* Alignment
* Padding
* Spans
    * Background color
    * Foreground color
    * Relative size
    * Typeface
    * Style
    * Subscript
    * Underline
    * Clickable
    * (and any other span that implements `CharacterStyle` should also work)
* (TODO line spacing)
* Emoji support
* CJK orientation support
* Unicode support

These can be further explored with the [Demo App](demo-app).

![MongolTextView (Demo App)](docs/images/mtv-demo.png)


### MongolLabel 

`MongolLabel` is a light weight view similar to `MongolTextView`. It is less expensive because it does not have to calculate multi-line, emoji rotation, or spans. If you need a large number of MongolTextViews and are experiencing performance problems, then this may be a solution.

Supports:

* Unicode 
* text color
* text size
* fonts (typeface)
* centered in view
* auto text resizing if font too big for view size

Does not support:

* multi-line text
* CJK/emoji rotation 
* text spans

Here is an image of the [Demo App](demo-app):

![MongolLabel (Demo App)](docs/images/ml-demo.png)


### MongolEditText 

The `MongolEditText` is a vertical text replacement for the standard Android `EditText`. As much as possible the [API](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/MongolEditText.java) seeks to follow the standard `EditText` [API](https://developer.android.com/reference/android/widget/EditText.html). It subclasses `MongolTextView`. In addition to allowing cursor location and text selection, it also adds the API elements needed to communicate with both custom in-app keyboards and system keyboards. 

Long clicking the `MongolEditText` will display a default `MongolMenu` with editing options. This menu can also be replaced with a custom menu if you implement `setContextMenuCallbackListener()`. 

#### Basic usage

The following image shows `MongolEditText` receiving text input from the Menksoft and Delehi system keyboards.

![MongolLabel (Demo App)](docs/images/met-example.png)

###### XML example

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:padding="20dp">

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

</RelativeLayout>
```

Note that since `MongolEditText` does not support scrolling itself yet, it is good to wrap it in a `HorizontalScrollView`. If you need only a single line then you can wrap it in a `ScrollView`. A future TODO may be to add a `singleLine` or `maxLines` attribute.

###### Code example

```java
MongolEditText mongolEditText = findViewById(R.id.metExample);
String text = mongolEditText.getText().toString();
```

#### Features

* Accepts input from system keyboards and mongol-library keyboards. 
* text selection 
* highlight words on double click
* change selection on drag
* conforms to the Editable protocol 
* Unicode indexing (handles glyph indexing internally) 
* also supports Menksoft code

### Keyboards

It cannot be assumed that all users will have a Mongol IME (like the Menksoft or Delehi keyboards) installed on their phone, so if you need Mongolian input in your app, you should probably include an in-app keyboard. 

This library includes four keyboard layouts: two traditional Mongolian ones (AEIOU and QWERTY) and Cyrillic and Latin keyboards. Punctuation is shown by clicking the keyboard button. Keyboard layouts can be switched by long pressing the keyboard button.

##### AEIOU keyboard

![AEIOU keyboard](docs/images/keyboard-aeiou.png)

The philosophy behind the AEIOU keyboard is to make input as easy as possible. The general arrangement follows the order of the Mongolian alphabet. The buttons are large by making infrequently used letters only available as longpress popups. The Unicode distinctions between O/U, OE/UE, and T/D are hidden from the user. It has been reported that countryside Mongols who have less interaction with computer keyboards prefer this layout. Users who want more controll over the Unicode input characters can use the QWERTY keyboard layout.

##### QWERTY keyboard

![QWERTY keyboard](docs/images/keyboard-qwerty.png)

This keyboard copies the layout of a computer keyboard (with the addition of Mongolian Unicode ANG). Users can differentiate O/U, OE/UE, and T/D.

##### Latin keyboard

![Latin keyboard](docs/images/keyboard-english.png)


##### Cyrillic keyboard

![Cyrillic keyboard](docs/images/keyboard-cyrillic.png)

##### Custom keyboard

Is is possible to use your own custom keyboard layout. You just need to extend `Keyboard` and implement the appropriate methods. Start with a copy of the source code for one of the library keyboards and modify it to suite your needs. An example of a custom keyboard is [included in the Demo App](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/KeyboardActivity.java).

#### Basic usage

The keyboards are added to an `ImeContainer` to allow for keyboard switching and candidate word suggestions. This can be done programmatically or in XML. However, if you want to style your keyboard, you must do it in XML.

XML layout

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
You could style each keyboard separately, but in the above example a single style is used.

```xml
<style name="KeyboardTheme" parent="AppTheme">
    <item name="keyColor">#ffffff</item>
    <item name="keyPressedColor">#b3b3b3</item>
    <item name="primaryTextColor">#000000</item>
    <item name="secondaryTextColor">#b3b3b3</item>
    <item name="keySpacing">3dp</item>
    <item name="keyBorderWidth">1px</item>
    <item name="keyBorderColor">#000000</item>
    <item name="keyBorderRadius">3dp</item>
    <item name="popupTextColor">#fe9a52</item>
    <item name="popupHighlightColor">#dbdbdb</item>
</style>
```

Code

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);

        ImeContainer imeContainer = findViewById(R.id.ime_container);
        MongolEditText mongolEditText = findViewById(R.id.mongoledittext);

        // The MongolInputMethodManager handles communication between the 
        // ImeContainer (keyboards) and the MongolEditText (or EditText).
        MongolInputMethodManager mimm = new MongolInputMethodManager();
        mimm.addEditor(mongolEditText);
        mimm.setIme(imeContainer);
    }
}
```
#### Support keyboard candidates

You can provide word suggestions in a candidates view while the user is typing. You will need to suply your own word database, though.

![keyboard candidates](docs/images/keyboard-candidates.png)

To do this you need to set `KeyboardCandidateView` location in XML for each keyboard that is using it.

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

The candidates view uses a RecyclerView internally, so you should include it in your app's build.gradle dependencies.

```xml
dependencies {
    implementation 'com.android.support:recyclerview-v7:27.1.1'
}
```

The system keyboard will popup natually so you can prevent that by hiding in the Manifest for the activity that is using the keyboard.

```xml
android:windowSoftInputMode="stateHidden"
```

Then implement `ImeContainer.DataSource` in your activity to provide the requested word suggestion list. Implement  `ImeContainer.OnNonSystemImeListener` to hide the keyboard when requested from the navigation view.

```java
public class MyActivity extends AppCompatActivity implements ImeContainer.DataSource, ImeContainer.OnNonSystemImeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        
        // provide words for candidate selection
        imeContainer.setDataSource(this);
        // listen for when keyboard requests to be hidden
        imeContainer.setOnNonSystemImeListener(this);

        // ...
    }

    // ImeContainer.DataSource methods

    @Override
    public void onRequestWordsStartingWith(String text) {
        // query database for words starting with `text`
        // call `imeContainer.setCandidates(wordList)` after the results come back
    }

    @Override
    public void onWordFinished(String word, String previousWord) {
        // save/update `word` 
        // optionally update `previousWord`'s following 
    }

    @Override
    public void onCandidateClick(int position, String word, String previousWordInEditor) {
        // query database for words that can follow `word`
        // call `imeContainer.setCandidates(wordList)` after the results come back
        // optionally update database word frequency and following
    }

    @Override
    public void onCandidateLongClick(int position, String word, String previousWordInEditor) {
        // user long clicked a candidate item
    }
    
    @Override
    public void onHideKeyboardRequest() {
        // set the ImeContainer visibility to View.GONE 
    }
}
```

This is what the navigation view looks like:

![keyboard navigation](docs/images/keyboard-navigation.png)

### MongolMenu

`MongolMenu` is a replacement for context menus and toolbar menus.

#### Basic usage

```java
MongolMenu menu = new MongolMenu(this);
menu.add(new MongolMenuItem("ᠨᠢᠭᠡ", R.drawable.ic_sun));
menu.add(new MongolMenuItem("ᠬᠤᠶᠠᠷ", R.drawable.ic_moon));
menu.add(new MongolMenuItem("ᠭᠤᠷᠪᠠ", R.drawable.ic_star));
menu.setOnMenuItemClickListener(new MongolMenu.OnMenuItemClickListener() {
    public boolean onMenuItemClick(MongolMenuItem item) {
        MongolToast.makeText(MongolMenuActivity.this, item.getTitle(), MongolToast.LENGTH_SHORT).show();
        return true;
    }
});
menu.showAsDropDown(view, 0, 0);
```

Instead of `showAsDropDown` (which anchors it to the view), you can also use `showAtLocation` to place it anywhere on the screen. Here is an example that places it on the toolbar.

![MongolMenu example](docs/images/mm-example.png)

### MongolToast 

`MongolToast` is a vertical version of Android `Toast`.

#### Basic usage

```java
MongolToast.makeText(getApplicationContext(), "ᠰᠠᠢᠨ ᠪᠠᠢᠨ᠎ᠠ ᠤᠤ︖", MongolToast.LENGTH_LONG).show();
```

This produces the following result:

![MongolToast example](docs/images/mt-example.png)

### MongolAlertDialog

`MongolAlertDialog` is a vertical version of Android `AlertDialog`. It currently only supports a title, message, and up to 3 buttons. 

#### Basic usage

```java
// setup the alert builder
MongolAlertDialog.Builder builder = new MongolAlertDialog.Builder(this);
builder.setMessage("ᠵᠠᠮᠤᠭ ᠰᠠᠢᠲᠠᠢ ᠨᠠᠭᠤᠷ ᠲᠤ ᠵᠢᠭᠠᠰᠤ ᠤᠯᠠᠨ᠂\nᠵᠠᠩ ᠰᠠᠢᠲᠠᠢ ᠬᠦᠮᠦᠨ ᠳᠦ ᠨᠦᠬᠦᠷ ᠤᠯᠠᠨ᠃");

// add the button
builder.setPositiveButton("ᠮᠡᠳᠡᠯ᠎ᠡ", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        // do sth
    }
});

// create and show the alert dialog
 MongolAlertDialog dialog = builder.create();
 dialog.show();
 ```

This produces the following result:

![MongolToast example](docs/images/mad-example.png)

### Horizontal RecyclerView

No special UI componants are needed from the library to create a horizontal `RecyclerView`. However, it is a common need for Mongolian apps, so an example is included here.

![Horizontal RecyclerView example](docs/images/horizontal-recyclerview-example.png)

The only thing you really need to do differently from a normal `RecyclerView` list is to use `LinearLayoutManager.HORIZONTAL` for the orientation. Check out [this Stack Overflow answer](https://stackoverflow.com/a/45953855/3681880) for more detailed instructions. 

Also see the example in the Demo App. Here is the relevant code:

* [HorizontalRecyclerViewActivity.java](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/HorizontalRecyclerViewActivity.java)
* [HorizontalRecyclerViewAdapter.java](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/java/net/studymongolian/mongollibrarydemo/HorizontalRecyclerViewAdapter.java)
* [activity_horizontal_recyclerview.xml](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/res/layout/activity_horizontal_recyclerview.xml)
* [horizontal_recyclerview_item.xml](https://github.com/suragch/mongol-library/blob/master/demo-app/src/main/res/layout/horizontal_recyclerview_item.xml)

### Deprecated views

In the past I displayed Mongolian by rotating and mirroring the standard Android views (mainly `TextView`). There are a number of disadvantages with this method, which is why I no longer do it. However, I am including the following two views (but omitting `RotatedEditText`) in the library. This is partly for historical/reference purposes and partly because they could be used if `MongolTextView` is lacking some fuctionality that you need. 

* [RotatedTextView](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/RotatedTextView.java)
* [RotatedViewGroup](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/main/java/net/studymongolian/mongollibrary/RotatedViewGroup.java)

These views are deprecated. In the future they may be dropped from the library. If you plan to use them long term, it is recommended that you just copy the code into your project. 

Disadvantages of using these views:

* Since correct text orientation is achieved by rotating and mirroring the entire view, a vertically mirrored font must be used with them. This font is not included with this library. However, you may [download them from Menksoft](http://www.menksoft.com/site/alias__menkcms/2805/Default.aspx) (Choose the Photoshop mirrored fonts). 
* It is very difficult to disable the popup menu to replace it with a Mongolian one. (This was the primary reason that this library was started.) 
* Any glyphs not included in the mirrored font will be backwards. That includes all Chinese and other CJK characters. Also, emoji will not be correctly rotated. 

## Unicode 

All of the UI components in this library are designed to use Unicode for all input and output. (However, since glyph rendering internally uses Menksoft code, you can also use Menksoft code for input. This is not recommended, though.) 

The `MongolCode` rendering engine seeks to conform to the [Unicode 10.0 standard](http://unicode.org/versions/Unicode10.0.0/) ([Mongolian Block](https://www.unicode.org/charts/PDF/U1800.pdf)). However, the standard was deviated from in the following two cases:

* MONGOLIAN LETTER GA, second final form. The Unicode 10.0 standard second final form is the feminine form. However, there is no way to override a feminine GA in words like SHIG to make it masculine and the only solution is to explicitly specify a feminine GA in every single neuter word (example: BICHIG(FVS1)). However, none of the major font players do that ([source](https://r12a.github.io/mongolian-variants/#char182D)). They use FVS1 as a masculine override and FVS2 to specify the feminine form. This was also the proposal in [DS01](https://w3c.github.io/mlreq/variants/ds01.pdf). It is much more natural to default to a feminine final GA in neuter words and only override to a masculine GA when necessary. Thus, the rendering engine in this library also follows the DS01 recomendation for final GA.

* MONGOLIAN LETTER I, third medial form. This is undefined in Unicode 10.0. In this library it is being used the same way that the Menksoft fonts use it, that is, as a single long tooth medial I. This decision was made to support the correct rendering of existing Mongolian text files. The `I + FVS2` coding is used in the Menksoft IME to override the context and render a single tooth I in words like NAIMA (eight). There is another solution for input, and that is to use `A + ZWJ + I`. However, that does not solve the problem of rendering existing text. This library's Keyboard IMEs now use the `I + FVS2` rather than `ZWJ + I` encoding. Note that this will cause [incorrect rendering with other fonts](https://r12a.github.io/mongolian-variants/#char1822), many of which use `I + FVS2` to specify a double long-tooth I. 

Todo Script is now supported and rendering is handled completely by the font.

###### Other issues

The Unicode standard does not specify how diphthongs should be encoded (or whether diphthongs exist at all in written Mongolian). For example, the AI of SAIN is sometimes encoded as AI (`\u1820\u1821`) and sometimes encoded as AYI (`\u1820\u1836\u1821`). For this reason, both of these encodings are supported. However, this creates a problem for rendering the AI of NAIMA (eight). The solution this library takes for the NAIMA problem was discussed above in MONGOLIAN LETTER I.

Another problem is how to override the dotted N in names like CHOLMON-ODO or BAYAN-UNDUSU. Menksoft uses `N + FVS1`. The problem is that this deviates from Unicode 10.0 and [many other fonts](https://r12a.github.io/mongolian-variants/#char1828). For that reason, this library does not currently follow Menksoft in this. Another input/rendering solution is to insert a ZWJ after the N. Thus, CHOLMONODO would be rendered (CHOLMON(ZWJ)O(FVS1)DO). However, this is not a standard documented use of `ZWJ`. 

It is hoped that the Unicode standard will introduce an additional control character that could be used similarly to the FVS characters. This new control character would always override the context and make the default form be shown. (This would solve the final GA problem, the NAIMA problem, and the N problem discussed above.)

See the demo app or the [tests](https://github.com/suragch/mongol-library/blob/master/mongol-library/src/test/java/net/studymongolian/mongollibrary/ConvertUnicodeToMenksoftCodeTest.java) for examples of how words are rendered. If you discover any rendering errors then please report them. This is a high priority issue. 

#### Code examples

The `MongolCode` class is the Unicode rendering engine. Generally you won't need to use this class directly, but you can use it to covert between Menksoft code and Unicode if needed. The `MongolCode.Uni` and `MongolCode.Suffix` inner classes may also be useful for references to get Unicode characters and strings. 

###### Unicode <--> Menksoft code conversion

```java
MongolCode converter = MongolCode.INSTANCE;
String unicode;
String menksoftCode;
        
// Unicode -> Menksoft code
unicode = "ᠮᠣᠩᠭᠣᠯ";
menksoftCode = converter.unicodeToMenksoft(unicode);
        
// Menksoft code -> Unicode
menksoftCode = "\uE2F2\uE289\uE2BC\uE2EC\uE289\uE2F9";
unicode = converter.menksoftToUnicode(menksoftCode);
```

###### Mongolian letters and suffixes

```java
char unicodeLetter = MongolCode.Uni.MA;                         // '\u182E'
char unicodePunctuation = MongolCode.Uni.MONGOLIAN_FULL_STOP;   // '\u1803'
String iyerSuffix = MongolCode.Suffix.IYER;                     // "\u202F\u1822\u1836\u1821\u1837"
```

###### Static classes

There are a number of static methods that may also be useful.

* `boolean isMongolian(char character)`
* `boolean isConsonant(char character)`
* `boolean isVowel(char character)`
* `boolean isMasculineVowel(char character)`
* `boolean isFeminineVowel(char character)`
* `boolean isFVS(char character)`
* `boolean isMvsPrecedingChar(char character)`
* `boolean isMenksoft(char character)`

## Fonts

In order to keep the library size as small as possible, only one font is included by default. This is the [Menksoft Qagan font](http://www.menksoft.com/mdls/am/amview.aspx?pid=0&alias=menkcms&iid=168137&mid=15302&wv=U). However, you may include any of the other [Menksoft fonts](http://www.menksoft.com/site/alias__menkcms/2805/Default.aspx) in your project. Either TrueType or OpenType are fine. In fact, the TrueType fonts are smaller and since the OpenType rendering code is not used in this library, the TrueType version of the font may be better when available. 

Some of the Menksoft fonts contain ligature errors for Latin letter combinations like `fi`. See [this Stack Overflow question](https://stackoverflow.com/questions/43315957/how-to-remove-ligatures-from-a-font-using-fontforge). It is hoped that Menksoft will correct these errors by removing the ligature encoding from the affected fonts. 

#### Code example

Use `MongolFont` to create a `TypeFace`. The `MongolFont` class will take care of caching fonts so that you can reuse them on multiple views. You should [create an assets folder](https://stackoverflow.com/questions/26706843/adding-an-assets-folder-in-android-studio) and add the Menksoft font that you want to use (optionally in a `fonts` subfolder).


![custom font example](docs/images/custom-font-example.png)

```java
public class MainActivity extends AppCompatActivity {

    // custom font is stored in the app's assets/fonts folder
    public static final String AMGLANG = "fonts/MAM8102.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MongolTextView mongolTextView = (MongolTextView) findViewById(R.id.mongol_text_view_id);
        mongolTextView.setText("ᠮᠣᠩᠭᠣᠯ");

        // set the custom font
        Typeface customFont = MongolFont.get(AMGLANG, getApplicationContext());
        mongolTextView.setTypeface(customFont);
    }
}
```

Custom fonts can also be added to spans.

## How to contribute 

For this library to be used widely, more testing and development is needed from other developers. 

If you find a bug, open an issue report. Even better would be to add a unit or instrumentation test that shows it. 

The following explanation shows how the library works internally. 

`MongolEditText` extends and adds editing functionality to `MongolTextView`, which itself directly extends View. `MongolTextView` uses `MongolCode` to convert the Unicode text into the Menksoft glyph text codes that are contained in the font. This text is then passed on to `MongolLayout`, which measures the text and breaks it into lines that are laid out vertically from left to right. Each line of text is drawn by `MongolTextLine`, which handles rotating emojis and CJK characters. A text run is the smallest string of characters that are processed together (for drawing or non-linebraking word units). 

`MongolEditText` communicates with the in-app keyboard using `MongolInputMethodManager`. The keyboards (both system and in-app) send input to the `MongolEditText` using `MetInputConnection`. 

The keyboards are embedded in the keyboard container, which acts as a controller switching between the in-app keyboards. It also handles communication with the candidate view (TODO). 

#### TODO 

* [ ] translate this documentation into Mongolian and correct the Chinese translation
* [ ] `MongolTextView` line spacing
* [ ] `MongolTextView` or its helper classes need optimization. Slow rendering time for long text strings.
* [ ] more `MongolAlertDialog` types (check box, radio button, list)
* [ ] add lots more jUnit and instrumentation tests 
* [ ] apply style/theme colors to `MongolTextView`, `MongolLabel` and `MongolEditText` so that the default colors are correct for both light and dark themes.
* [ ] Convert more punctuation and NNBS in to Menksoft code in MongolCode

#### Version changes 

* `1.16.3`: Make hooked Y the default when entering [Vowel + Y + I] using the `ImeContainer`
* `1.16.2`: Shadow, text view, and edit text bug fixes
* `1.16.1`: Added ability to set text shadow
* `1.16.0`: Added ability to stroke text in `MongolTextView` and `MongolLayout`
* `1.15.0`: Made more MongolLayout methods public to match the standard Android Layout classes
* `1.14.0`: Renamed English keyboard to Latin and added more symbols; improved `MongolAlertDialog`; bug fixes
* `1.13.0`: Keyboard improvements: punctuation/ZWJ layout changes, punctuation auto-spacing, double tap shortcuts for MVS/period, swipe up shortcuts; bug fixes 
* `1.12.4`: Made ImeContainer.getPreviousMongolWords() public
* `1.12.3`: Bug fixes and minor adjustments
* `1.12.2`: Improved Menksoft to Unicode conversion, targeting Android P
* `1.12.1`: Improved Menksoft to Unicode conversion
* `1.12.0`: `MongolEditText` extracted text support, scrolling to cursor position (when in HorizontalTextView), Keyboard toolbar custom icons, bug fixes
* `1.11.1`: Bug fixes for `MongolEditText` context menu
* `1.11.0`: Added a default context menu to `MongolEditText`. Can also replace with custom menu.
* `1.10.0`: Allow custom keyboard to request a system keyboard
* `1.9.1`: Bug fix for crash caused by selection index out of bounds (new bug added in 1.9.0)
* `1.9.0`: Added navigation support to `MongolEditText` and navigation keyboard to `ImeContainer`; bug fixes.
* `1.8.1`: Bug fix for keyboard candidate view item long click 
* `1.8.0`: Updates to `ImeContainer.DataSource` API, allow suffix candidates, added more javadocs
* `1.7.2`: Spacing and popup improvements for IME
* `1.7.0`: Further updates to `ImeContainer.DataSource` API
* `1.6.0`: Updated `ImeContainer.DataSource` methods to better support database updates, added Espresso tests to demo app
* `1.5.0`: Allow ImeContainer to request keyboard candidate word updates asynchronously 
* `1.4.1`: Very minor bug fixes for Keyboard candidate view
* `1.4.0`: Added `MongolMenu`. Bug fixes.
* `1.3.1`: Fixed `MongolEditText` cursor blinking bug and keyboard keeps showing on exit bug; added CHU suffix to Qwerty keyboard.
* `1.3.0`: Added support for Todo Script; removed internal glyph index mapping (glyph and unicode text is one-to-one now)
* `1.2.0`: Fixed system keyboard popup clipping, support for naming keyboards and setting fonts in xml, ability to change keyboard based on editor's `InputType`.
* `1.1.0`: Added support for making custom system keyboards
* `1.0.0`: All basic components are working. Major upgrade to the Keyboard system.
* `0.9.16`: Support for II spelling of double tooth I
* `0.9.15`: Changed `MongolCode` rendering and Keyboards to support medial `I + FVS2` for words like NAIMA. (This is a Unicode 10.0 deviation.)
* `0.9.14`: Added full `TextWatcher` support (text change listener)
* `0.9.13`: Made `MongolCode.isMongolian()` and `MongolCode.isMenksoft()` public
* `0.9.12`: Made more MongolCode methods public; added color theme for KeyImage (for light and dark keyboard icons)
* `0.9.11`: Customize keyboard properties like key background color, borders widths, radius, and popups.
* `0.9.10`: QWERTY keyboard, ability to add custom keyboard; update for Android API 27.
* `0.9.8`: Allow both touch events and click events on `MongolEditText`; fixed spacing on `MongolAlertView` with no buttons 
* `0.9.7`: Added support for `UnderlineSpan` and `ClickableSpan`
* `0.9.6`: fixed resizing bug, added text change listener to `MongolEditText`
* `0.9.5`: fixed rotation for vertical presentation forms
* `0.9.4`: Started adding instrumentation tests for `MongolTextStorage`; crash fix in `MongolTextStorage`
* `0.9.3`: `MongolEditText` crash fix, automatic keyboard switching
* `0.9.2`: Android Oreo updates. `MongolLabel` vertical auto-resizing bug fix
* `0.9.1`: Bug fix that was causing crashes for `setText` on `MongolEditText`
* `0.9.0`: Removed extra font and resource files to make as small as possible
* `0.8.2`: Updated to Unicode 10.0 

## External links 

* Groups
    * [Menksoft](http://www.menksoft.com/)
        * [fonts](http://www.menksoft.com/site/alias__menkcms/2805/Default.aspx)
    * [Delehi](http://www.delehi.com)
    * [Almas](http://www.mongolfont.com/en/android/index.html)
    * [Inner Mongolia University](http://mglip.com/)
    * [Orhon open source community](http://www.openmgl.org/)
* Unicode
    * [Unicode: Mongolian block](http://unicode.org/charts/PDF/U1800.pdf)
    * [Mongolian variant forms ](https://r12a.github.io/mongolian-variants/)
    * [DS01 document](https://w3c.github.io/mlreq/variants/ds01.pdf)
* Tools
    * [Code analyzer](http://r12a.github.io/apps/conversion/)
    * [Mongol online editor](http://mongol-bichig.top/editor)
    * [Unicode - Menksoft code online converter](http://mtg.mglip.com/)
    * [Cyrillic - Tradional script online converter](http://trans.mglip.com/)
    * [Code analyzer](http://r12a.github.io/apps/conversion/)

## Apps that use this library

* [Demo app](https://github.com/suragch/mongol-library/tree/master/demo-app)
* [Chimee](https://github.com/suragch/Chimee)
* [Suryaa](https://github.com/suragch/Suryaa) 
* [Todo Keyboard](https://github.com/suragch/TodoKeyboard)

If your app uses this library, you can notify me or add it here, especially if it is open source.
