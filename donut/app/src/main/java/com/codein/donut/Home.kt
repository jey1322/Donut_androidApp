package com.codein.donut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codein.donut.adapter.NotaAdapter
import com.codein.donut.databinding.ActivityHomeBinding
import com.codein.donut.preferense.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.services.ApiClient
import com.codein.donut.toolbar.MyToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var adapter: NotaAdapter
    private var mNotas : MutableList<LoginResponse.Component> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        MyToolbar().mostrar(this, "Donut", false)

        initRecyclerView()
        obtenerMatriculas()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.homeb) {
            Toast.makeText(this, "cargando...", Toast.LENGTH_SHORT).show()
            finish()
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.perfilb)
        {
            startActivity(Intent(this, Perfil::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


    fun obtenerMatriculas()
    {
        val id = sessionManager.fetchlid()
        val pass = sessionManager.fetchlpass()
        val year = sessionManager.fetchlyear()

        apiClient.getApiService(this).login(LoginRequest(id = id.toString(), password = pass.toString(), year = year.toString()))
            .enqueue(object : Callback<LoginResponse>{

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    binding.pb.visibility = android.view.View.GONE
                    Toast.makeText(this@Home, "Error en conexion", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
/*
                    //obtener el numero de veces donde second_call no sea null
                    var espe = 0
                    for (i in response.body()?.components!!) {
                        if (i.second_call != null || i.second_call != "") {
                            espe++
                        }
                    }

                   var clases = 0
                    for (i in response.body()?.components!!) {
                        if (i.final_grade < "59.5" || i.second_call < "59.5" || i.partial_1 != "SATISFACTORIO") {
                            clases++
                        }
                    }


                    //obtener el numero de veces donde estado inicie con "Aprobado"
                    var aprob = 0
                    for (i in response.body()?.components!!) {
                        if (i.final_grade > "59.4" || i.second_call > "59.4" || i.partial_1 == "SATISFACTORIO") {
                            aprob++
                        }
                    }

                    sessionManager.saveClases(clases.toString())
                    sessionManager.saveAprob(aprob.toString())
                    sessionManager.saveEspe(espe.toString()) */

                    binding.pb.visibility = android.view.View.GONE
                    mNotas.clear()
                    mNotas.addAll(response.body()?.components!!)
                    adapter.notifyDataSetChanged()
                }
            })
    }

    fun initRecyclerView()
    {
        binding.rvNotas.layoutManager = LinearLayoutManager(this)
        adapter = mNotas?.let { NotaAdapter(this, it, R.layout.notas_list) }!!
        binding.rvNotas.adapter = adapter
    }
}