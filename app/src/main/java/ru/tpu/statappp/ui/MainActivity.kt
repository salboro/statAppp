package ru.tpu.statappp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.tpu.statappp.R
import ru.tpu.statappp.ui.feed.FeedFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, FeedFragment())
                .commit()
        }
    }
}