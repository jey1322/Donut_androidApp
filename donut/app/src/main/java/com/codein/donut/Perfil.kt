package com.codein.donut

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codein.donut.databinding.ActivityPerfilBinding
import com.codein.donut.preferense.SessionManager
import com.codein.donut.toolbar.MyToolbar

class Perfil : AppCompatActivity() {
    private lateinit var binding : ActivityPerfilBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        MyToolbar().mostrar(this, "Perfil", true)

        binding.tvnombre.text = sessionManager.fetchName()
        binding.tvcarnet.text = sessionManager.fetchId()
        binding.tvfacultad.text = sessionManager.fetchFaculty()
        binding.tvcarrera.text = sessionManager.fetchCareer()
        binding.tvanio.text = sessionManager.fetchYear()
        binding.tvgrado.text = sessionManager.fetchCycleYear()
        binding.tvindice.text = sessionManager.fetchAverage()

        val clases = sessionManager.fetchClases()
        val aprob = sessionManager.fetchAprob()
        val espe = sessionManager.fetchEspe()
        val curso = sessionManager.fetchCurso()
        binding.tvaprob.text = "Materias aprobadas: "+aprob+" / "+clases
        binding.tvespecial.text = "Especiales realizados: "+espe
        binding.tvcurso.text = "Cursos de verano realizados: "+curso


        binding.btn.setOnClickListener {
            sessionManager.deleteAverage()
            sessionManager.deleteCareer()
            sessionManager.deleteCycleYear()
            sessionManager.deleteFaculty()
            sessionManager.deleteId()
            sessionManager.deleteName()
            sessionManager.deleteYear()
            sessionManager.deletelid()
            sessionManager.deletelpass()
            sessionManager.deletelyear()
            sessionManager.deleteCurso()
            sessionManager.deleteAprob()
            sessionManager.deleteClases()
            sessionManager.deleteEspe()
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}