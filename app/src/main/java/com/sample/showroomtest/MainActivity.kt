package com.sample.showroomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = RepositoryListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment, FRAGMENT)
                .commit()
        }
    }
}