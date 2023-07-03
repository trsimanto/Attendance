package com.towhid.attendance.activities.acMain.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.towhid.attendance.databinding.ActivityMainBinding
import com.towhid.attendance.fragments.fgStore.view.StoreFragment
import com.towhid.attendance.utils.Utils.replaceFragmentWithoutBackStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        action()
    }

    private fun init() {
        replaceFragmentWithoutBackStack(this, StoreFragment.newInstance())

    }

    private fun action() {

    }

}