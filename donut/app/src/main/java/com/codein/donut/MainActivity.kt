package com.codein.donut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.codein.donut.databinding.ActivityMainBinding
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.services.ApiClient
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

            binding.etCarnet.error = null
            binding.etPin.error = null
            binding.etAnio.error = null

            val id = binding.etCarnet.text.toString().trim()
            val pin = binding.etPin.text.toString().trim()
            var year = binding.etAnio.text.toString().trim()

            if(id.isEmpty())
            {
                Toast.makeText(this, "Ingrese su carnet", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (pin.isEmpty())
                {
                    Toast.makeText(this, "Ingrese su pin", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    if (year.isEmpty())
                    {
                        year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR).toString()
                        binding.etAnio.setText(year)
                    }
                    else{

                        binding.btnIngresar.isEnabled = false
                        binding.btnIngresar.background = getDrawable(R.drawable.buttonproc)
                        binding.btnIngresar.text = "solicitando datos..."


                        //recuerda que el erro de okhttp tal y tal cosa, intenta probar enviado los valores estaticos
                        apiClient.getApiService(this).login(LoginRequest(
                            id = id,
                            password = pin,
                            year = year
                        ))
                            .enqueue(object : Callback<LoginResponse>{

                                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                    Toast.makeText(this@MainActivity, "Error al conectar", Toast.LENGTH_SHORT).show()
                                    binding.btnIngresar.isEnabled = true
                                    binding.btnIngresar.background = getDrawable(R.drawable.btn)
                                    binding.btnIngresar.text = "Ver notas"
                                }

                                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                                ) {
                                    val resp = response.body()
                                    if (resp?.student?.name != null) {
                                        Toast.makeText(this@MainActivity, "Bienvenido...", Toast.LENGTH_SHORT).show()
                                    }
                                    else {
                                        binding.etAnio.error = "este es el año a buscar?"
                                        //pintar el contenido de etpass
                                        binding.etPin.error = "PIN es correcto?"
                                        binding.etPin.inputType = InputType.TYPE_CLASS_TEXT
                                        binding.etCarnet.error = "Carnet es correcto?"
                                        Toast.makeText(this@MainActivity, "Error: Algo salió mal", Toast.LENGTH_SHORT).show()
                                        binding.btnIngresar.isEnabled = true
                                        binding.btnIngresar.background = getDrawable(R.drawable.btn)
                                        binding.btnIngresar.text = "Ver notas"
                                    }
                                }

                            })
                    }
                }
            }


        }

    }
}