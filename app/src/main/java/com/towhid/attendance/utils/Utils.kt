package com.towhid.attendance.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.towhid.attendance.R
import java.util.*


object Utils {
    private const val TAG = "Utils"


    fun View.showKeyboard() {
        if (this is EditText) {
            setSelection(this.length())
        }
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun View.hideKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        this.clearFocus()
    }


    fun replaceFragmentWithoutBackStack(activity: Activity, fragment: Fragment?) {
        val fragmentManager1 = (activity as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager1.beginTransaction()
        fragmentTransaction.replace(R.id.container_activity_main, fragment!!)
        fragmentTransaction.commitAllowingStateLoss()
    }
    fun replaceFragmentWithBackStack(activity: Activity, fragment: Fragment?) {
        val fragmentManager1 = (activity as FragmentActivity).supportFragmentManager
        val fragmentTransaction = fragmentManager1.beginTransaction()
        fragmentTransaction.add(R.id.container_activity_main, fragment!!)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun View.show() {
        this.visibility = View.VISIBLE
    }

    fun View.hide() {
        this.visibility = View.GONE
    }


    /**
     * Function to return [List<Address>],
     * from [Location] instance using [Geocoder]
     * */
    fun Context.getAddressFroLocation(location: Location?): List<Address>? {
        if (location == null) return null

        val gcd = Geocoder(this, Locale.getDefault())
        try {
            return gcd.getFromLocation(location.latitude, location.longitude, 1)
        } catch (e: Exception) {
            Log.e(TAG, "getAddressFroLocation: ", e)
        }

        return null
    }
    fun Context.isDarkTheme(): Boolean {
        return (resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_YES
    }

    fun NavController.safeNavigate(@IdRes resId: Int) {
        currentDestination?.getAction(resId)?.run {
            navigate(resId)
        }
    }


}