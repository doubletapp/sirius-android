package com.doubletapp.sirius.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.bottomnavigation.LabelVisibilityMode
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import com.doubletapp.sirius.R
import com.doubletapp.sirius.base.BaseActivity
import com.doubletapp.sirius.extensions.showFragment
import com.doubletapp.sirius.presentation.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(
                intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TOP
                )
            )
        }
    }

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val mainActivityAdapter = MainActivityAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_main)
        mainBottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        mainBottomNavigation.setOnNavigationItemSelectedListener(this)
        mainViewPager.adapter = mainActivityAdapter
        mainViewModel.getCourses()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean =
            when (p0.itemId) {
                R.id.bottom_navigation_profile_item -> {
                    mainViewPager.currentItem = 0
                    true
                }
                R.id.bottom_navigation_news_item -> {
                    mainViewPager.currentItem = 1
                    true
                }
                R.id.bottom_navigation_traces_item -> {
                    mainViewPager.currentItem = 2
                    true
                }
                R.id.bottom_navigation_settings_item -> {
                    mainViewPager.currentItem = 3
                    true
                }
                else -> false
            }
}
