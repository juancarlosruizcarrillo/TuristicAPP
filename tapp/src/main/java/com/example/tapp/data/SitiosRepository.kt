package com.example.tapp.data

class SitiosRepository {
    suspend fun getSitios() = ApiFactory.retrofit.getSitios()
}