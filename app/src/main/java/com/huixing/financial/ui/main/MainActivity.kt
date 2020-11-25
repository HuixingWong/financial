package com.huixing.financial.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.huixing.financial.R
import com.huixing.financial.base.DataBindingActivity
import com.huixing.financial.base.viewmodel.SharedViewModel
import com.huixing.financial.ui.search.SearchFundActivity
import com.huixing.financial.utils.appViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : DataBindingActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val sharedViewModel: SharedViewModel by appViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
    }


    private fun setupActionBar() {
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        menu.findItem(R.id.search).setOnMenuItemClickListener {
            Intent(this, SearchFundActivity::class.java).apply {
                startActivity(this)
            }
            return@setOnMenuItemClickListener false
        }
        sharedViewModel.showSearch.observe(this, {
            menu.findItem(R.id.search).isVisible = it
        })
        return true
    }


    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

}