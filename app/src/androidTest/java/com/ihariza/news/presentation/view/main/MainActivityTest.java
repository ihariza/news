package com.ihariza.news.presentation.view.main;


import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ihariza.news.R;
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
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<>(MainActivity.class);

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
    public void fragmentContainerShouldBeShown() {
        onView(withId(R.id.container)).check(matches(isDisplayed()));
    }

    @Test
    public void whenClickOnItemShouldOpenDetailView() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerview));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_share)).check(matches(isDisplayed()));
    }
}
