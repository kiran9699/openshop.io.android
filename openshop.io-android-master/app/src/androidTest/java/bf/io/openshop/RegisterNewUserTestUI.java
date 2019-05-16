package bf.io.openshop;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.volley.RequestQueue;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import bf.io.openshop.UX.MainActivity;
import bf.io.openshop.UX.SplashActivity;
import bf.io.openshop.testing.FakeRequestQueue;

import static android.support.test.espresso.action.ViewActions.typeText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterNewUserTestUI {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

      @Rule
      public IntentsTestRule<MainActivity> mActivityTestRule = new IntentsTestRule<>(MainActivity.class, true, false);

      @BeforeClass
     public static void fakeNetworkLayer() {
          RequestQueue requestQueue = new FakeRequestQueue(MyApplication.getInstance());
          MyApplication.getInstance().setRequestQueue(requestQueue);
      }

      private void preparationFirstRun() {
          SettingsMy.setActualShop(null);
     }
    @Test
    public void registerUser() throws Exception{
        Thread.sleep(15000);
        preparationFirstRun();
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
        Thread.sleep(15000);
    }


    @Test
    public void searchAndAddToCart() throws Exception{
        Thread.sleep(500);
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

    }


}




