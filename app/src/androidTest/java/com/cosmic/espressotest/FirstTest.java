package com.cosmic.espressotest;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Run app on physical device or emulator
 * Then run FirstTest
 *
 * As we proceed to check for failed tests, once we verify it failed as
 * expected, comment out the test method or test methods individually
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest {

    /**
     * Assign the activity being examined
     */
    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception{
        //before test case execution
    }

    /**
     * This test passes because there is a textView in activity_main.xml that has this exact text
     */
    @Test
    public void checkIfTextViewWithHelloWorldTextIsDisplayed(){
        onView(withText("Hello World!")).check(matches(isDisplayed()));
    }

    /*
     * activity_main.xml has no text view visible with this matching text. so the test fails. The report produces:
     * androidx.test.espresso.NoMatchingViewException: No views in hierarchy found matching: with text: is "Is anybody there?"
     */
    @Test
    public void thisTestFailsBecauseThereIsNotMatchingText(){
        onView(withText("Is anybody there?")).check(matches(isDisplayed()));
    }

    /*
     * This test fails because the button in activity_main.xml is currently set
     * to: android:enabled="false"
     *
     * The test produces the result:
     * androidx.test.espresso.base.DefaultFailureHandler$AssertionFailedWithCauseError: 'is enabled' doesn't match the selected view.
     *
     * In our next test, change the button in activity_main.xml to:
     * android:enabled="true"
     * and run the test with the same code
     */
    @Test
    public void isButtonEnabled(){
        onView(withId(R.id.button)).check(matches(isEnabled()));
    }

    /**
     * Running the test on the whole class now passes (comment out previous test)
     * once we change the button to set enabled true
     */
    @Test
    public void isTheButtonNowEnabled(){
        onView(withId(R.id.button)).check(matches(isEnabled()));
    }

    /**
     * This test fails. We did several different things here. First, we check for withHint()
     * instead of withText() or withId().
     *
     * Currently the edit text in activity_main.xml is:
     * android:focusable="false"
     *
     * So this test should fail because we are checking if the isFocusable() call is true
     */
    @Test
    public void isTheEditTextFocusable(){
        onView(withHint("Am I focusable?")).check(matches(isFocusable()));
    }

    /**
     * Keep the button in activity_main.xml set to:
     * android:focusable="false"
     * import the static method:
     * import static org.hamcrest.Matchers.not;
     * to check for the button not being focusable. This test should pass because we are checking to see
     * if it is not focusable, which it currently is
     */
    @Test
    public void isTheEditTextNotFocusable(){
        onView(withHint("Am I focusable?")).check(matches(not(isFocusable())));
    }

    /**
     * Now change the edit text in activity_main.xml to:
     * android:focusable="true"
     * and run the original code to check if it is focusable
     * the test should now pass
     */
    @Test
    public void canINowEnterTextIntoThisEditText(){
        onView(withHint("Am I focusable?")).check(matches(isFocusable()));
    }

    /**
     * Now create an ImageView in activity_main.xml like this:
     * <ImageView
     *         android:layout_width="200dp"
     *         android:layout_height="200dp"
     *         android:id="@+id/ivOne"
     *         android:layout_below="@+id/etCheckIfFocusable"
     *         android:visibility="gone"/>
     * We want to check the visibility of the image view. We are checking for it to return true, so this
     * test should fail. We will alter the ImageView for the next test so it passes
     */
    @Test
    public void imageViewVisibilityTestThatFails(){
        onView(withId(R.id.ivOne)).check(matches(isDisplayed()));
    }

    /**
     * Change the attribute in the ImageView to:
     * android:visibility="visible"
     * and rerun the test with identical code inside the method.
     * This test will now pass
     */
    @Test
    public void imageViewIsDisplayedPassingTest(){
        onView(withId(R.id.ivOne)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception{
        //after test has executed
    }

}
