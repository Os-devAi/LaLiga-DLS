package com.nexusdev.laligadls

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
import com.nexusdev.laligadls.adapter.AdapterTabla
import com.nexusdev.laligadls.data.Equipos
import com.nexusdev.laligadls.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: AdapterTabla? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)


        getAllRealtime()
    }

    @SuppressLint("SetTextI18n")
    private fun getAllRealtime() {
        val db = FirebaseFirestore.getInstance()
        val dataRef = db.collection("equipos")

        dataRef.orderBy("puntos", Query.Direction.DESCENDING)
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
                    val listaEquipos = mutableListOf<Equipos>()
                    for (document in snapshots) {
                        val reparacion = document.toObject(Equipos::class.java)
                        reparacion.id = document.id
                        listaEquipos.add(reparacion)
                    }
                    configRecyclerView(listaEquipos)
                }
            }
    }

    private fun configRecyclerView(listaReparaciones: List<Equipos>) {
        adapter = AdapterTabla(listaReparaciones)
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, 1, GridLayoutManager.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
        adapter?.onItemClick = {
//            val i = Intent(this@MainActivity, DetalleReparacionActivity::class.java)
//            i.putExtra("reparacion", it)
//            startActivity(i)
        }
    }
}