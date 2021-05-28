package com.meruj.picturesandweather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.meruj.picturesandweather.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.picture_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var selectedFragment: Fragment = PicturesFragment()
        supportActionBar?.title = resources.getString(R.string.pictures)
        bottom_navigation_bar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_pictures -> {
                    selectedFragment = PicturesFragment()
                    supportActionBar?.title = resources.getString(R.string.pictures)
                }
                R.id.nav_accaunt -> {
                    selectedFragment = SignInFragment()
                    supportActionBar?.title = resources.getString(R.string.signIn)
                }
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment
            ).commit()
            true
        }
        supportFragmentManager.beginTransaction().add(
            R.id.fragment_container,
            selectedFragment
        ).commit()
    }

}