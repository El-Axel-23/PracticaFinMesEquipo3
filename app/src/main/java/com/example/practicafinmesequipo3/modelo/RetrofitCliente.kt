package com.example.practicafinmesequipo3.modelo

object RetrofitCliente{

    private const val BASE_URL = ""

    private  val jsonConfig=Json{
        ignoreUnknownKeys=true
        coerceInputValues=true
        isLenient=true
    }
    val apiServer:ApiService by lazy {
        val contentType="aplication/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConvertFactory(jsonConfig.asConvertFactory(contentType))
            .build()
            .create(ApiService::class.java)

    }
}