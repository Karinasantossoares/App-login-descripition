package com.example.app_description_apiary.extensions

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.setOnClickLeft(onClick: () -> Unit) {
    setOnTouchListener { _, event ->
        if (event.x < this.totalPaddingLeft && event.action == MotionEvent.ACTION_UP) {
            onClick.invoke()
            return@setOnTouchListener true
        }
        return@setOnTouchListener false
    }
}