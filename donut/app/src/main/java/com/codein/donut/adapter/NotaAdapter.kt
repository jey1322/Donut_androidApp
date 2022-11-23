package com.codein.donut.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.codein.donut.R
import com.codein.donut.retrofit.LoginResponse

class NotaAdapter(private val context: Context, private val mNotas : List<LoginResponse.Component>, private val mRowLayout : Int):
    RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(context).inflate(mRowLayout, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        holder.nombre.text = mNotas[position].name
        holder.finalgrade.text = mNotas[position].final_grade
        if (mNotas[position].final_grade.toInt() > 59.4)
        {
            holder.estado.text = "Aprobado"
            holder.estado.setTextColor(context.resources.getColor(R.color.success))
        }
        else
        {
            holder.estado.text = "Reprobado"
            holder.estado.setTextColor(context.resources.getColor(R.color.error))
        }
        holder.partial1.text = mNotas[position].partial_1
        holder.partial2.text = mNotas[position].partial_2
        holder.partial3.text = mNotas[position].partial_3
        holder.secondcall.text = mNotas[position].second_call

        val isExpanded : Boolean = mNotas[position].visib
        holder.expanded.visibility = if (isExpanded) View.VISIBLE else View.GONE

        holder.select.setOnClickListener {
            mNotas[position].visib = !mNotas[position].visib
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return mNotas.size
    }

        class NotaViewHolder(val cview : View): RecyclerView.ViewHolder(cview)
        {
            val nombre = cview.findViewById<TextView>(R.id.tvname)
            val estado = cview.findViewById<TextView>(R.id.tvestado)
            val finalgrade = cview.findViewById<TextView>(R.id.tvfinalgrade)
            val partial1 = cview.findViewById<TextView>(R.id.partial_1)
            val partial2 = cview.findViewById<TextView>(R.id.partial_2)
            val partial3 = cview.findViewById<TextView>(R.id.partial_3)
            val secondcall = cview.findViewById<TextView>(R.id.second_call)

            val expanded : ConstraintLayout = cview.findViewById(R.id.expanded)
            val select = cview.findViewById<TextView>(R.id.select)
        }
}