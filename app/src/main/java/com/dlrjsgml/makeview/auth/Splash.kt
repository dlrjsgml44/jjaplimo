package com.dlrjsgml.makeview.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.dlrjsgml.makeview.MainActivity
import com.dlrjsgml.makeview.MyApplication
import com.dlrjsgml.makeview.R

class Splash : AppCompatActivity() {
    val TAG: String = "로그"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val isLogind  = MyApplication.prefs.getBoolean("isLogined",false)
        Log.d(TAG, "$isLogind");
        if (isLogind){
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }, 2000) // 시간 2초 이후 실행
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, Start::class.java)
                startActivity(intent)
                finish()

            }, 2000) // 시간 2초 이후 실행
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


    }

    private fun isLoign(){

    }
}