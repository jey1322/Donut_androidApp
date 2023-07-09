package com.codein.donut.retrofit

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("student")
    val student: Student,
    @SerializedName("university")
    val university: University,
    @SerializedName("cycles")
    val cycles : List<Cycle>,
)
{
    class Student{
        @SerializedName("name")
        val name: String = ""
        @SerializedName("id")
        val id : String = ""
        @SerializedName("grade")
        val grade : String = ""
        @SerializedName("average")
        val average : String = ""
    }
    class University{
        @SerializedName("full_name")
        val full_name : String = ""
        @SerializedName("name")
        val name : String = ""
        @SerializedName("academic_year")
        val academic_year : String = ""
        @SerializedName("faculty")
        val faculty : String = ""
        @SerializedName("career")
        val career : String = ""
    }
    class Cycle{
        @SerializedName("name")
        val name : String = ""
        @SerializedName("components")
        val components : List<Component> = emptyList()
    }
    class Component{
        @SerializedName("name")
        val name : String = ""
        @SerializedName("partial_1")
        val partial_1 : String = ""
        @SerializedName("partial_2")
        val partial_2 : String = ""
        @SerializedName("partial_3")
        val partial_3 : String = ""
        @SerializedName("final")
        val final : String = ""
        @SerializedName("second_call")
        val second_call : String = ""
    }
}