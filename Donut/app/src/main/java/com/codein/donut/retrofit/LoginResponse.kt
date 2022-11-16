package com.codein.donut.retrofit

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("student")
    val student: Student,

    @SerializedName("components")
    val components: List<Component>,
)
{
    class Student{
        val year: String = ""
        val faculty: String = ""
        val career : String = ""
        val name: String = ""
        val id: String = ""
        val cycle_year: String = ""
        val average: String = ""
    }

    class Component
    {
        val name : String = ""
        val partial_1 : String = ""
        val partial_2 : String = ""
        val partial_3 : String = ""
        val final_grade : String = ""
        val second_call : String = ""
    }
}
