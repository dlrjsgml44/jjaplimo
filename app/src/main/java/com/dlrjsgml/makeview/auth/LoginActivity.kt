package com.dlrjsgml.makeview.auth

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.dlrjsgml.makeview.MainActivity
import com.dlrjsgml.makeview.R
import com.dlrjsgml.makeview.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var isHide = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.backback.setOnClickListener {
            finish()
        }

        binding.pwHide.setOnClickListener {
            if (isHide) {
                binding.pwEdit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.pwHide.setImageResource(R.drawable.eye)
            } else {
                binding.pwEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.pwHide.setImageResource(R.drawable.eye_crossed)
            }
            isHide = !isHide
        }
        binding.startGogo.setOnClickListener {
            if (isValidInput()) {
                val prefs = getSharedPreferences("my_prefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putBoolean("isLoggedIn", true)

                // 변경 사항 적용
                editor.apply() // 비동기
                val i = Intent(this, MainActivity::class.java)
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
            }
        }
        binding.loginEdit.setOnFocusChangeListener { v, hasFocus ->
            val background = if (hasFocus) R.drawable.edit_radius_on else R.drawable.edit_radius_off
            val hintTextColor = if (hasFocus) resources.getColor(R.color.alimoyellow, theme) else Color.GRAY
            binding.loginEdit.setBackgroundResource(background)
            binding.loginEdit.setHintTextColor(hintTextColor)
        }
        binding.pwEdit.setOnFocusChangeListener { _, hasFocus ->
            val background = if (hasFocus) R.drawable.edit_radius_on else R.drawable.edit_radius_off
            val hintTextColor = if (hasFocus) resources.getColor(R.color.alimoyellow, theme) else Color.GRAY
            binding.pwEdit.setBackgroundResource(background)
            binding.pwEdit.setHintTextColor(hintTextColor)
        }

        binding.loginEdit.addTextChangedListener {
            checkButtonState()
        }
        binding.pwEdit.addTextChangedListener {
            checkButtonState()
        }
    }

    private fun checkButtonState() {
        val isValidInput = isValidInput()
        val background = if (isValidInput) R.drawable.radius else R.drawable.yetradius
        val textColor = if (isValidInput) Color.BLACK else Color.GRAY
        val text = if (isValidInput) "다음" else "로그인"

        with(binding.startGogo) {
            isEnabled = isValidInput
            setBackgroundResource(background)
            setTextColor(textColor)
            this.text = text
        }
    }

    private fun isValidInput() =
        binding.loginEdit.text.isNotEmpty() && binding.pwEdit.text.toString().isNotEmpty()
}