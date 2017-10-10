package com.example.android.i_am_baker;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ROHAN on 09-10-2017.
 */
@RunWith(AndroidJUnit4.class)
public class StepVideoTest {
    @Rule
    public ActivityTestRule<MainActivity> vActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void click_step_takes_to_video_of_it()
    {

      //  onView(withId(R.id.onlystep))
       //         .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        //onView(withId(R.id.detail)).check(matches(isDisplayed()));

       // onView(allOf(withId(R.id.exoplayer_view),withClassName(is(VideoFragment.class.getName()))));





            onView(withId(R.id.types))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
            onView(withId(R.id.onlystep)).check(matches(isDisplayed()));







    }





}

