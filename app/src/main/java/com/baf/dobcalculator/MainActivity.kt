package com.baf.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById<TextView>(R.id.tvAgeInMunutes)
        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)
        val dPD= DatePickerDialog(this,
                  { _, selectedYear, selectedMonthOfYear, selectedDayOfMonth ->
                      val selectedMonthOfYearPlus = selectedMonthOfYear + 1
                      val selectedDate = "$selectedDayOfMonth/$selectedMonthOfYearPlus/$selectedYear"
                      tvSelectedDate?.text = selectedDate
                      val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                      val theDate = sdf.parse(selectedDate)
                      theDate?.let{
                          val selectedDateInMinutes = theDate.time / 60000
                          val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                          currentDate?.let {
                              val currentDateInMinutes =currentDate.time / 60000
                              val ageInMinutes = currentDateInMinutes - selectedDateInMinutes
                              tvAgeInMinutes?.text = ageInMinutes.toString()
                          }
                      }
                   },
            year,
            month,
            day)
        dPD.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dPD.show()
    }
}