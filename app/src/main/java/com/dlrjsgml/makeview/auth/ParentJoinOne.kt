package com.dlrjsgml.makeview.auth

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.dlrjsgml.makeview.R
import com.dlrjsgml.makeview.databinding.ActivityParentJoinOneBinding


class ParentJoinOne : AppCompatActivity() {

    private lateinit var binding: ActivityParentJoinOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentJoinOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.write1.requestFocus()
        WindowCompat.getInsetsController(window, binding.write1)
            .show(WindowInsetsCompat.Type.ime())
        binding.startGogo.setOnClickListener {
            if (isValidInput()) {
                val intent = Intent(this, ParentJoinTwo::class.java)
                startActivity(intent)
            }
        }

        binding.mylogin.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.mylogin.setOnClickListener {
            val intent = Intent(this, ParentLogin::class.java)
            startActivity(intent)
            finish()
        }
        binding.backback.setOnClickListener {
            finish()
        }

        //1
        binding.write1.setOnFocusChangeListener { _, hasFocus ->
            handleStartGogo()
            if (hasFocus) {
                binding.write1.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                if (binding.write1.text.isEmpty()) {
                    binding.write1.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
        binding.write1.addTextChangedListener {
            handleStartGogo()
            if (binding.write1.text.isNotEmpty()) {
                binding.write2.requestFocus()
                handleStartGogo()
                binding.write1.setBackgroundResource(R.drawable.edit_radius_on)
            } else {

                binding.write1.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }
        //--------
        //2
        binding.write2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.write2.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                if (binding.write2.text.isEmpty()) {
                    binding.write2.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
        binding.write2.addTextChangedListener {
            handleStartGogo()
            if (binding.write2.text.isNotEmpty()) {
                binding.write3.requestFocus()
                handleStartGogo()
                binding.write2.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                binding.write1.requestFocus()
                binding.write2.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }
        //3
        binding.write3.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.write3.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                if (binding.write3.text.isEmpty()) {
                    binding.write3.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
        binding.write3.addTextChangedListener {
            handleStartGogo()
            if (binding.write3.text.isNotEmpty()) {
                binding.write4.requestFocus()
                handleStartGogo()
                binding.write3.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                binding.write2.requestFocus()
                binding.write3.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }

        //4
        binding.write4.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.write4.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                if (binding.write4.text.isEmpty()) {
                    binding.write4.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
        binding.write4.addTextChangedListener {
            handleStartGogo()
            if (binding.write4.text.isNotEmpty()) {
                binding.write5.requestFocus()
                handleStartGogo()
                binding.write4.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                binding.write3.requestFocus()
                binding.write4.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }

        //5
        binding.write5.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.write5.setBackgroundResource(R.drawable.edit_radius_on)

            } else {
                if (binding.write5.text.isEmpty()) {
                    binding.write5.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
//        binding.write5.addTextChangedListener {
//            handleStartGogo()
//            if (binding.write5.text.isNotEmpty()) {
//                binding.write6.requestFocus()
//                handleStartGogo()
//                binding.write5.setBackgroundResource(R.drawable.edit_radius_on)
//            } else {
//                binding.write4.requestFocus()
//                binding.write5.setBackgroundResource(R.drawable.edit_radius_off)
//            }
//        }
        with(binding) {
            write5.addTextChangedListener(
                btnLeft = write4,
                btnRight = write6
            )
        }
        //6
        binding.write6.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.write6.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                if (binding.write6.text.isEmpty()) {
                    binding.write6.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        }
        binding.write6.addTextChangedListener(
            onTextChanged = { _, _, _, _ ->
                handleStartGogo()
                if (binding.write6.text.isNotEmpty()) {
                    binding.write6.setBackgroundResource(R.drawable.edit_radius_on)
                    handleStartGogo()
                } else {
                    binding.write5.requestFocus()
                    binding.write6.setBackgroundResource(R.drawable.edit_radius_off)
                }
            }
        ) {
            if (binding.write6.text.isNotEmpty()) {
                binding.write6.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                binding.write6.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }
    }

    private fun handleStartGogo() {
        if (isValidInput()) {
            binding.startGogo.setBackgroundResource(R.drawable.radius)
            binding.startGogo.setTextColor(Color.BLACK)
            WindowCompat.getInsetsController(window, binding.write1)
                .hide(WindowInsetsCompat.Type.ime())
        } else {
            binding.startGogo.setBackgroundResource(R.drawable.yetradius)
            binding.startGogo.setTextColor(Color.GRAY)
        }
    }

    private fun EditText.addTextChangedListener(
        btnLeft: EditText,
        btnRight: EditText
    ) {
        this.addTextChangedListener {
            handleStartGogo()
            if (this.text.isNotEmpty()) {
                btnRight.requestFocus()
                handleStartGogo()
                this.setBackgroundResource(R.drawable.edit_radius_on)
            } else {
                btnLeft.requestFocus()
                this.setBackgroundResource(R.drawable.edit_radius_off)
            }
        }
    }

    private fun isValidInput(): Boolean {
        return binding.write1.text.isNotEmpty() && binding.write2.text.isNotEmpty() && binding.write3.text.isNotEmpty() && binding.write4.text.isNotEmpty() && binding.write5.text.isNotEmpty() && binding.write6.text.isNotEmpty()
    }
}