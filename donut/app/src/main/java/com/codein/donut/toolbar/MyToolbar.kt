package com.codein.donut.toolbar

import androidx.appcompat.app.AppCompatActivity
import com.codein.donut.R

class MyToolbar {
    fun mostrar(activities: AppCompatActivity, title: String, upButton: Boolean) {
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}