package net.studymongolian.mongollibrarydemo;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
        recyclerView.perform(actionOnItemAtPosition(11, click()));

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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
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
                withClassName(is("android.support.v7.widget.RecyclerView")));
        recyclerView13.perform(actionOnItemAtPosition(2, click()));

    }

    @Test
    public void mongolToastTest() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvApiDemoList),
                        childAtPosition(
                                withId(R.id.activity_main),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(7, click()));

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
