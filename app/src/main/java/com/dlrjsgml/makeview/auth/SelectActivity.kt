package com.dlrjsgml.makeview.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.dlrjsgml.makeview.R
import com.dlrjsgml.makeview.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding

    private var isStudent = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.mylogin.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.good.isVisible = false
        changeImage()
        binding.backgogo.setOnClickListener {
            finish()
        }
        binding.studentCover.setOnClickListener {
            isStudent = true
            changeImage()
        }
        binding.parentCover.setOnClickListener {
            isStudent = false
            changeImage()
        }
        binding.mylogin.setOnClickListener {
            val intent = Intent(this, ParentLogin::class.java)
            startActivity(intent)
        }
        binding.goLogin.setOnClickListener {
            if (isStudent) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, ParentJoinOne::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeImage() {
        if (isStudent) {
            binding.studentCover.setBackgroundResource(R.drawable.selectback_on)
            binding.parentCover.setBackgroundResource(R.drawable.selectback)
            binding.stud.setImageResource(R.drawable.studenton)
            binding.pare.setImageResource(R.drawable.parentoff)
            binding.goLogin.text = "도담도담 로그인"
            binding.good.isVisible = false
            binding.stuTxt.setTextColor(Color.BLACK)
            binding.pa.setTextColor(Color.GRAY)
        } else {
            binding.parentCover.setBackgroundResource(R.drawable.selectback_on)
            binding.studentCover.setBackgroundResource(R.drawable.selectback)
            binding.stud.setImageResource(R.drawable.studentoff)
            binding.pare.setImageResource(R.drawable.parent_on)
            binding.goLogin.text = "회원가입"
            binding.good.isVisible = true
            binding.pa.setTextColor(Color.BLACK)
            binding.stuTxt.setTextColor(Color.GRAY)
        }
    }
}