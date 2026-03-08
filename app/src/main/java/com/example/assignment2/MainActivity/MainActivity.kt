package com.example.assignment2

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BroadcastFragment())
                .commit()
        }

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_broadcast -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, BroadcastFragment())
                        .commit()
                }

                R.id.nav_image -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ImageScaleFragment())
                        .commit()
                }

                R.id.nav_video -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, VideoFragment())
                        .commit()
                }

                R.id.nav_audio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, AudioFragment())
                        .commit()
                }
            }

            drawerLayout.closeDrawers()
            true
        }
    }
}