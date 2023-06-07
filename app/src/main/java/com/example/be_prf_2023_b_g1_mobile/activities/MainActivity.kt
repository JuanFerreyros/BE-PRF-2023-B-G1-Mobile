package com.example.be_prf_2023_b_g1_mobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.be_prf_2023_b_g1_mobile.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navHostFragment: NavHostFragment
    lateinit var navigationView: NavigationView
    private lateinit var txtToolbar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val imgMenu = findViewById<ImageView>(R.id.open_drawer)

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.nav_view)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        txtToolbar = findViewById(R.id.toolbarTitle)

        imgMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        setUpDrawerLayout()
    }

    private fun setUpDrawerLayout() {
        val navController = navHostFragment.navController

        navigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
            txtToolbar.text = getTitleFromDestination(destination)
        }
    }

    private fun getTitleFromDestination(destination: NavDestination): String {
        val menuItem = navigationView.menu.findItem(destination.id)
        return when {
            menuItem != null -> menuItem.title.toString()
            destination.label.toString() == "fragment_station_details" ||
                    destination.label.toString() == "fragment_request_details" -> getString(R.string.details)
            else -> destination.label.toString()
        }
    }
}