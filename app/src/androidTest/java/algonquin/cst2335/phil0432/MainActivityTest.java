//package algonquin.cst2335.phil0432;
//
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.Espresso.pressBack;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static androidx.test.espresso.action.ViewActions.replaceText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withParent;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.allOf;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import androidx.test.espresso.ViewInteraction;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.filters.LargeTest;
//
//import org.hamcrest.Description;
//import org.hamcrest.Matcher;
//import org.hamcrest.TypeSafeMatcher;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@LargeTest
//@RunWith(AndroidJUnit4.class)
//public class MainActivityTest {
//
//    @Rule
//    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(MainActivity.class);
//
//    /**
//     *This test case tests to verify when the password entered does not meet the necessary requirements.
//     */
//    @Test
//    public void mainActivityTest() {
//
//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.editTextCity) );
//        appCompatEditText.perform(replaceText("12345"), closeSoftKeyboard());
//
//
//        ViewInteraction materialButton = onView(
//                withId(R.id.forecastButton) );
//       materialButton.perform(click());
//
//        ViewInteraction textView = onView(
//                withId(R.id.textViewName) );
//        textView.check(matches(withText("You shall not pass!")));
//    }
//
//    /**
//     * Test case to verify that when the password entered is missing an uppercase letter,
//     * the TextView displays "You shall not pass!".
//     */
//    @Test
//    public void testFindMissingUpperCase(){
//        ViewInteraction appCompatEditText = onView( withId(R.id.editTextCity) );
//
//        appCompatEditText.perform(replaceText("password123#$*"));
//
//        ViewInteraction materialButton = onView( withId(R.id.forecastButton) );
//
//        materialButton.perform(click());
//
//        ViewInteraction textView = onView( withId(R.id.textViewName) );
//        textView.check(matches(withText("You shall not pass!")));
//    }
//
//    /**
//     * Test case to verify that when the password entered is missing a lowercase letter,
//     * the TextView displays "You shall not pass!".
//     */
//    @Test
//    public void testFindMissingLowerCase(){
//        ViewInteraction appCompatEditText = onView( withId(R.id.editTextCity) );
//
//        appCompatEditText.perform(replaceText("PASSWORD123#$*"));
//
//        ViewInteraction materialButton = onView( withId(R.id.forecastButton) );
//
//        materialButton.perform(click());
//
//        ViewInteraction textView = onView( withId(R.id.textViewName) );
//        textView.check(matches(withText("You shall not pass!")));
//    }
//
//    /**
//     * Test case to verify that when the password entered is missing a digit,
//     * the TextView displays "You shall not pass!".
//     */
//    @Test
//    public void testFindMissingDigit() {
//
//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.editTextCity) );
//        appCompatEditText.perform(replaceText("Password#$*"), closeSoftKeyboard());
//
//
//        ViewInteraction materialButton = onView(
//                withId(R.id.forecastButton) );
//        materialButton.perform(click());
//
//        ViewInteraction textView = onView(
//                withId(R.id.textViewName) );
//        textView.check(matches(withText("You shall not pass!")));
//    }
//
//    /**
//     * Test case to verify that when the password entered is missing a special character,
//     * the TextView displays "You shall not pass!".
//     */
//    @Test
//    public void testFindMissingSpecialCharacter() {
//
//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.editTextCity) );
//        appCompatEditText.perform(replaceText("Password123"), closeSoftKeyboard());
//
//
//        ViewInteraction materialButton = onView(
//                withId(R.id.forecastButton) );
//        materialButton.perform(click());
//
//        ViewInteraction textView = onView(
//                withId(R.id.textViewName) );
//        textView.check(matches(withText("You shall not pass!")));
//    }
//
//    /**
//     * Test case to verify that when the password entered has all the necessary requirements,
//     * the TextView displays "You shall not pass!".
//     */
//    @Test
//    public void testAllRequirements() {
//
//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.editTextCity) );
//        appCompatEditText.perform(replaceText("Password123#$*"), closeSoftKeyboard());
//
//
//        ViewInteraction materialButton = onView(
//                withId(R.id.forecastButton) );
//        materialButton.perform(click());
//
//        ViewInteraction textView = onView(
//                withId(R.id.textViewName) );
//        textView.check(matches(withText("Your password meets the requirements")));
//    }
//
//    private static Matcher<View> childAtPosition(
//            final Matcher<View> parentMatcher, final int position) {
//
//        return new TypeSafeMatcher<View>() {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Child at position " + position + " in parent ");
//                parentMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                ViewParent parent = view.getParent();
//                return parent instanceof ViewGroup && parentMatcher.matches(parent)
//                        && view.equals(((ViewGroup) parent).getChildAt(position));
//            }
//        };
//    }
//}
