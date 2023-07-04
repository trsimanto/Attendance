package com.towhid.attendance

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.towhid.attendance.fragments.fgSubmission.view.SubmissionFragment
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.towhid.attendance", appContext.packageName)
    }


    @JvmField
    @Rule
    var fragmentTestRule: FragmentTestRule<*, SubmissionFragment> =
        FragmentTestRule.create(
            SubmissionFragment::class.java
        )

    @Test
    @Throws(Exception::class)
    fun clickButton() {
        onView(withId(R.id.et_name))
            .perform(typeText("user"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.et_user_id))
            .perform(typeText("user_id"))
            .perform(ViewActions.closeSoftKeyboard())


    }

}