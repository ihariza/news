package com.ihariza.news.news;


import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import com.ihariza.news.R;
import com.ihariza.news.presentation.view.main.MainActivity;
import com.ihariza.news.presentation.view.news.NewsFragment;
import com.ihariza.news.presentation.view.util.RequestCountingIdlingResource;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
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
import static org.hamcrest.Matchers.not;

@LargeTest
public class NewFragmentTest {

    private NewsFragment fragment;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        activityScenarioRule.getScenario()
                .onActivity(activity -> {
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    fragment = (NewsFragment) fragmentManager
                            .findFragmentByTag(NewsFragment.class.getName());
                });
        IdlingRegistry.getInstance()
                .register(RequestCountingIdlingResource.getCountingIdlingResource());
    }

    @After
    public void tear() {
        IdlingRegistry.getInstance()
                .unregister(RequestCountingIdlingResource.getCountingIdlingResource());
    }

    @Test
    public void whenClickOnItemShouldOpenDetailView() {
        ViewInteraction recyclerView = onView(withId(R.id.recyclerview));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_share)).check(matches(isDisplayed()));
    }

    @Test
    public void hideRefresh() {
        fragment.hideRefresh();

        onView(withId(R.id.swipe_refresh)).check(matches(not(isRefreshing())));
    }

    private Matcher<View> isRefreshing() {
        return new BoundedMatcher<View, SwipeRefreshLayout> (SwipeRefreshLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("is refreshing");
            }

            @Override
            protected boolean matchesSafely(SwipeRefreshLayout item) {
                return item.isRefreshing();
            }
        };
    }
}
