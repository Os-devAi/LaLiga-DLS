package com.nexusdev.laligadls.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.nexusdev.laligadls.MainActivity
import com.nexusdev.laligadls.R
import com.nexusdev.laligadls.adapter.AdapterPartidos
import com.nexusdev.laligadls.adapter.AdapterTabla
import com.nexusdev.laligadls.data.Equipos
import com.nexusdev.laligadls.data.Partidos
import com.nexusdev.laligadls.databinding.ActivityPartidosBinding

@Suppress("DEPRECATION")
class PartidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPartidosBinding

    private var adapter: AdapterPartidos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        click()
        getAllRealtime()
    }

    private fun click() {
        binding.btnAtras.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getAllRealtime() {
        val db = FirebaseFirestore.getInstance()
        val dataRef = db.collection("partidos")

        dataRef.orderBy("fecha", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    Toast.makeText(
                        this,
                        "Error al escuchar cambios: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@addSnapshotListener
                }

                if (snapshots != null) {
                    val listaEquipos = mutableListOf<Partidos>()
                    for (document in snapshots) {
                        val partido = document.toObject(Partidos::class.java)
                        partido.id = document.id
                        listaEquipos.add(partido)
                    }
                    configRecyclerView(listaEquipos)
                }
            }
    }

    private fun configRecyclerView(listaPartidos: List<Partidos>) {
        adapter = AdapterPartidos(listaPartidos)
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(this@PartidosActivity, 1, GridLayoutManager.VERTICAL, false)
            adapter = this@PartidosActivity.adapter
        }
        adapter?.onItemClick = {
//            val i = Intent(this@MainActivity, DetalleReparacionActivity::class.java)
//            i.putExtra("reparacion", it)
//            startActivity(i)
        }
    }
}