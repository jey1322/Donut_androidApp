package com.codein.donut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codein.donut.adapter.NotaAdapter
import com.codein.donut.databinding.ActivityHomeBinding
import com.codein.donut.preferense.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.services.ApiClient
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

        initRecyclerView()
        obtenerMatriculas()

    }
    fun obtenerMatriculas()
    {
        val id = sessionManager.fetchlid()
        val pass = sessionManager.fetchlpass()
        val year = sessionManager.fetchlyear()

        apiClient.getApiService(this).login(LoginRequest(id = id.toString(), password = pass.toString(), year = year.toString()))
            .enqueue(object : Callback<LoginResponse>{

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@Home, "Error en conexion", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
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