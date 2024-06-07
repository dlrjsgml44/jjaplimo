package com.dlrjsgml.makeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dlrjsgml.makeview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigationView()


        MyApplication.prefs.setBoolean("isLogined", true)
        val isLogind  = MyApplication.prefs.getBoolean("isLogined",false)
        Log.d(TAG, "$isLogind")
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.fragmentHome
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    fun setBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.fragmentHome -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
                    true
                }
                R.id.fragmentBookmark -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, BookmarkFragment()).commit()
                    true
                }
                R.id.fragmentMy -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container, MyFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}
