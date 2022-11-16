package com.codein.donut.preferens

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences("Donut", Context.MODE_PRIVATE)

    companion object {
        const val YEAR = "year"
        const val FACULTY = "faculty"
        const val CAREER = "career"
        const val NAME = "name"
        const val ID = "id"
        const val CYCLE_YEAR = "cycle_year"
        const val AVERAGE = "average"
    }

    fun saveYear(year: String?) {
        val editor = prefs.edit()
        editor.putString(YEAR, year)
        editor.apply()
    }
    fun fetchYear(): String? {
        return prefs.getString(YEAR, null)
    }
    fun deleteYear() {
        val editor = prefs.edit()
        editor.remove(YEAR)
        editor.apply()
    }
}