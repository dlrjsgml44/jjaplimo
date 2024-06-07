package com.dlrjsgml.makeview.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.dlrjsgml.makeview.MainActivity
import com.dlrjsgml.makeview.R
import com.dlrjsgml.makeview.databinding.ActivityParentLoginBinding

class ParentLogin : AppCompatActivity() {
    private lateinit var binding: ActivityParentLoginBinding
    var EmptyEdit = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkButtonState()
        binding.mylogin.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.mylogin.setOnClickListener {
            val intent = Intent(this, ParentJoinOne::class.java)
            startActivity(intent)
            finish()
        }
        binding.backback.setOnClickListener {
            finish()
        }
        binding.loginDelete.setOnClickListener {
            binding.loginEdit.setText(null)
        }
        binding.loginDelete.setOnClickListener {
            binding.loginEdit.setText(null)
        }
        var nosee = false
        binding.loginEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.loginEdit.setBackgroundResource(R.drawable.edit_radius_on)
                binding.loginEdit.setHintTextColor(Color.parseColor("#F7D04F"))
            } else {
                binding.loginEdit.setBackgroundResource(R.drawable.edit_radius_off)
                binding.loginEdit.setHintTextColor(Color.GRAY)
            }
        }
        binding.pwEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.pwEdit.setBackgroundResource(R.drawable.edit_radius_on)
                binding.pwEdit.setHintTextColor(Color.parseColor("#F7D04F"))
            } else {
                binding.pwEdit.setBackgroundResource(R.drawable.edit_radius_off)
                binding.pwEdit.setHintTextColor(Color.GRAY)
            }
        }
        binding.pwHide.setOnClickListener {
            if (nosee) {
                nosee = !nosee
                binding.pwEdit.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.pwHide.setImageResource(R.drawable.eye)
            } else {
                nosee = !nosee
                binding.pwEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.pwHide.setImageResource(R.drawable.eye_crossed)
            }
        }

        binding.startGogo.setOnClickListener {
            if (EmptyEdit) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //dd
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //ddd
            }

            override fun afterTextChanged(s: Editable?) {
                checkButtonState()
            }
        }
        binding.loginEdit.addTextChangedListener(textWatcher)
        binding.pwEdit.addTextChangedListener(textWatcher)
    }

    fun checkButtonState() {
        val editText1Text = binding.loginEdit.text.toString()
        val editText2Text = binding.pwEdit.text.toString()

        if (editText1Text.isNotEmpty() && editText2Text.isNotEmpty()) {
            binding.startGogo.setBackgroundResource(R.drawable.radius)
            binding.startGogo.setTextColor(Color.BLACK)
            EmptyEdit = true
        } else {
            binding.startGogo.setBackgroundResource(R.drawable.yetradius)
            binding.startGogo.setTextColor(Color.GRAY)
            EmptyEdit = false
        }
    }
}