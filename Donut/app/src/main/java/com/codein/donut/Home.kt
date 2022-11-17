package com.codein.donut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.codein.donut.adapter.NotasAdapter
import com.codein.donut.databinding.ActivityHomeBinding
import com.codein.donut.preferens.SessionManager
import com.codein.donut.retrofit.LoginRequest
import com.codein.donut.retrofit.LoginResponse
import com.codein.donut.retrofit.servicios.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: NotasAdapter
    private var mNotas : MutableList<LoginResponse.Component> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        initRecycler()
        getNotas()

    }

    private fun getNotas()
    {
        val id = sessionManager.fetchId()
        val pass = sessionManager.fetchlpass()
        val year = sessionManager.fetchlyear()

        apiClient.getApiService().login(LoginRequest(id = id.toString(), password = pass.toString(), year = year.toString()))
            .enqueue(object : Callback<LoginResponse>{

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    //mostrar el log
                    Log.d("ErrorF", t.message.toString())
                    Toast.makeText(this@Home, "Error: conexion fallida", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>
                ) {
                    val resp = response.body()
                    if (resp?.student?.id != null)
                    {
                        mNotas.clear()
                        mNotas.addAll(response.body()?.components!!)
                        adapter.notifyDataSetChanged()
                    }
                    else
                    {
                        Log.d("ErrorR", response.errorBody().toString())
                        Toast.makeText(this@Home, "Error: algo salio mal", Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }

    private fun initRecycler()
    {
        val listrv = binding.recycler
        listrv.layoutManager = LinearLayoutManager(this)
        adapter = mNotas?.let { NotasAdapter(this, it, R.layout.notas_list) }!!
        listrv.adapter = adapter
    }

}