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

    fun saveFaculty(faculty: String?) {
        val editor = prefs.edit()
        editor.putString(FACULTY, faculty)
        editor.apply()
    }
    fun fetchFaculty(): String? {
        return prefs.getString(FACULTY, null)
    }
    fun deleteFaculty() {
        val editor = prefs.edit()
        editor.remove(FACULTY)
        editor.apply()
    }

    fun saveCareer(career: String?) {
        val editor = prefs.edit()
        editor.putString(CAREER, career)
        editor.apply()
    }
    fun fetchCareer(): String? {
        return prefs.getString(CAREER, null)
    }
    fun deleteCareer() {
        val editor = prefs.edit()
        editor.remove(CAREER)
        editor.apply()
    }

    fun saveName(name: String?) {
        val editor = prefs.edit()
        editor.putString(NAME, name)
        editor.apply()
    }
    fun fetchName(): String? {
        return prefs.getString(NAME, null)
    }
    fun deleteName() {
        val editor = prefs.edit()
        editor.remove(NAME)
        editor.apply()
    }

    fun saveId(id: String?) {
        val editor = prefs.edit()
        editor.putString(ID, id)
        editor.apply()
    }
    fun fetchId(): String? {
        return prefs.getString(ID, null)
    }
    fun deleteId() {
        val editor = prefs.edit()
        editor.remove(ID)
        editor.apply()
    }

    fun saveCycleYear(cycle_year: String?) {
        val editor = prefs.edit()
        editor.putString(CYCLE_YEAR, cycle_year)
        editor.apply()
    }
    fun fetchCycleYear(): String? {
        return prefs.getString(CYCLE_YEAR, null)
    }
    fun deleteCycleYear() {
        val editor = prefs.edit()
        editor.remove(CYCLE_YEAR)
        editor.apply()
    }

    fun saveAverage(average: String?) {
        val editor = prefs.edit()
        editor.putString(AVERAGE, average)
        editor.apply()
    }
    fun fetchAverage(): String? {
        return prefs.getString(AVERAGE, null)
    }
    fun deleteAverage() {
        val editor = prefs.edit()
        editor.remove(AVERAGE)
        editor.apply()
    }

}