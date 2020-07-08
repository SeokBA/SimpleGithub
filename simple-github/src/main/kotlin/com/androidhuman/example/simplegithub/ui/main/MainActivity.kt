package com.androidhuman.example.simplegithub.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidhuman.example.simplegithub.R
import com.androidhuman.example.simplegithub.ui.search.SearchActivity
// 코틀린 안드로이드 익스텐션에서 activity_main 레이아웃을 사용합니다.
import kotlinx.android.synthetic.main.activity_main.*
// import문에 startActivity 함수를 추가합니다.
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 인스턴스 선언 없이 뷰 ID를 사용하여 인스턴스에 접근합니다.
        btnActivityMainSearch.setOnClickListener {
            // 호출할 액티비티만 명시합니다.
            startActivity<SearchActivity>()
        }
    }
}
