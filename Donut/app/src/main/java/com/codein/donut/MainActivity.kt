package com.codein.donut

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import com.codein.donut.databinding.ActivityMainBinding
import com.codein.donut.preferens.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.servicios.ApiClient
import org.w3c.dom.Text
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

            binding.etcarnet.error = null
            binding.etpin.error = null
            binding.etanio.error = null


            val id = binding.etcarnet.text.toString()
            val pass = binding.etpin.text.toString()
            var year = binding.etanio.text.toString()

            if (id.isEmpty())
            {
                Toast.makeText(this, "Ingrese su carnet", Toast.LENGTH_SHORT).show()
            }
            else{
                if (pass.isEmpty())
                {
                    Toast.makeText(this, "Ingrese su PIN", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    if (year.isEmpty())
                    {
                        year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR).toString()
                        binding.etanio.setText(year)
                    }

                    // conexion a api
                    binding.btnlog.isEnabled = false
                    binding.btnlog.background = resources.getDrawable(R.drawable.buttonproc)
                    binding.btnlog.text = "Solicitando datos"


                    apiClient.getApiService().login(LoginRequest(id = id, password = pass, year = year))
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
                                    //mostrar en log el id del estudiante
                                    Log.d("idestudent", "onResponse: ${resp.student.id}")
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
                                    binding.etanio.error = "este es el año a buscar?"
                                    //pintar el contenido de etpass
                                    binding.etpin.error = "PIN es correcto?"
                                    binding.etpin.inputType = InputType.TYPE_CLASS_TEXT
                                    binding.etcarnet.error = "Carnet es correcto?"
                                    Toast.makeText(this@MainActivity, "Error: Algo salió mal", Toast.LENGTH_SHORT).show()
                                    binding.btnlog.isEnabled = true
                                    binding.btnlog.background = resources.getDrawable(R.drawable.button)
                                    binding.btnlog.text = "Iniciar sesión"
                                }
                            }

                        })
                }
            }


        }
    }
}