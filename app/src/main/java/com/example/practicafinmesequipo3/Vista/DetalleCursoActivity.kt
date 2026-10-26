package com.example.practicafinmesequipo3.Vista

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.tuproyecto.Presentador.clsDetalleMateriaPresentador
import com.example.tuproyecto.R

class DetalleMateriaActivity : AppCompatActivity() {
    private lateinit var presenter: clsDetalleMateriaPresentador
    private lateinit var btnRegresar: Button
    private lateinit var imgMateria: ImageView
    private lateinit var txtNombre: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtTotalHoras: TextView
    private lateinit var txtDiasAtencion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_materia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnRegresar = findViewById(R.id.btnRegresar)
        imgMateria = findViewById(R.id.imgdetalleMateria)
        txtNombre = findViewById(R.id.txtdetalleNombre)
        txtCodigo = findViewById(R.id.txtdetalleCodigo)
        txtDescripcion = findViewById(R.id.txtdetalleDescripcion)
        txtEspacio = findViewById(R.id.txtdetalleEspacio)
        txtTotalHoras = findViewById(R.id.txtdetalleTotalHoras)
        txtDiasAtencion = findViewById(R.id.txtdetalleDiasAtencion)

        val id = intent.getIntExtra("MATERIA_ID", 0)

        presenter = clsDetalleMateriaPresentador(this)
        configurarListeners()

        if (id != 0) {
            presenter.cargarDetalle(id)
        }
    }

    private fun configurarListeners() {
        btnRegresar.setOnClickListener(this::ejecutarRegresar)
    }

    fun ejecutarRegresar(v: View) {
        finish()
    }

    fun mostrarDetalle(
        nombre: String,
        codigo: String,
        descripcion: String,
        espacio: String,
        totalHoras: String,
        diasAtencion: String,
        urlImagen: String
    ) {
        txtNombre.text = nombre.uppercase()
        txtCodigo.text = codigo
        txtDescripcion.text = descripcion
        txtEspacio.text = espacio
        txtTotalHoras.text = totalHoras
        txtDiasAtencion.text = diasAtencion

        val baseUrl = "http://192.168.1.69/tienda_api/"
        Glide.with(this)
            .load(baseUrl + urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery) // Imagen por defecto mientras carga
            .error(android.R.drawable.stat_notify_error)     // Imagen si falla la carga
            .into(imgMateria)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}