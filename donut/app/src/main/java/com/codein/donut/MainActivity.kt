package com.codein.donut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codein.donut.databinding.ActivityMainBinding
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.services.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()

        binding.btnIngresar.setOnClickListener {
                apiClient.getApiService(this).login(
                    LoginRequest(
                        id = "16-01447-0",
                        password = "NAFUWP",
                        year = "2021"
                    )
                )
                    .enqueue(object : Callback<LoginResponse>{

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(this@MainActivity, "Error al conectar", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                        ) {
                            val resp = response.body()
                            if (resp?.student?.name != null) {
                                Toast.makeText(this@MainActivity, "Bienvenido...", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                Toast.makeText(this@MainActivity, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
        }

    }
}