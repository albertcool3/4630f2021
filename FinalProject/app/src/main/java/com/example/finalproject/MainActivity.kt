package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.finalproject.Fragments.EventView
import com.example.finalproject.Fragments.MonthView
import com.example.finalproject.Fragments.WeeklyView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val monthlyFragment = MonthView()
    private val weeklyFragment = WeeklyView()
    private val eventFragment = EventView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(monthlyFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavMethod)



    }

    private fun replaceFragment(fragment: Fragment) {
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFrame, fragment)
            transaction.commit()
        }
    }

    private val bottomNavMethod =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            var bottomFragment: Fragment = monthlyFragment
            when (menuItem.itemId) {
                R.id.month -> bottomFragment = monthlyFragment
                R.id.week -> bottomFragment = weeklyFragment
                R.id.events -> bottomFragment = eventFragment
            }
            replaceFragment(bottomFragment)
            true
        }

}