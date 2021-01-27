package com.example.app_description_apiary.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import java.text.SimpleDateFormat
import java.util.*


class DateDialog(
    private val context: Context
) {
    private lateinit var datePickerDialog: DatePickerDialog

    init {
        bindCalendar()
    }

    fun showCalendar() {
        datePickerDialog.show()
    }

    private fun bindCalendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePickerDialog = DatePickerDialog(context, { _, _, _, _ -> }, year, month, day)
    }


    fun setOnSelectedDate(onSuccess: (Date) -> Unit) {
        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.set(year, month, dayOfMonth)
            onSuccess.invoke(calendar.time)
        }
    }

}