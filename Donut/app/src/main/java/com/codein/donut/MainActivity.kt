package com.codein.donut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codein.donut.databinding.ActivityMainBinding
import com.codein.donut.preferens.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.servicios.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Donut)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient= ApiClient()
        sessionManager= SessionManager(this)

        binding.btnlog.setOnClickListener {
            binding.btnlog.isEnabled = false
            binding.btnlog.text = "Solicitando datos"

            apiClient.getApiService().login(LoginRequest(id = "16-01447-0", password = "NAFUWP", year = "2021"))
                .enqueue(object : Callback<LoginResponse>{

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error en conexión", Toast.LENGTH_SHORT).show()
                        binding.btnlog.isEnabled = true
                        binding.btnlog.text = "Iniciar sesión"
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                    ) {
                        val resp = response.body()
                        //si la respuesta es exitosa

                        if (resp?.student?.id != null)
                        {
                            Toast.makeText(this@MainActivity, "Bienvenido estudiante...", Toast.LENGTH_SHORT).show()
                            sessionManager.saveYear(resp.student.year)
                            sessionManager.saveAverage(resp.student.average)
                            sessionManager.saveId(resp.student.id)
                            sessionManager.saveName(resp.student.name)
                            sessionManager.saveCareer(resp.student.career)
                            sessionManager.saveCycleYear(resp.student.cycle_year)
                            sessionManager.saveFaculty(resp.student.faculty)

                            startActivity(Intent(this@MainActivity, Home::class.java))
                            finish()

                        }
                        else
                        {
                            Log.e("MainActivity", "onResponse: ${response.errorBody()}")
                            Toast.makeText(this@MainActivity, "Error: Algo salió mal", Toast.LENGTH_SHORT).show()
                            binding.btnlog.isEnabled = true
                            binding.btnlog.text = "Iniciar sesión"
                        }
                    }

                })
        }
    }
}