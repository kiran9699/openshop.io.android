package bf.io.openshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.RequestQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import bf.io.openshop.ListMatcher;
import bf.io.openshop.MyApplication;
import bf.io.openshop.R;
import bf.io.openshop.SettingsMy;
import bf.io.openshop.UX.MainActivity;
import bf.io.openshop.UX.SplashActivity;
import bf.io.openshop.testing.FakeRequestQueue;
import bf.io.openshop.utils.Analytics;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestUI {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     * <p/>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     * Third parameter is set to false which means the activity is not started automatically
     */
    @Rule
    public IntentsTestRule<SplashActivity> mActivityTestRule = new IntentsTestRule<>(SplashActivity.class, true, false);

    @BeforeClass
    public static void fakeNetworkLayer() {
        RequestQueue requestQueue = new FakeRequestQueue(MyApplication.getInstance());
        MyApplication.getInstance().setRequestQueue(requestQueue);
    }

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests significantly
     * more reliable.
     */
    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(MyApplication.getInstance().getCountingIdlingResource());
//        SettingsMy.setActualShop(null);  USE this and look how to start activity with different intent.
    }

    private void preparationFirstRun() {
        SettingsMy.setActualShop(null);
    }


    @Test
    public void test() throws Exception{
        preparationFirstRun();
        mActivityTestRule.launchActivity(null);

        onView(withId(R.id.splash_continue_to_shop_btn)).perform(click());
        Thread.sleep(15000);
        onView(withId(R.id.action_wish_list)).perform(click());

        Thread.sleep(15000);
        onView(withId(R.id.login_form_registration_btn)).perform(click());
        Thread.sleep(15000);
        onView(withId(R.id.login_registration_email_text_auto))
                .perform(typeText("sasi.batchu2277@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.login_registration_password_auto))
                .perform(typeText("Test@123"), closeSoftKeyboard());
        Thread.sleep(5000);
        onView(withId(R.id.login_registration_sex_man)).perform(click());
        onView(withId(R.id.login_registration_confirm)).perform(click());
    }

    @Test
    public void searchAndAddToCart() throws Exception{

        preparationFirstRun();
        mActivityTestRule.launchActivity(null);
        Thread.sleep(500);
        onView(withId(R.id.splash_continue_to_shop_btn)).perform(click());
        Thread.sleep(15000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(500);
        onView(withId(R.id.navigation_drawer_list_header_text)).perform(click());
        Thread.sleep(500);
        // Check the All buttons is presence or not under profile screen
        onView(withId(R.id.account_dispensing_places)).check(matches(isDisplayed()));
        onView(withId(R.id.account_settings)).check(matches(isDisplayed()));
        onView(withId(R.id.account_login_logout_btn)).check(matches(isDisplayed()));
        Thread.sleep(500);
        onView(withId(R.id.action_search)).perform(click());
        Thread.sleep(20000);

        onView (withId(R.id.account_dispensing_places)).perform(click());
        Thread.sleep(10000);
        ViewActions.pressBack();
        onView(withId(R.id.shipping_dialog_close)).perform(click());
        Thread.sleep(10000);
        onView(withId(R.id.account_settings)).perform(click());
        Thread.sleep(10000);

        Thread.sleep(10000);
        ViewActions.pressBack();

        intended(hasComponent(MainActivity.class.getName()));

    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(MyApplication.getInstance().getCountingIdlingResource());
    }
}
