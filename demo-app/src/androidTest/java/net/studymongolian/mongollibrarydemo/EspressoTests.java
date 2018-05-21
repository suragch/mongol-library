package net.studymongolian.mongollibrarydemo;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mongolMenuTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(13, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.mongol_menu_button), withText("Mongol Menu (show as dropdown)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction recyclerView2 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.mongol_menu_button), withText("Mongol Menu (show as dropdown)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction recyclerView3 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView3.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.mongol_menu_button), withText("Mongol Menu (show as dropdown)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction recyclerView4 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView4.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.button3), withText("Mongol Menu (show at location)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction recyclerView5 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView5.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.button3), withText("Mongol Menu (show at location)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction recyclerView6 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView6.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.button3), withText("Mongol Menu (show at location)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction recyclerView7 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView7.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.button4), withText("Mongol Menu (no icons)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction recyclerView8 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView8.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.button4), withText("Mongol Menu (no icons)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction recyclerView9 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView9.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.button4), withText("Mongol Menu (no icons)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction recyclerView10 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView10.perform(actionOnItemAtPosition(2, click()));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_menu), withContentDescription("menu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction recyclerView11 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView11.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_menu), withContentDescription("menu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction recyclerView12 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView12.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.action_menu), withContentDescription("menu"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.my_toolbar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction recyclerView13 = onView(
                childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0));
        recyclerView13.perform(actionOnItemAtPosition(2, click()));

        pressBack();

    }

    @Test
    public void mongolToastTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(8, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withText("Toast"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Mongol Toast"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Mongol Toast (xml string)"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton3.perform(click());

        pressBack();

    }

    @Test
    public void keyboardTests() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(6, click()));

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("From XML"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("From code"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.support.v7.view.menu.ListMenuItemView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        pressBack();

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView2.perform(actionOnItemAtPosition(7, click()));

        pressBack();

    }

    @Test
    public void mongolEditTextTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(5, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withText("input text"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withText("select"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withText("delete"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                2),
                        isDisplayed()));
        appCompatButton12.perform(click());

        pressBack();

    }

    @Test
    public void mongolFontTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(4, click()));

    }

    @Test
    public void labelTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.fontcolor_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.fontcolor_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.fontcolor_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.fontcolor_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatSpinner4.perform(click());

        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.fontsize_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatSpinner5.perform(click());

        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatSpinner6 = onView(
                allOf(withId(R.id.fontsize_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatSpinner6.perform(click());

        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatCheckedTextView6.perform(click());

        ViewInteraction appCompatSpinner7 = onView(
                allOf(withId(R.id.fontsize_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatSpinner7.perform(click());

        DataInteraction appCompatCheckedTextView7 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        appCompatCheckedTextView7.perform(click());

        ViewInteraction appCompatSpinner8 = onView(
                allOf(withId(R.id.fontsize_spinner),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatSpinner8.perform(click());

        DataInteraction appCompatCheckedTextView8 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(4);
        appCompatCheckedTextView8.perform(click());

        pressBack();

    }

    @Test
    public void mongolTextViewTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction appCompatButton = onView(
                allOf(withText("Text"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Text"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withText("Text"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withText("Color"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton4.perform(click());

        DataInteraction appCompatCheckedTextView4 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withText("Color"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton5.perform(click());

        DataInteraction appCompatCheckedTextView5 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withText("Color"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                0),
                        isDisplayed()));
        appCompatButton6.perform(click());

        DataInteraction appCompatCheckedTextView6 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withText("Alignment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton7.perform(click());

        DataInteraction appCompatCheckedTextView7 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView7.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withText("Alignment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton8.perform(click());

        DataInteraction appCompatCheckedTextView8 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withText("Alignment"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                0),
                        isDisplayed()));
        appCompatButton9.perform(click());

        DataInteraction appCompatCheckedTextView9 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView9.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton10.perform(click());

        DataInteraction appCompatCheckedTextView10 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView10.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton11.perform(click());

        DataInteraction appCompatCheckedTextView11 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView11.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton12.perform(click());

        DataInteraction appCompatCheckedTextView12 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(3);
        appCompatCheckedTextView12.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton13.perform(click());

        DataInteraction appCompatCheckedTextView13 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(4);
        appCompatCheckedTextView13.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withText("Size"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton14.perform(click());

        DataInteraction appCompatCheckedTextView14 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView14.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton15.perform(click());

        DataInteraction appCompatCheckedTextView15 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView15.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton17.perform(click());

        DataInteraction appCompatCheckedTextView16 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView16.perform(click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton18.perform(scrollTo(), click());

        ViewInteraction appCompatButton19 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton19.perform(click());

        DataInteraction appCompatCheckedTextView17 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView17.perform(click());

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton20.perform(scrollTo(), click());

        ViewInteraction appCompatButton21 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton21.perform(click());

        DataInteraction appCompatCheckedTextView18 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(3);
        appCompatCheckedTextView18.perform(click());

        ViewInteraction appCompatButton22 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton22.perform(scrollTo(), click());

        ViewInteraction appCompatButton23 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton23.perform(click());

        DataInteraction appCompatCheckedTextView19 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(4);
        appCompatCheckedTextView19.perform(click());

        ViewInteraction appCompatButton24 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton24.perform(scrollTo(), click());

        ViewInteraction appCompatButton25 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton25.perform(click());

        DataInteraction appCompatCheckedTextView20 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(5);
        appCompatCheckedTextView20.perform(click());

        ViewInteraction appCompatButton26 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton26.perform(scrollTo(), click());

        ViewInteraction appCompatButton27 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton27.perform(click());

        DataInteraction appCompatCheckedTextView21 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(6);
        appCompatCheckedTextView21.perform(click());

        ViewInteraction appCompatButton28 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton28.perform(scrollTo(), click());

        ViewInteraction appCompatButton29 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton29.perform(click());

        DataInteraction appCompatCheckedTextView22 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(7);
        appCompatCheckedTextView22.perform(click());

        ViewInteraction appCompatButton30 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton30.perform(scrollTo(), click());

        ViewInteraction appCompatButton31 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton31.perform(click());

        DataInteraction appCompatCheckedTextView23 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(8);
        appCompatCheckedTextView23.perform(click());

        ViewInteraction appCompatButton32 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton32.perform(scrollTo(), click());

        ViewInteraction appCompatButton33 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton33.perform(click());

        DataInteraction appCompatCheckedTextView24 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(9);
        appCompatCheckedTextView24.perform(click());

        ViewInteraction appCompatButton34 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton34.perform(scrollTo(), click());

        ViewInteraction appCompatButton35 = onView(
                allOf(withText("Span"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        1),
                                1),
                        isDisplayed()));
        appCompatButton35.perform(click());

        ViewInteraction appCompatButton36 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        appCompatButton36.perform(scrollTo(), click());

        ViewInteraction appCompatButton37 = onView(
                allOf(withText("Padding"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton37.perform(click());

        DataInteraction appCompatCheckedTextView25 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView25.perform(click());

        ViewInteraction appCompatButton38 = onView(
                allOf(withText("Padding"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton38.perform(click());

        DataInteraction appCompatCheckedTextView26 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView26.perform(click());

        ViewInteraction appCompatButton39 = onView(
                allOf(withText("Padding"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        2),
                                1),
                        isDisplayed()));
        appCompatButton39.perform(click());

        DataInteraction appCompatCheckedTextView27 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView27.perform(click());

        ViewInteraction appCompatButton40 = onView(
                allOf(withText("Font"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton40.perform(click());

        DataInteraction appCompatCheckedTextView28 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView28.perform(click());

        ViewInteraction appCompatButton41 = onView(
                allOf(withText("Font"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton41.perform(click());

        DataInteraction appCompatCheckedTextView29 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView29.perform(click());

        ViewInteraction appCompatButton42 = onView(
                allOf(withText("Font"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton42.perform(click());

        DataInteraction appCompatCheckedTextView30 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(3);
        appCompatCheckedTextView30.perform(click());

        ViewInteraction appCompatButton43 = onView(
                allOf(withText("Font"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton43.perform(click());

        DataInteraction appCompatCheckedTextView31 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(4);
        appCompatCheckedTextView31.perform(click());

        ViewInteraction appCompatButton44 = onView(
                allOf(withText("Font"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.button_layout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton44.perform(click());

        DataInteraction appCompatCheckedTextView32 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView32.perform(click());

        pressBack();

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
