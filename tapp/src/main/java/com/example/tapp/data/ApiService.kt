package com.example.tapp.data

import com.example.tapp.model.Sitio
import retrofit2.http.GET

interface ApiService {

    @GET("juancarlosruizcarrillo/TuristicAPP/sitios")
    suspend fun getSitios(): Sitio
}