package com.example.practicafinmesequipo3.InterfaceApi

import com.example.practicafinmesequipo3.Modelo.dataDetalleMateria
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("detalle_materia.php")
    suspend fun detalleMateria(@Query("materia_id") materiaId: Int): dataDetalleMateria
    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(@Query("materia_id") materiaId: Int): dataObtenerRegistros
    @GET("registro_cursos.php")
    suspend fun registroCursos(@Query("materia_id") materiaId: Int): dataRegistrosCursos
}