package com.example.androiduitesting;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    private static final String TEST_CITY_NAME = "Toronto";
    private static final int CITY_INDEX = 0;

    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setupCityInList(){
        onView(withId(R.id.button_add)).perform(click());

        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(TEST_CITY_NAME));

        onView(withId(R.id.button_confirm)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(CITY_INDEX).check(matches(withText(TEST_CITY_NAME)));

    }

    /**
     * Test 1: Check if the activity correctly switched from MainActivity
     */

    @Test
    public void testActivitySwitchCorrect(){
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(CITY_INDEX).perform(click());
        onView(withId(R.id.button_back)).check(matches(isDisplayed()));

    }

    /**
     * Test 2: Check if city name is correct
     */

    @Test
    public void testCityNameIsCorrect(){
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(CITY_INDEX).perform(click());

        onView(withId(R.id.text_city_name)).check(matches(withText(TEST_CITY_NAME)));
    }

    /**
     * Test 3: Check if back button is functional
     */

    @Test
    public void testBackButton(){
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(CITY_INDEX).perform(click());

        onView(withId(R.id.button_back)).perform(click());

        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }
}
