package com.ihariza.news.report;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.ihariza.news.R;
import com.ihariza.news.presentation.view.main.MainActivity;
import com.ihariza.news.presentation.view.util.RequestCountingIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
public class ReportFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        IdlingRegistry.getInstance()
                .register(RequestCountingIdlingResource.getCountingIdlingResource());
    }

    @After
    public void tear() {
        IdlingRegistry.getInstance()
                .unregister(RequestCountingIdlingResource.getCountingIdlingResource());
    }

    @Test
    public void whenPressBackShouldShowNews() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerview));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()));
    }
}
