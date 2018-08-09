package com.sample;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.sample.sampleapp.MainActivity;
import com.sample.sampleapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.sample.sampleapp.network.ConnectionDetector.isConnected;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void setUp() throws Exception {
        //Before Test case execution
    }

    @Test
    public void test1ChatId() throws Exception {
        onView(withId(R.id.rv_announcements)).check(matches(isDisplayed()));

    }
    @Test
    public void clickOnItemWithTextEqualToTwo() throws  Exception{
        onView(withId(R.id.rv_announcements)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

    }
    @Test
    public void testInternetConnection() throws Exception{
        assertTrue(isConnected(mActivityRule.getActivity()));
    }

    @Test
    public void testSample() throws Exception{
        if (getRVcount() > 0){
            onView(withId(R.id.rv_announcements)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }

    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) mActivityRule.getActivity().findViewById(R.id.rv_announcements);
        return recyclerView.getAdapter().getItemCount();
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }

}


