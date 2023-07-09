package com.codein.donut

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.codein.donut.databinding.ActivityMainBinding
import com.codein.donut.preferense.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.services.ApiClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Donut)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        binding.terminos.setOnClickListener {
            Uri.parse("http://cb-develop.tk/terms").let { uri ->
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
        binding.btnIngresar.setOnClickListener {
            val id = binding.etCarnet.text.toString().trim()
            val pin = binding.etPin.text.toString().trim()
            var year = binding.etAnio.text.toString().trim()
            if(id.isEmpty()) {
                Toast.makeText(this, "Ingrese su carnet", Toast.LENGTH_SHORT).show()
            } else if (pin.isEmpty()) {
                Toast.makeText(this, "Ingrese su pin", Toast.LENGTH_SHORT).show()
            } else if (year.isEmpty()) {
                year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR).toString()
                binding.etAnio.setText(year)
            } else{
                binding.btnIngresar.isEnabled = false
                binding.btnIngresar.background = getDrawable(R.drawable.buttonproc)
                binding.btnIngresar.text = "solicitando datos..."
                apiClient.getApiService(this).login(LoginRequest(id = id, password = pin, year = year)).enqueue(object : Callback<LoginResponse>{
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Snackbar.make(binding.root, "Error en conexión, inténtelo de nuevo", Snackbar.LENGTH_LONG).show()
                        binding.btnIngresar.isEnabled = true
                        binding.btnIngresar.background = getDrawable(R.drawable.btn)
                        binding.btnIngresar.text = "Ver notas"
                    }
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(response.isSuccessful){
                            if (response.body() != null) {
                                sessionManager.savelid(id)
                                sessionManager.savelpass(pin)
                                sessionManager.savelyear(year)
                                Toast.makeText(this@MainActivity, "Bienvenido...", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, Home::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Snackbar.make(binding.root, "Si tus credenciales están correctos, lo mas probable es que no tengas notas en este año", Snackbar.LENGTH_LONG).show()
                                binding.etAnio.error = "este es el año a buscar?"
                                binding.etPin.error = "PIN es correcto?"
                                binding.etPin.inputType = InputType.TYPE_CLASS_TEXT
                                binding.etCarnet.error = "Carnet es correcto?"
                                Toast.makeText(this@MainActivity, "Error: Algo salió mal", Toast.LENGTH_SHORT).show()
                                binding.btnIngresar.isEnabled = true
                                binding.btnIngresar.background = getDrawable(R.drawable.btn)
                                binding.btnIngresar.text = "Ver notas"
                            }
                        }else{
                            Snackbar.make(binding.root, "Error: Algo salió mal", Snackbar.LENGTH_SHORT).show()
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