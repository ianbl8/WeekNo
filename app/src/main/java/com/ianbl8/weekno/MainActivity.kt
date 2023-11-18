package com.ianbl8.weekno

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import java.time.temporal.WeekFields
import java.util.Locale

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private val today: LocalDate = LocalDate.now()
    private var date: LocalDate = today
    private val dateFormat: String = "EEE d MMM yyyy"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val btnToday: Button = findViewById(R.id.btnToday)
        val btnSelect: Button = findViewById(R.id.btnSelect)

        displayWeekNumber(date)

        btnToday.setOnClickListener {
            date = today
            displayWeekNumber(date)
        }

        btnSelect.setOnClickListener {
            DatePickerDialog(this, this, date.year, date.monthValue - 1, date.dayOfMonth).show()
        }
     }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = LocalDate.of(year, month + 1, dayOfMonth)
        displayWeekNumber(date)
    }

    private fun displayWeekNumber(date: LocalDate) {
        val dateSelected: TextView = findViewById(R.id.dateSelected)
        val weekNumber: TextView = findViewById(R.id.weekNumber)
        val weekOfYear = date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
        dateSelected.setText(date.format(ofPattern(dateFormat)))
        weekNumber.setText("${weekOfYear}")
    }

}