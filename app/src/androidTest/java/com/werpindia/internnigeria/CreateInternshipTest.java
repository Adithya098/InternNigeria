package com.werpindia.internnigeria;

import com.werpindia.internnigeria.activities.CreateInternshipActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class CreateInternshipTest
{
    @Rule
    public ActivityTestRule<CreateInternshipActivity> activityTestRule = new ActivityTestRule<>(CreateInternshipActivity.class);

    @Test
    public void categorySpinnerTest()
    {
        onView(withId(R.id.internshipCategory)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("Mobile App Development"))).perform(click());
    }

    @Test
    public void durationLengthSpinnerTest()
    {
        onView(withId(R.id.internshipDurationNumber)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("1"))).perform(click());
    }

    @Test
    public void durationTypeSpinnerTest()
    {
        onView(withId(R.id.internshipDuration)).perform(click());
        onData(allOf(is(instanceOf(String.class)),is("Weeks"))).perform(click());
    }
}
