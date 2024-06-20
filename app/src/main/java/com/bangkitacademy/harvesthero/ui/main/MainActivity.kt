package com.bangkitacademy.harvesthero.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.bangkitacademy.harvesthero.R
import com.bangkitacademy.harvesthero.databinding.ActivityMainBinding
import com.bangkitacademy.harvesthero.ui.SectionsPagerAdapter
import com.bangkitacademy.harvesthero.ui.add.AddActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setViewPager()
        setupAction()
    }

    private fun setupAction() {
        binding.apply {
            floatingBtnMain.setOnClickListener{
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setViewPager() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewpagerMain
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabsMain
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
            tab.icon = ContextCompat.getDrawable(this, TAB_ICON[position])
        }.attach()
    }



    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.home,
            R.string.my_plants,
            R.string.plantpedia,
        )

        @DrawableRes
        private val TAB_ICON = intArrayOf(
            R.drawable.home,
            R.drawable.leaf,
            R.drawable.book
        )
    }
}