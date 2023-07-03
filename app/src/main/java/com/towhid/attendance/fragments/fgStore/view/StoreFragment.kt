package com.towhid.attendance.fragments.fgStore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.towhid.attendance.R

class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        init(view)
        action(view)
        return view

    }

    private fun action(view: View?) {

    }

    private fun init(view: View?) {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StoreFragment()
    }
}