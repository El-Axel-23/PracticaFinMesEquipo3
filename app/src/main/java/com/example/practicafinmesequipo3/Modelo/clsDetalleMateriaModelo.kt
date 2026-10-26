package com.example.practicafinmesequipo3.Modelo

import com.example.practicafinmesequipo3.Modelo.clsDetalleMateriaModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleMateriaPresentador(private val vista: DetalleMateriaActivity) {
    private val modelo = clsDetalleMateriaModelo()

    fun cargarDetalle(materiaId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val materia = modelo.obtenerDetalleMateria(materiaId)

                val codigo = "Código: " + materia.codigo
                val totalHoras = materia.totalhoras.toString() + " Horas"
                val diasAtencion = materia.diasatencion + " (" + materia.horarioatencion + " hrs)"

                vista.mostrarDetalle(
                    materia.nombre,
                    codigo,
                    materia.descripcion,
                    materia.espacio,
                    totalHoras,
                    diasAtencion,
                    materia.urlimagenprincipal
                )
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}