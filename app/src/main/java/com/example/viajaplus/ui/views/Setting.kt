package com.example.viajaplus.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.viajaplus.R

class Setting : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}