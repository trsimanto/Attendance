package com.towhid.attendance.toast

import android.app.Activity
import android.app.AlertDialog
import android.widget.Toast
import com.towhid.attendance.application.Attendance
import java.net.UnknownHostException
import javax.inject.Inject

class ToastShow @Inject constructor() {
    fun toast(massage: String) {
        Toast.makeText(Attendance.appContext, massage, Toast.LENGTH_SHORT).show()
    }

    fun showInternetError(context: Activity, err: Throwable, message: String = "No Internet Connection") {
        val alert = AlertDialog.Builder(context).setTitle(message)
            .setPositiveButton("Ok", null)
        if (err is UnknownHostException && !context.isFinishing){
            alert.show()
        }
    }

    fun longToast(massage: String) {
        Toast.makeText(Attendance.appContext, massage, Toast.LENGTH_LONG).show()
    }
}
