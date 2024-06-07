package com.dlrjsgml.makeview.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dlrjsgml.makeview.databinding.ActivityStartBinding

class Start : AppCompatActivity() {
    private lateinit var binding : ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.startGogo.setOnClickListener{
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
        }
    }
}