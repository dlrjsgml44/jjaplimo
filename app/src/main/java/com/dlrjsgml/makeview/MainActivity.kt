package com.dlrjsgml.makeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.dlrjsgml.makeview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragments = listOf(
        HomeFragment(),
        BookmarkFragment(),
        MyFragment()
    )

    // animation 방향 계산을 위해 가장 마지막 위치 값 저장
    private var recentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 첫 화면 초기화 HomeFragment
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, fragments[0])
            .commit()

        // BottomNavigation 아이템 선택 콜백
        binding.bottomNavigationView.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.fragmentHome -> {
                    transaction.setCustomAnimations(
                        R.anim.anim_slide_in_from_left_fade_in,
                        R.anim.fadeout
                    )
                    transaction.replace(binding.mainContainer.id, fragments[0])
                    transaction.commit()
                    recentPosition = 0
                    return@setOnItemSelectedListener true
                }
                R.id.fragmentBookmark -> {

                    // 두번째 보다 작은 왼쪽에서 이동해 올 경우 오른쪽에서 화면이 나타남
                    // 반대의 경우 왼쪽에서 나타남
                    if (recentPosition < 1) {
                        transaction.setCustomAnimations(
                            R.anim.anim_slide_in_from_right_fade_in,
                            R.anim.fadeout
                        )
                    } else {
                        transaction.setCustomAnimations(
                            R.anim.anim_slide_in_from_left_fade_in,
                            R.anim.fadeout
                        )
                    }
                    transaction.replace(binding.mainContainer.id, fragments[1])
                    transaction.commit()
                    recentPosition = 1
                    return@setOnItemSelectedListener true
                }
                R.id.fragmentMy -> {
                    transaction.setCustomAnimations(
                        R.anim.anim_slide_in_from_right_fade_in,
                        R.anim.fadeout
                    )
                    transaction.replace(binding.mainContainer.id, fragments[2])
                    transaction.commit()
                    recentPosition = 2
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }

    }
}